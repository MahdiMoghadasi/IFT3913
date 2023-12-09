TP4-IFT3913 README

Nom: Ould Saada         Nom: Moghadasi	
Prénom: Ibrahim         Prénom: Mahdi
Matricule: 20151421     Matricule: 20184188

Github repository: https://github.com/MahdiMoghadasi/IFT3913
------------------------------------------------------------------------------------------------------------------------------------
# README du Projet TP4

## Aperçu
Le projet TP4 est basé sur Java, Junit5 et jupyter notebook pour faire l'analyse des chemins indepnadants.

## Structure du Projet
Les principaux composants du projet sont les suivants:

- **les fichier de tests**: nous avons 4 fichiers de test qui se trouve dans `IFT3913/TP4/src/test`,
                            fait a l'aide de Junit5, 2 blackbox et 2 whitebox,
                            pour tester les deux méthodes de conversion demandées.

- **src** : code source contenant tous nos tests ainsi que le projet java sur lequel
            ils s'appliquent qui se trouve a `IFT3913/TP4/src`
  
-**analyse** : dossier pour les fichiers d'analyse des chemins de couverture
               qui se trouve a `IFT3913/TP4/analyse`

- **CFD** : Toutes les images utilisées dans le rapport se trouvent dans le repertoire `TP3913/TP4/analyse/CFD`.

- **analyse-path.md** : c'est la où se trouve l'analyse de couverture de chemins pour
                        la methode `currencyConverter.MainWindow.convert()`


- **analyse-independant-paths.ipynb.** : Le notebook jupyter pour nous aider à trouver les chemin independants.


- **latex-source** : Ce doissier contient le rapport en format latex, ainsi le pdf et les images utilisées.
                      Nous avons utilisé le site overleaf pour écrire et compiler le rapport.



## Junit5:
Nous utilisons la librairie Junit5 qui se trouve dans `IFT3913/lib`.



## Libraries python
Nous utilisons les libraries `numpy`, `scipy` et `sympy`.
Il faut s'assurer que ces libraries python sont installées avant d'executer le notebook `analyse-independant-paths.ipynb.`
Nous pouvons installer les libraries python en lançant la ligne en sous dans CLI(Interface en ligne de commande).
      pip install `nom de librarie`
ex:   pip install numpy

## Exécution du Projet
- executer les tests se trouvant dans le dossier src/test, les tests sont faits a l'aide de Junit5.