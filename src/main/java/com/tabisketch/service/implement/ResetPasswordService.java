package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.PasswordResetToken;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IResetPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.util.UUID;


@Service
public class ResetPasswordService implements IResetPasswordService {
    private final IUsersMapper usersMapper;
    private final IPasswordResetTokensMapper passwordResetTokensMapper;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(
            final IUsersMapper usersMapper,
            final IPasswordResetTokensMapper passwordResetTokensMapper,
            final PasswordEncoder passwordEncoder
    ) {
        this.usersMapper = usersMapper;
        this.passwordResetTokensMapper = passwordResetTokensMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(
            final String token,
            final String password
    ) throws UpdateFailedException, DeleteFailedException, SQLDataException {
        final UUID tokenUUID = UUID.fromString(token);
        final String encryptedPassword = this.passwordEncoder.encode(password);

        final PasswordResetToken passwordResetToken = passwordResetTokensMapper.selectByToken(tokenUUID);
        if (passwordResetToken == null) throw new SQLDataException("トークンが見つかりませんでした。");

        final int userId = passwordResetToken.getUserId();

        final int passwordResetResult = usersMapper.updatePassword(userId, encryptedPassword);
        if (passwordResetResult != 1) throw new UpdateFailedException("パスワードの更新に失敗しました。");

        final int deleteTokenResult = passwordResetTokensMapper.deleteByUserId(userId);
        if (deleteTokenResult != 1) throw new DeleteFailedException("PasswordResetTokenの削除に失敗しました。");
    }
}
