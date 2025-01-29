package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.MAAToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.RegisterForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.valueobject.Mail;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IRegisterService;
import com.tabisketch.service.ISendMailService;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService implements IRegisterService {
    private final IUsersMapper usersMapper;
    private final IMAATokensMapper maaTokensMapper;
    private final ISendMailService sendMailService;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(
            final IUsersMapper usersMapper,
            final IMAATokensMapper maaTokensMapper,
            final ISendMailService sendMailService,
            final PasswordEncoder passwordEncoder
    ) {
        this.usersMapper = usersMapper;
        this.maaTokensMapper = maaTokensMapper;
        this.sendMailService = sendMailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void execute(final RegisterForm registerForm) throws Exception {
        // メールアドレスが使われていればエラー
        final boolean isUsedMailAddress = this.usersMapper.selectByMailAddress(registerForm.getMailAddress()) != null;
        if (isUsedMailAddress) throw new Exception("既に使用されているメールアドレスが指定されました。");

        // Userを追加
        final String encryptedPassword = this.passwordEncoder.encode(registerForm.getPassword());
        final var user = registerForm.toUser(encryptedPassword);
        final int insertUserResult = this.usersMapper.insert(user);
        if (insertUserResult != 1) throw new InsertFailedException("Userの追加に失敗しました。");

        // MAATokenを追加
        final var maaToken = MAAToken.generate(user.getId());
        final int insertMAATokenResult = this.maaTokensMapper.insert(maaToken);
        if (insertMAATokenResult != 1) throw new InsertFailedException("MAATokenの追加に失敗しました。");

        // メールアドレス認証メールを送信
        final var mail = Mail.registrationMail(user.getMailAddress(), maaToken.getToken());
        this.sendMailService.execute(mail);
    }
}
