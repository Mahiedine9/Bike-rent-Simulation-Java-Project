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
 - [x] Main


## HowTo :

### Récupération du code source :
1. Placez-vous dans le répertoire où vous souhaitez cloner le projet 
2. $ git clone https://gitlab-etu.fil.univ-lille.fr/houssameddine.fidani.etu/fidani-coo.git


### Générez la documentation : 
1. Placez-vous dans la racine du projet cloné 
2. $ make javadoc

### Compiler et exécuter les sources : 

make run

### Compilation des tests :

make test-classes 

### Execution des tests :
1. cd vlille
2. java -cp ".:vlille/junit-4.13.2.jar:vlille/hamcrest-core-1.3.jar:vlille/test:vlille/src/classes" org.junit.runner.JUnitCore vlille.ClassToTest

### Génerer la javadoc : 
make javadoc

### Générer et exécuter l’archive (.jar) du projet : 
make jar



## Éléments de Code Saillants
### Conception du Projet et Modèles de Conception Utilisés

- **Architecture Modulaire** : Le projet est structuré en différentes classes telles que `Bike`, `Station`, et `ControlCenter`, reflétant une architecture modulaire et une séparation claire des responsabilités.

- **Modèle Stratégie (Strategy Pattern)** : Implémenté dans `RandomStrategy` et `RoundRobinStrategy`, ce modèle permet de modifier dynamiquement l'algorithme de redistribution des vélos sans changer le code client.

- **Singleton dans `ControlCenter`** : Cette classe utilise le modèle Singleton pour garantir une instance unique du centre de contrôle dans l'application, centralisant la gestion des stations et des vélos.

- **État (State Pattern) dans la Gestion des Vélos** : Les classes d'état comme `InService`, `OutOfService`, et `Rented` illustrent l'utilisation du modèle d'état pour gérer les différents états d'un vélo de manière fluide et extensible.

Ces éléments de conception montrent l'attention portée aux principes de conception solide et à la flexibilité du système pour faciliter l'évolution et la maintenance du projet.


 

