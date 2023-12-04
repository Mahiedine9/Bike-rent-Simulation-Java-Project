# PROJET COO 2023 - simulation Location Vlille


## binomes : 
 - MAHIEDINE FERDJOUKH
 - HOUSSAM-EDDINE FIDANI


### Description du Projet :
Le projet Vlille est une implémentation en Java d'un système de partage de vélos, conçu pour gérer et simuler les opérations d'un service de partage de vélos. Il comprend des fonctionnalités pour la gestion des différents états des vélos, la gestion des stations, les routines de service, et une simulation des interactions des utilisateurs avec le système.


### Tâches Accomplies :

 - [x] UML réalisé
 - [x] Classes 
 - [x] Tests 
 - [ ] Main


## HowTo :

### Récupération du code source :
1.Placez-vous dans le répertoire où vous souhaitez cloner le projet 
2.git clone https://gitlab-etu.fil.univ-lille.fr/houssameddine.fidani.etu/fidani-coo.git

### Générez la documentation : 
1. Placez-vous dans la racine du projet cloné (pour toutes les autres commandes également)
2. javadoc -d doc src/vlille/*.java

### Compilation du src :
javac -d classes src/**/*.java 

### Éxecution du Main : 
java -cp classes vlille.Main 

### Compilation des tests :
javac -cp junit-4.13.2.jar:classes test/**/*.java

### Éxecution des tests :
java -cp "junit-4.13.2.jar:hamcrest-core-1.3.jar:test:classes" org.junit.runner.JUnitCore vlille.ClasseToTest  

### Génération et exécution de l'archive (.jar) :
1. jar cvfe vlille.jar vlille.Main -C classes .
2. java -jar vlille.jar


 

