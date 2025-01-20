package com.tabisketch.valueobject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Mail {
    private final String toMailAddress;
    private final String subject;
    private final String content;

    private Mail(final String toMailAddress, final String subject, final String content) {
        this.toMailAddress = toMailAddress;
        this.subject = subject;
        this.content = content;
    }

    /**
     * 新規登録メールを生成する
     */
    public static Mail registrationMail(final String toMailAddress, final UUID token) {
        final String subject = "たびすけっち 登録確認のメール";
        final String content = "「たびすけっち」へのユーサー登録、ありがとうございます。" +
                "メールアドレス認証のために以下のURLをクリックして、本登録してください。" +
                "https://tabisketch.com/mail/confirm/" + token.toString();
        return new Mail(toMailAddress, subject, content);
    }

    /**
     * メールアドレス編集メールを生成する
     */
    public static Mail mailAddressEditMail(final String toMailAddress, final UUID token) {
        final String subject = "たびすけっち メールアドレス変更確認のメール";
        final String content = "メールアドレスが変更されました。" +
                        "メールアドレス認証のために以下のURLをクリックして、確定してください。" +
                        "https://tabisketch.com/mail/confirm/" + token.toString();
        return new Mail(toMailAddress,subject, content);
    }

    /**
     * パスワード編集通知メールを生成する
     */
    public static Mail passwordEditNoticeMail(final String toMailAddress) {
        final String subject = "たびすけっち パスワード変更のお知らせメール";
        final String content = "パスワードが変更されました。";
        return new Mail(toMailAddress,subject, content);
    }

    /**
     * パスワードリセットメールを生成する
     */
    public static Mail passwordResetMail(final String toMailAddress, final UUID token) {
        final String subject = "たびすけっち パスワードリセットのメール";
        final String content = "パスワードリセットのために以下のURLをクリックして、パスワードをリセットしてください。" +
                "https://tabisketch.com/password/reset/" + token.toString();
        return new Mail(toMailAddress, subject, content);
    }
}
