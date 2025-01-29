package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.EditMailAddressForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IEditMailAddressService;
import com.tabisketch.service.ISendMailService;
import com.tabisketch.valueobject.Mail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditMailAddressService implements IEditMailAddressService {
    private final IUsersMapper usersMapper;
    private final IMAATokensMapper maaTokensMapper;
    private final ISendMailService sendMailService;
    private final PasswordEncoder passwordEncoder;

    public EditMailAddressService(
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
    public void execute(final EditMailAddressForm editMailAddressForm) throws Exception {
        // Userが存在しなければエラー
        final User user = this.usersMapper.selectByMailAddress(editMailAddressForm.getCurrentMailAddress());
        if (user == null) throw new SelectFailedException("Userの取得に失敗しました。");

        // 新しいメールアドレスが使われていればエラー
        final boolean isUsedMailAddress = this.usersMapper.selectByMailAddress(editMailAddressForm.getNewMailAddress()) != null;
        if (isUsedMailAddress) throw new Exception("既に使用されているメールアドレスが指定されました。");

        // パスワードが一致していなければエラー
        final boolean isNotMatchPassword = !this.passwordEncoder.matches(editMailAddressForm.getPassword(), user.getPassword());
        if (isNotMatchPassword) throw new Exception("パスワードが一致しません。");

        // MAATokenを追加
        final var maaToken = editMailAddressForm.toMAAToken(user.getId());
        final int insertMAATokenResult = this.maaTokensMapper.insert(maaToken);
        if (insertMAATokenResult != 1) throw new InsertFailedException("MAATokenの追加に失敗しました。");

        // メールアドレス認証メールを送信
        final var mail = Mail.mailAddressEditMail(maaToken.getNewMailAddress(), maaToken.getToken());
        this.sendMailService.execute(mail);
    }
}
