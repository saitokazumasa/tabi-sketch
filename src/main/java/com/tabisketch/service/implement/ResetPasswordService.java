package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.PasswordResetToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.ResetPasswordForm;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IResetPasswordService;
import com.tabisketch.service.ISendMailService;
import com.tabisketch.valueobject.Mail;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class ResetPasswordService implements IResetPasswordService {
    private final IPasswordResetTokensMapper passwordResetTokensMapper;
    private final IUsersMapper usersMapper;
    private final ISendMailService sendMailService;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(
            final IPasswordResetTokensMapper passwordResetTokensMapper,
            final IUsersMapper usersMapper,
            final ISendMailService sendMailService,
            final PasswordEncoder passwordEncoder
    ) {
        this.passwordResetTokensMapper = passwordResetTokensMapper;
        this.usersMapper = usersMapper;
        this.sendMailService = sendMailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void execute(final ResetPasswordForm resetPasswordForm) throws SelectFailedException, UpdateFailedException, DeleteFailedException, MessagingException {
        // PasswordResetTokenが存在しなければエラー
        final var token = UUID.fromString(resetPasswordForm.getToken());
        final PasswordResetToken passwordResetToken = this.passwordResetTokensMapper.selectByToken(token);
        if (passwordResetToken == null) throw new SelectFailedException("PasswordResetTokenの取得に失敗しました。");

        // TODO: PasswordResetTokenの有効期限が切れていたらエラー

        // Userが存在しなければエラー
        final User user = this.usersMapper.selectById(passwordResetToken.getUserId());
        if (user == null) throw new SelectFailedException("Userの取得に失敗しました。");

        // Userのpasswordを更新
        final String encryptedPassword = this.passwordEncoder.encode(resetPasswordForm.getPassword());
        final int updateUserResult = this.usersMapper.updatePassword(user.getId(), encryptedPassword);
        if (updateUserResult != 1) throw new UpdateFailedException("Userの更新に失敗しました。");

        // PasswordResetTokenを削除
        final int deletePasswordResetTokenResult = this.passwordResetTokensMapper.deleteById(passwordResetToken.getId());
        if (deletePasswordResetTokenResult != 1) throw new DeleteFailedException("PasswordResetTokenの削除に失敗しました。");

        // パスワード編集通知メールで送信
        final var mail = Mail.passwordEditNoticeMail(user.getMailAddress());
        this.sendMailService.execute(mail);
    }
}
