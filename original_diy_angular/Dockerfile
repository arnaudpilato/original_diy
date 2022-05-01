FROM node:latest as build
WORKDIR /app

RUN npm install -g @angular/cli

COPY ./package*.json .
RUN npm install
COPY . .
RUN ng build

FROM nginx as runtime
COPY --from=build /app/dist/original_diy_angular /usr/share/nginx/html
