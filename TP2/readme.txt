TP1-IFT3913 README

Nom: Ould Saada         Nom: Moghadasi	
Prénom: Ibrahim         Prénom: Mahdi
Matricule: 20151421     Matricule: 20184188

Github repository: https://github.com/MahdiMoghadasi/IFT3913
------------------------------------------------------------------------------------------------------------------------------------
# README du Projet TP2

## Aperçu
Le projet TP2 est basé sur Maven et encapsule divers outils et métriques pour l'analyse du code source de jfreechart.

## Structure du Projet
Les principaux composants du projet sont les suivants:
- **Fichiers Source Java** : Situés sous le répertoire `src/main/java/org.example`. 
    Le projet comprend plusieurs classes telles que `App`, `AssertionsCounter`, `CodeChurnCalculator`, 
    `CodeTestCommitRatio`, `CommentCount`, `Jacoco`, et `TLS`.
  
- **Ressources** : Toutes les ressources supplémentaires requises pour le projet se trouvent sous le répertoire `src/main/resources`.
  
- **Configuration Maven** : La configuration du projet est maintenue dans le fichier `pom.xml.

- **Jacoco** : La configuration du jacoco est dans le fichier `pom.xml` de jfreechart qui se trouve dans Resources.

- **jfreechart** : Le projet `jfreechart` a été ajouté en tant que sous-module Git pour pouvoir l'analyser automatiquement.

## Exécution du Projet
- Le fichier `App.java` peut être exécuté pour voir les résultats des différents outils et métriques. Cependant, 
  `CodeChurnCalculator.java` est exclu de la méthode main de'app.java' pour éviter une logging excessive. 
   En plus, tous les fichiers `.java` peuvent être exécutés individuellement, 
   chacun ayant une méthode main et des métriques associées.

## Workflow & Artifacts
Le projet possède un workflow automatisé sur github. Après un build réussie, 
l'artefact `jacoco-report` est généré et offre des informations sur les métriques Jacoco. 
Il peut être téléchargé pour une analyse détaillée dans l'onglet action de github.

