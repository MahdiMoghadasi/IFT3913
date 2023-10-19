TP1-IFT3913 README

Nom: Ould Saada         Nom: Moghadasi	
Prénom: Ibrahim         Prénom: Mahdi
Matricule: 20151421     Matricule: 20184188

Github repository: https://github.com/MahdiMoghadasi/TP1-IFT3913
------------------------------------------------------------------------------------------------------------------------------------
Overview
ce projet utilise plusieur metrique pour calculer la complexité des classe de test java utilisant la librairie Junit.

*pour faciliter la compilation et l'execution vous pouvez ouvrir le projet sur l'ide intellij

*sinon utiliser la ligne de commande comme suit:

------------------------------------------------------------------------------------------------------------------------------------

Compilation des classes java(sans jar):

On a 4 modules indépendants, dans chaque module pour compiler puisqu'on utilise le package org.example
la compilation doit etre executer a partir du directory qui contient le dossier org ce qui correspond au
repertoire qui se nomme java.

dans les repertoires suivants du projet:
tloc : \TP1-IFT3913\tloc\src\main\Java
tassert : \TP1-IFT3913\tassert\src\main\Java
Tls : \TP1-IFT3913\tls\src\main\Java
tropcomp : \TP1-IFT3913\tloc\src\main\Java

a partir de ces repertoire executer la ligne de commande suivante pour compiler la calsse voulu:

javac org/example/<classname>.java

-----------------------------------------------------------------------------------------------------------------------------------
Execution classes java (sans jar)
gere les path relatif et absolu.

tloc:      java org.example.Tloc <File_Path>

tassert:   java org.example.TAssert <File_Path>

tls:       java org.example.TLS <folder_Path>                                     (affiche sur le terminal)
                          ou
           java org.example.TLS -o <output_path.csv> <input_folder_path>          (ecrit sur fichier csv au path donné)

tropcomp:  java org.example.TropComp <folder_path> <seuil>                        (affiche sur le terminal) 
                          ou    
           java org.example.TLS -o <output_path.csv> <input_folder_path> <seuil>  (ecrit sur fichier csv au path donné)


----------------------------------------------------------------------------------------------------------------------------------

execution des jar:(executer depuis le directory ou se trouver le .jar)

tloc        :     java -jar tloc.jar <File_Path>

tassert     :     java -jar tassert.jar <File_Path>

tls         :     java -jar tls.jar <Folder_Path>                                         (affiche sur le terminal)
                             ou
                  java -jar tls.jar -o <output_path.csv> <input_folder_path>              (ecrit sur fichier csv au path donné)

tropcomp    :     java -jar tropcomp.jar <folder_path> <seuil>                            (affiche sur le terminal)
                                ou
                  java -jar tropcomp.jar -o <output_path.csv> <input_folder_path> <seuil> (ecrit sur fichier csv au path donné)
