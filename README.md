# たびすけっち

## ブランチ
- main ... メインブランチ
- release ... アプリリリースブランチ

その他ブランチ名は [GitHub フロー](https://docs.github.com/ja/get-started/using-github/github-flow) に従い、変更内容に沿った名前にする。

## Tailwind CSS
初期設定  
`npm install`

ビルドコマンド  
`npx tailwindcss -i .\src\main\resources\static\css\input.css -o .\src\main\resources\static\css\tailwind.css --watch`  

## application.properties
application.properties は以下の内容に書き換える
```
spring.application.name=tabisketch

spring.datasource.url=jdbc:postgresql://localhost:[ポート番号]/tabisketch
spring.datasource.username=[ユーザー名]
spring.datasource.password=[パスワード]
```


