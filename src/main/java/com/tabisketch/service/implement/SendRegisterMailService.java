package com.tabisketch.service.implement;

import com.tabisketch.bean.valueobject.ServerMailAddress;
import com.tabisketch.service.ISendRegisterMailService;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendRegisterMailService implements ISendRegisterMailService {
    private final JavaMailSender mailSender;
    private final ServerMailAddress fromMail;

    public SendRegisterMailService(
            final JavaMailSender mailSender,
            final ServerMailAddress fromMail
    ) {
        this.mailSender = mailSender;
        this.fromMail = fromMail;
    }

    @Override
    @Async
    public void execute(final String toMail) throws MessagingException {
        final var message = mailSender.createMimeMessage();
        final var messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(fromMail.getValue());
        messageHelper.setTo(toMail);
        // TODO: どこかに文言をまとめておきたいかも
        messageHelper.setSubject("たびすけっち 登録確認メール");
        messageHelper.setText("たびすけっちの登録確認メールです。");

        mailSender.send(message);
    }
}
