package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.EditPasswordForm;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IEditPasswordService;
import com.tabisketch.service.ISendMailService;
import com.tabisketch.valueobject.Mail;
import jakarta.mail.MessagingException;
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
    public void execute(final EditPasswordForm editPasswordForm) throws UpdateFailedException, MessagingException {
        final User user = this.usersMapper.selectByMailAddress(editPasswordForm.getMailAddress());

        if (notMatchCurrentPassword(editPasswordForm.getCurrentPassword(), user.getPassword())) return;

        final String encryptedPassword = this.passwordEncoder.encode(editPasswordForm.getNewPassword());
        final var newUser = new User(
                user.getId(),
                user.getMailAddress(),
                encryptedPassword,
                user.getMailAddressAuthenticated()
        );
        final int updateResult = this.usersMapper.update(newUser);

        if (updateResult != 1)  throw new UpdateFailedException("Userの更新に失敗しました。");

        final var mail = Mail.passwordEditNoticeMail(newUser.getMailAddress());
        this.sendMailService.execute(mail);
    }

    private boolean notMatchCurrentPassword(final String rawPassword, final String encodedPassword) {
        return !this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
