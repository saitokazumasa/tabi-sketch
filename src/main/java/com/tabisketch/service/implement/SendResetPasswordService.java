package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.PasswordResetToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.SendResetPasswordForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.ISendMailService;
import com.tabisketch.service.ISendResetPasswordService;
import com.tabisketch.valueobject.Mail;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class SendResetPasswordService implements ISendResetPasswordService {
    private final IUsersMapper usersMapper;
    private final IPasswordResetTokensMapper passwordResetTokensMapper;
    private final ISendMailService sendMailService;

    public SendResetPasswordService(
            final IUsersMapper usersMapper,
            final IPasswordResetTokensMapper passwordResetTokensMapper,
            final ISendMailService sendMailService
    ) {
        this.usersMapper = usersMapper;
        this.passwordResetTokensMapper = passwordResetTokensMapper;
        this.sendMailService = sendMailService;
    }

    public void execute(final SendResetPasswordForm sendResetPasswordForm) throws InsertFailedException, MessagingException, SelectFailedException {
        // Userが存在しなければエラー
        final User user = this.usersMapper.selectByMailAddress(sendResetPasswordForm.getMailAddress());
        if (user == null) throw new SelectFailedException("Userの取得に失敗しました。");

        // PasswordResetTokenを追加
        final PasswordResetToken passwordResetToken = PasswordResetToken.generate(user.getId());
        final int insertPasswordResetTokenResult = this.passwordResetTokensMapper.insert(passwordResetToken);
        if (insertPasswordResetTokenResult != 1) throw new InsertFailedException("PasswordResetTokenの追加に失敗しました。");

        // パスワードリセットメールを送信
        final var mail = Mail.passwordResetMail(user.getMailAddress(), passwordResetToken.getToken());
        this.sendMailService.execute(mail);
    }
}
