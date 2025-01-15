# たびすけっち

# 規則
## ブランチ

- main ... メインブランチ
- release ... アプリリリースブランチ

その他ブランチ名は [GitHub フロー](https://docs.github.com/ja/get-started/using-github/github-flow) に従い、変更内容に沿った名前にする。

## 用語

- MailAddressAuthenticationToken -> MAAToken  
  メールアドレス認証トークン

# 開発
## 環境構築

1. コマンドラインで `npm install` を実行
2. DATABASEを作成
    ```postgresql
    CREATE DATABASE tabisketch;
    ```
3. .envファイルを作成
    ```
    DATABASE_HOST=データベースのホスト名
    DATABASE_PORT=データベースのポート番号
    DATABASE_NAME=データベース名
    DATABASE_USERNAME=データベースのユーザ名
    DATABASE_PASSWORD=データベースのパスワード
    MAIL_USERNAME=Gmailアカウントのユーザ名
    MAIL_PASSWORD=Gmailアカウントのアプリパスワード
    GOOGLE_MAPS_API_KEY=GoogleMapのAPIキー
    ```
4. `./bin/CreateTable.sh` を実行

## 各種コマンド

### 実行
```shell
./bin/Run.sh
```
TailwindCSSも同時にビルドされる

### テスト
```shell
./bin/Test.sh
```

### Jar ビルド
```shell
./bin/BuildJar.sh
```

### TailwindCSS ビルド
```shell
./bin/BuildTailwindCss.sh
```

### Table一括作成
```shell
./bin/CreateTable.sh
```

### Table一括削除
```shell
./bin/DropTable.sh
```