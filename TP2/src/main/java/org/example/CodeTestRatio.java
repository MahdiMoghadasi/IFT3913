package org.example;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CodeTestRatio {

    public static void main(String[] args) {
        String localPath = "/path/to/jfreechart"; // chemin local vers votre dépôt

        // Construire le dépôt
        Repository repository = null;
        try {
            repository = new RepositoryBuilder()
                    .setGitDir(new File(localPath))
                    .readEnvironment()
                    .findGitDir()
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Les structures pour conserver les informations des commits
        Map<String, Date> lastCommitsTests = new HashMap<>();
        Map<String, Date> lastCommitsCode = new HashMap<>();

        try (Git git = new Git(repository)) {
            Iterable<RevCommit> commits = git.log().all().call();
            RevWalk walk = new RevWalk(repository);
[15:16]
            for (RevCommit commit : commits) {
                // Obtenez les détails du commit ici
                String commitName = commit.getName(); // ou d'autres détails pertinents
                Date commitDate = commit.getAuthorIdent().getWhen();

                // Ici, vous devez déterminer si le commit appartient à un fichier de test ou à un fichier de code.
                // Cela dépendra de votre projet spécifique. Habituellement, cela se fait en analysant le chemin ou le message du commit.
                boolean isTestCommit = ... ; // à déterminer
                boolean isCodeCommit = ... ; // à déterminer

                if (isTestCommit) {
                    // Mettre à jour la map des commits de test
                    lastCommitsTests.put(commitName, commitDate);
                } else if (isCodeCommit) {
                    // Mettre à jour la map des commits de code
                    lastCommitsCode.put(commitName, commitDate);
                }
            }

            // Après avoir parcouru tous les commits, vous pouvez maintenant calculer vos métriques.
            // Par exemple, pour calculer le Ratio de Modification Code/Test, vous pourriez faire quelque chose comme ceci :
            int codeChanges = lastCommitsCode.size();
            int testChanges = lastCommitsTests.size();

            double codeToTestRatio = (double) codeChanges / testChanges;
            System.out.println("Ratio de Modification Code/Test : " + codeToTestRatio);

            // ... et d'autres calculs pour différentes métriques.

        } catch (GitAPIException | IOException e) {
            e.printStackTrace();
        }
    }
}
