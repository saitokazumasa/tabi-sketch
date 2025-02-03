FROM mcr.microsoft.com/playwright:v1.50.1-noble

WORKDIR /app

ENV SHELL=/bin/bash
ENV NODE_ENV=development
ENV IN_DOCKER=true

COPY package*.json ./

RUN npm ci

COPY . .

CMD ["npx", "playwright", "test"]
