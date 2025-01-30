package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.EditPasswordForm;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IEditPasswordService;
import com.tabisketch.service.ISendMailService;
import com.tabisketch.valueobject.Mail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditPasswordService implements IEditPasswordService {
    private final IUsersMapper usersMapper;
    private final ISendMailService sendMailService;
    private final PasswordEncoder passwordEncoder;

    public EditPasswordService(
            final IUsersMapper usersMapper,
            final ISendMailService sendMailService,
            final PasswordEncoder passwordEncoder
    ) {
        this.usersMapper = usersMapper;
        this.sendMailService = sendMailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void execute(final EditPasswordForm editPasswordForm) throws Exception {
        // Userが存在しなければエラー
        final User user = this.usersMapper.selectByMailAddress(editPasswordForm.getMailAddress());
        if (user == null) throw new SelectFailedException("Userの取得に失敗しました。");

        // パスワードが一致していなければエラー
        final boolean isNotMatchPassword = !this.passwordEncoder.matches(editPasswordForm.getCurrentPassword(), user.getPassword());
        if (isNotMatchPassword) throw new Exception("パスワードが一致しません。");

        // UserのPasswordを更新
        final String encryptedPassword = this.passwordEncoder.encode(editPasswordForm.getNewPassword());
        final int updateUserResult = this.usersMapper.updatePassword(user.getId(), encryptedPassword);
        if (updateUserResult != 1) throw new UpdateFailedException("Userの更新に失敗しました。");

        // パスワード編集通知メールを送信
        final var mail = Mail.passwordEditNoticeMail(user.getMailAddress());
        this.sendMailService.execute(mail);
    }
}
