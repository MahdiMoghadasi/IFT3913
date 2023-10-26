
package org.example;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class CodeTestRatio {

    public static void main(String[] args) {

        String repoPath = ".git/modules/TP2/src/main/resources/jfreechart";
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try {
            Git git = Git.open(new File(repoPath));
            Iterable<RevCommit> commits = git.log().call();
            int totalCommits = 0;
            int testCommits = 0;
            int codeCommits = 0;
            for (RevCommit commit : commits) {
                totalCommits++;
                String commitMessage = commit.getFullMessage().toLowerCase();
                if (commitMessage.contains("test")) {
                    testCommits++;
                } else {
                    codeCommits++;
                }
            }
            double testCommitFrequency = (double) testCommits / totalCommits;
            double testModificationRatio = (double) testCommits / codeCommits;
            System.out.println("Total Commits: " + totalCommits);
            System.out.println("Test Commits: " + testCommits);
            System.out.println("Code Commits: " + codeCommits);
            System.out.println("Test Commit Frequency: " + testCommitFrequency);
            System.out.println("Test Modification Ratio: " + testModificationRatio);
        } catch (IOException | org.eclipse.jgit.api.errors.GitAPIException e) {
            e.printStackTrace();
        }
    }
}
