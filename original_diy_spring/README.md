# Projet OriginalDIY
Ce projet a été réalisé au sein de la Wild Code School, l’équipe était composée d’Arnaud Pilato, Mathias Tisserand et Mathieu Hoarau.
Pour Virginie Mattazzi

Construit avec :
* Spring Boot / Angular

Prérequis:
* Angular v^13
* Java v^v11
* NPM v^8.3.1
* NodeJS v^16.14.0
* Spring Boot v^2.6.6

Cloner le repository :
* git clone git@github.com:arnaudpilato/original_diy.git

## Spring Boot Configuration
01 : Configuration environnement
* Mise en place du fichier .env à partir du fichier .env.local à la racine du projet, puis compléter tous les champs de ce fichier avec vos informations:

02 : mvn install :
* Après avoir renseigné le fichier .env, lancer la commande mvn install afin d'installer les dépendances nécessaires au projet

03 : Mettre en place la base de données
* Dans fichier application-local-properties changer le spring.jpa.hibernate.ddl-auto=update en create

04 : Mettre en route le serveur :
* Pour démarrer le serveur lancer la commande ./env_spring_boot_run.sh afin de lancer le serveur via les variables d'environnement du fichier .env

05 : Arrêter le serveur :
* Après avoir mis en place la base de données, stopper le serveur afin de mettre en place l'hydratation d la base de données

06 : Hydrater la base de données :
* Dans le fichier application properties décommenter la ligne spring.datasource.initialization-mode=always

07 : Remettre en route le serveur :
* Redémarrer le serveur avec la commande ./env_spring_boot_run.sh

08 : Désactiver l'hydratation de la base de données
* Dès que le serveur est lancé il reste plus qu'à commenter la ligne spring.datasource.initialization-mode=always et mettre la ligne spring.jpa.hibernate.ddl-auto=create à update dans le fichier application properties

* 09 : Enjoy!

## Angular Configuration
1 : NPM install :
* Installer les dépendances du projet via la commande NPM install

2 : Lancer le serveur
* Vous pouvez maintenant lancer le serveur avec la commande ng serve -o

* 3 : Enjoy

## Contact :
- Arnaud Pilato : https://github.com/arnaudpilato
- Mathias Tisserand :  https://github.com/Tisserandmat
- Mathieu Hoarau : https://github.com/Mathelchrist
