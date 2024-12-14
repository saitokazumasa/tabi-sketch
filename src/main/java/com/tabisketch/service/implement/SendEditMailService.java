package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.MailAddressAuthToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.SendEditMailForm;
import com.tabisketch.valueobject.Mail;
import com.tabisketch.mapper.IMailAddressAuthTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.ISendEditMailService;
import com.tabisketch.service.ISendMailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class SendEditMailService implements ISendEditMailService {
    private final IUsersMapper usersMapper;
    private final IMailAddressAuthTokensMapper mailAddressAuthTokensMapper;
    private final ISendMailService sendMailService;

    public SendEditMailService(
            final IUsersMapper usersMapper,
            final IMailAddressAuthTokensMapper mailAddressAuthTokensMapper,
            final ISendMailService sendMailService
    ) {
        this.usersMapper = usersMapper;
        this.mailAddressAuthTokensMapper = mailAddressAuthTokensMapper;
        this.sendMailService = sendMailService;
    }

    @Override
    public void execute(final SendEditMailForm sendEditMailForm) throws MessagingException {
        final User user = this.usersMapper.selectByMailAddress(sendEditMailForm.getCurrentMailAddress());

        final var mailAddressAuthToken = MailAddressAuthToken.generate(user.getId(), sendEditMailForm.getNewMailAddress());
        this.mailAddressAuthTokensMapper.insertWithNewMailAddress(mailAddressAuthToken);

        final var mail = Mail.generateEditMail(sendEditMailForm.getNewMailAddress(), mailAddressAuthToken.getToken());
        this.sendMailService.execute(mail);
    }
}
