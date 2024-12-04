package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IUsersMapper;
import org.springframework.stereotype.Service;

@Service
public class EditUserService {
    private final IUsersMapper usersMapper;

    public EditUserService(final IUsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    // 新規ユーザーを登録
    public int createUser(final User user) {
        return usersMapper.insert(user);
    }

    // ユーザーをメールアドレスで取得
    public User getUserByMail(final String mail) {
        return usersMapper.selectByMail(mail);
    }

    // ユーザーのメール認証状態を更新
    public boolean updateUserMailVerified(final int id, final boolean isMailVerified) {
        int rowsUpdated = usersMapper.updateMailVerified(id, isMailVerified);
        return rowsUpdated > 0; // 1以上の更新があれば成功と判断
    }
}
