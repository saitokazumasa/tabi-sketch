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

## 事前準備

1. [mise-en-place](https://mise.jdx.dev/getting-started.html)をインストールする  

2. mise を使って node.js と openjdk をインストールする

   ```shell
   mise install
   ```

   mise の task runner を使って、各種タスクを実行できる

3. ```shell
   mise run
   ```

## 環境構築
以下の手順をコマンドラインで実行する。
1. 依存パッケージをインストール
```shell
npm install
```

2. `.env.example` を `.env` として複製、値を書き換え

3. Dockerをビルド&起動
```shell
mise run docker:build
mise run docker:up
```

## カスタムコマンド

### Docker ビルド
```shell
mise run docker:build
```

### Docker 起動
```shell
mise run docker:up
```

### Docker テスト実行
```shell
mise run docker:test
```

### Docker 破棄
```shell
mise run docker:down
```
