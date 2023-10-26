package org.example;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeTestRatio {

    public static void main(String[] args) {
        String localPath = "../resources/jfreechart/.git"; // Assurez-vous que c'est le chemin correct

        // Ouvrir le dépôt
        Repository repository;
        try {
            repository = new FileRepository(localPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Maps pour stocker les informations des commits
        Map<String, String> lastCommitsTests = new HashMap<>();
        Map<String, String> lastCommitsCode = new HashMap<>();

        try (Git git = new Git(repository)) {
            Iterable<RevCommit> commits = git.log().all().call();

            for (RevCommit commit : commits) {
                // Cette logique suppose que les messages de commit indiquent clairement s'il s'agit d'un commit de test ou de code.
                // Une stratégie plus robuste serait d'examiner les fichiers réellement modifiés dans chaque commit.
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

        } catch (GitAPIException | IOException e) {
            e.printStackTrace();
        }
    }
}

