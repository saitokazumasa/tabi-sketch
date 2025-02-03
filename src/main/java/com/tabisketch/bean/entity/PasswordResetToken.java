package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/** パスワードリセットトークン */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {
    /** 識別子文字列 */
    private UUID uuid;
    /** 作成日 */
    private LocalDateTime createdAt;
    /** 関連する「ユーザー」の識別子 */
    private int userId;
}
