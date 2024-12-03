package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.MailAuthenticationToken;
import com.tabisketch.bean.form.RegisterForm;
import com.tabisketch.bean.valueobject.Mail;
import com.tabisketch.mapper.IMailAuthenticationTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IRegisterService;
import com.tabisketch.service.ISendMailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService implements IRegisterService {
    private final IUsersMapper usersMapper;
    private final IMailAuthenticationTokensMapper mailAuthenticationTokensMapper;
    private final ISendMailService sendMailService;

    public RegisterService(
            final IUsersMapper usersMapper,
            final IMailAuthenticationTokensMapper mailAuthenticationTokensMapper,
            final ISendMailService sendMailService
    ) {
        this.usersMapper = usersMapper;
        this.mailAuthenticationTokensMapper = mailAuthenticationTokensMapper;
        this.sendMailService = sendMailService;
    }

    @Override
    @Transactional
    public void execute(final RegisterForm registerForm) throws MessagingException {
        final var user = registerForm.toUser();
        this.usersMapper.insert(user);

        final var mailAuthToken = MailAuthenticationToken.generate(user.id);
        this.mailAuthenticationTokensMapper.insert(mailAuthToken);

        final var mail = Mail.generateRegisterMail(user.mail, mailAuthToken.getToken());
        this.sendMailService.execute(mail);
    }
}
