# TP 3

Mahdi Moghadasi    20184188
Ibrahim Ould Saada 20151421

### T1. Visualisation des Métriques et Calcul des Informations Pertinentes
 voici un résumé des statistiques descriptives pour les trois métriques :

| Métrique | Nombre d'observations | Moyenne | Écart-type | Minimum | l              | m                        | u              | Maximum | s | i | d| 
|----------|-----------------------|---------|------------|---------|----------------|--------------------------|----------------|---------|----|--|---| 
| TLOC     | 351                   | 115.13  | 130.87     | 3       | 47.5           | 83                       | 124.5          | 1045    | 240  | 0 (-68)|77
| WMC      | 351                   | 11.58   | 6.53       | 7       | 8              | 9                        | 12             | 64      | 18 | 2|4
| TASSERT  | 351                   | 27.19   | 34.80      | 1       | 10             | 17                       | 32             | 265     | 65 | 0 (-23)|22


`![boxplot](images/boxplot.png)`

pour le i nous avons prit la precision dans le cours qui dit :
si i est inférieur à la valeur minimale possible pour la variable (min), alors i = min

#### Interprétation des distributions :
- La distribution de **TLOC** est très étendue, avec un écart-type élevé, indiquant une grande variabilité dans le nombre de lignes de code entre les différentes classes. La valeur maximale est beaucoup plus grande que la médiane, ce qui suggère la présence de valeurs extrêmes ou de "outliers".
- Pour **WMC**, les valeurs sont plus concentrées, mais il y a encore une dispersion significative, comme en témoignent l'écart-type et la différence entre la médiane et le maximum.
- **TASSERT** présente également une grande variabilité et un certain nombre de valeurs extrêmes, ce qui est mis en évidence par la différence entre la médiane et le maximum et un écart-type relativement élevé par rapport à la moyenne.

Les distributions pour TLOC et TASSERT semblent être fortement influencées par les valeurs extrêmes, comme on peut le voir par les longues moustaches dans les boîtes à moustaches. En revanche, WMC a une distribution plus concentrée, avec des valeurs extrêmes moins marquées.
**