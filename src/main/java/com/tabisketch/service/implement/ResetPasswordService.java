package com.tabisketch.service.implement;

import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IResetPasswordService;
import org.springframework.beans.factory.parsing.PassThroughSourceExtractor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResetPasswordService implements IResetPasswordService {
    private final IUsersMapper usersMapper;
    private final IPasswordResetTokensMapper passwordResetTokensMapper;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(
            final IUsersMapper usersMapper, IPasswordResetTokensMapper passwordResetTokensMapper,
            final PasswordEncoder passwordEncoder
    ) {
        this.usersMapper = usersMapper;
        this.passwordResetTokensMapper = passwordResetTokensMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(final int userId, final String password) {
        final String encryptedPassword = this.passwordEncoder.encode(password);

        final int passwordResetResult = usersMapper.updatePassword(userId, encryptedPassword);
        if (passwordResetResult != 1) throw new IllegalArgumentException("パスワードの更新に失敗しました");

        final int deleteTokenResult = passwordResetTokensMapper.deleteByUserId(userId);
    }
}
