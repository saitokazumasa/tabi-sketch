# たびすけっち

## ブランチ

- main ... メインブランチ
- release ... アプリリリースブランチ

その他ブランチ名は [GitHub フロー](https://docs.github.com/ja/get-started/using-github/github-flow) に従い、変更内容に沿った名前にする。

## 初期設定

1. コマンドラインで `npm install` を実行する
2. .run/TabisketchApplication.run.xml の環境変数を書き換える

```xml
<envs>
    <!-- 各自の環境変数に置き換える -->
    <env name="DATABASE_PASSWORD" value="[データベースのパスワード]"/>
    <env name="DATABASE_URL" value="[データベースのURL]"/>
    <env name="DATABASE_USERNAME" value="[データベースのユーザー名]"/>
</envs>
```