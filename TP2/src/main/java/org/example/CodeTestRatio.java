package org.example;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeTestRatio {

    public static void main(String[] args) {
        // Remplacez par le chemin absolu du répertoire de votre dépôt Git
        String localPath = "C:/Users/HP/Documents/GitHub/TP1-IFT3913/TP2/src/main/resources/jfreechart/";

        // Maps pour stocker les informations des commits
        Map<String, String> lastCommitsTests = new HashMap<>();
        Map<String, String> lastCommitsCode = new HashMap<>();

        try (Git git = Git.open(new File(localPath))) { // Ouverture du référentiel
            Iterable<RevCommit> commits = git.log().all().call();

            for (RevCommit commit : commits) {
                // Cette logique suppose que les messages de commit indiquent clairement s'il s'agit d'un commit de test ou de code.
                String message = commit.getFullMessage().toLowerCase();

                if (message.contains("test")) { // Ceci est un critère simpliste
                    lastCommitsTests.put(commit.getName(), message);
                } else {
                    lastCommitsCode.put(commit.getName(), message);
                }
            }

            // Calculer les métriques après avoir parcouru tous les commits
            int codeChanges = lastCommitsCode.size();
            int testChanges = lastCommitsTests.size();

            // Éviter la division par zéro
            if (testChanges == 0) {
                System.out.println("Aucun changement de test détecté ou division par zéro évitée.");
            } else {
                double codeToTestRatio = (double) codeChanges / testChanges;
                System.out.println("Ratio de Modification Code/Test : " + codeToTestRatio);
            }

        } catch (GitAPIException e) {
            System.err.println("Erreur lors de l'interaction avec le dépôt Git - " + e.getMessage());
            e.printStackTrace();  // Pour plus de détails sur l'erreur
        } catch (IOException e) {
            System.err.println("Erreur d'entrée/sortie - " + e.getMessage());
            e.printStackTrace();  // Pour plus de détails sur l'erreur
        }
    }
}
