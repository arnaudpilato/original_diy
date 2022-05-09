# etape 1
# On defini quel image on va utiliser pour le build ainsi que son alias
FROM node:16.15.0-alpine3.14 AS build
# On defini le repertoire dan lequel on va travailler
WORKDIR /app
# On copie les fichiers du repertoire courant dans le repertoire de travail
COPY . .
# on execute le script de build
RUN npm ci && npm run build

# etape 2
# On defini quel image on va utiliser pour le run
FROM nginx:alpine
# On copie le contenu du repertoire de build dans le repertoire de run
COPY --from=build /app/dist/original_diy_angular /usr/share/nginx/html
# On defini le port d'ecoute
EXPOSE 80
