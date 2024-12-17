package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.MAAToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.EditMailAddressForm;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IEditMailAddressService;
import com.tabisketch.service.ISendMailService;
import com.tabisketch.valueobject.Mail;
import jakarta.mail.MessagingException;
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
    public void execute(final EditMailAddressForm editMailAddressForm) throws MessagingException {
        final User user = this.usersMapper.selectByMailAddress(editMailAddressForm.getCurrentMailAddress());
        if (existMailAddress(editMailAddressForm.getNewMailAddress())) return;
        if (isNotMatchPassword(editMailAddressForm.getCurrentPassword(), user.getPassword())) return;

        final var maaToken = MAAToken.generate(user.getId(), editMailAddressForm.getNewMailAddress());
        this.maaTokensMapper.insert(maaToken);

        final var mail = Mail.mailAddressEditMail(maaToken.getNewMailAddress(), maaToken.getToken());
        this.sendMailService.execute(mail);
    }

    private boolean existMailAddress(final String mailAddress) {
        return this.usersMapper.selectByMailAddress(mailAddress) != null;
    }

    private boolean isNotMatchPassword(final String rawPassword, final String encodedPassword) {
        return !this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
