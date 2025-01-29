package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.MAAToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IAuthMailAddressService;
import com.tabisketch.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthMailAddressService implements IAuthMailAddressService {
    private final IMAATokensMapper maaTokensMapper;
    private final IUsersMapper usersMapper;

    public AuthMailAddressService(
            final IMAATokensMapper maaTokensMapper,
            final IUsersMapper usersMapper
    ) {
        this.maaTokensMapper = maaTokensMapper;
        this.usersMapper = usersMapper;
    }

    @Override
    @Transactional
    public void execute(final String maaTokenStr) throws UpdateFailedException, DeleteFailedException, SelectFailedException {
        // MAATokenが存在しなければエラー
        final var token = UUID.fromString(maaTokenStr);
        final MAAToken maaToken = this.maaTokensMapper.selectByToken(token);
        if (maaToken == null) throw new SelectFailedException("MAATokenの取得に失敗しました。");

        // TODO: MAATokenの有効期限が切れていたらエラー

        // Userが存在しなければエラー
        final User user = this.usersMapper.selectById(maaToken.getUserId());
        if (user == null) throw new SelectFailedException("Userの取得に失敗しました。");

        // 更新がある場合は、UserのmailAddressを更新
        if (StringUtils.notNullAndNotEmpty(maaToken.getNewMailAddress())) {
            final int updateMailAddressResult = this.usersMapper.updateMailAddress(user.getId(), maaToken.getNewMailAddress());
            if (updateMailAddressResult != 1) throw new UpdateFailedException("Userの更新に失敗しました。");
        }

        // UserのmailAddressAuthenticatedを更新
        final int updateMailAddressAuthenticated = this.usersMapper.updateMailAddressAuthenticated(user.getId(), true);
        if (updateMailAddressAuthenticated != 1) throw new UpdateFailedException("Userの更新に失敗しました。");

        // MAATokenを削除
        final int deleteMAATokenResult = this.maaTokensMapper.delete(maaToken.getId());
        if (deleteMAATokenResult != 1) throw new DeleteFailedException("MAATokenの削除に失敗しました。");
    }
}
