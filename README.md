# たびすけっち

## ブランチ
- main ... メインブランチ
- release ... アプリリリースブランチ

その他ブランチ名は [GitHub フロー](https://docs.github.com/ja/get-started/using-github/github-flow) に従い、変更内容に沿った名前にする。

## 環境変数
- DATABASE_URL ... データベースのURL
- DATABASE_USERNAME ... データベースのユーザー名
- DATABASE_PASSWORD ... データベースのパスワード

## Tailwind CSS
初期設定  
`npm install`

ビルドコマンド  
`npx tailwindcss -i .\src\main\resources\static\css\input.css -o .\src\main\resources\static\css\tailwind.css --watch`  
