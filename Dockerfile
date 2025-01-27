# TailwindCSSのビルドステージ
FROM node:21 AS tailwindcss-build
WORKDIR /app
COPY src ./src
COPY tailwind.config.js .
COPY package.json .
COPY package-lock.json .
RUN npm install
RUN npx tailwindcss -i src/main/resources/static/css/input.css -o src/main/resources/static/css/tailwind.css

# Spring Bootアプリケーションのビルドステージ
# GitHub ActionsでJavaのテストを実行するため、ここではテストをスキップする
FROM maven:3.9.9-eclipse-temurin-22 AS spring-boot-build
WORKDIR /app
COPY pom.xml .
COPY --from=tailwindcss-build /app/src ./src
RUN mvn package -DskipTests

# アプリケーション実行ステージ
FROM eclipse-temurin:22-jdk AS app-runtime
WORKDIR /app
COPY --from=spring-boot-build /app/target/tabisketch-0.0.1-SNAPSHOT.jar /app/app.jar
ARG _POSTGRES_HOST
ARG _POSTGRES_PORT
ARG _POSTGRES_DB
ARG _POSTGRES_USER
ARG _POSTGRES_PASSWORD
ARG _GOOGLE_MAPS_API_KEY
ARG _MAIL_PASSWORD
ARG _MAIL_USERNAME
ENV POSTGRES_HOST=${_POSTGRES_HOST}
ENV POSTGRES_PORT=${_POSTGRES_PORT}
ENV POSTGRES_DB=${_POSTGRES_DB}
ENV POSTGRES_USER=${_POSTGRES_USER}
ENV POSTGRES_PASSWORD=${_POSTGRES_PASSWORD}
ENV GOOGLE_MAPS_API_KEY=${_GOOGLE_MAPS_API_KEY}
ENV MAIL_PASSWORD=${_MAIL_PASSWORD}
ENV MAIL_USERNAME=${_MAIL_USERNAME}
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
