TP3-IFT3913 README

Nom: Ould Saada         Nom: Moghadasi	
Prénom: Ibrahim         Prénom: Mahdi
Matricule: 20151421     Matricule: 20184188

Github repository: https://github.com/MahdiMoghadasi/IFT3913
------------------------------------------------------------------------------------------------------------------------------------
# README du Projet TP3

## Aperçu
Le projet TP4 est basé sur Java, Junit5 et jupyter notebook pour faire l'analyse des chemins indepnadants.

## Structure du Projet
Les principaux composants du projet sont les suivants:

- **les fichier de tests**: nous avons 4 fichier de test fait a l'aide de Junit5, 2blackbox et 2 whitebox, pour tester les deux methodes demander
                            ils se trouve dans `IFT3913/TP4/src/test`

- **src** : code source contenant tout nos test ainsi que le projet java sur lequel ils s'appliques et se trouve a `IFT3913/TP4/src`
  
-**analyse** : dossier ou se trouve les ressource qui on servit a analiser le chemins et qui se trouve a `IFT3913/TP4/analyse`

- **CFD** : Toutes les images utilisées dans le rapport se trouvent dans le repertoire `TP3913/TP4/analyse/CFD`.

- **analyse-path.md** : c'est la ou se trouve l'analyse de couverture de chemins pour la methode `currencyConverter.MainWindow.convert()`


- **rapport-latex** : Ce doissier continet le rapport en format latex, ainsi le pdf et les images utilisées.
                      Nous avons utilisé le site overleaf pour écrire et compiler le rapport.


- **analyse-independant-paths.ipynb.** : Le notebook jupyter pour nous aider a trouver les chemin independants.


## Libraries python
Nous utilisons les libraries `numpy`.
Il faut s'assurer que ces libraries python sont installées avant d'executer le notebook `analyse-independant-paths.ipynb.`
Nous pouvons installer les libraries python en lançant la ligne en sous dans CLI(Interface en ligne de commande).
      pip install `nom de librarie`
ex:   pip install numpy

## Junit5:
Nous utilisons la librairie Junit5 qui se trouve dans `IFT3913/lib`.


## Exécution du Projet
- executer les tests se trouveant dans le dossier test qui est dans le dossier src, les tests sont fait a l'aide de Junit5