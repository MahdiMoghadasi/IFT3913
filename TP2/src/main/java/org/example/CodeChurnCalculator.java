package org.example;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;

public class CodeChurnCalculator {

    public static void main(String[] args) throws Exception {
        File gitDir = new File(".git/modules/TP2/src/main/resources/jfreechart");

        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try (Repository repository = builder.setGitDir(gitDir)
                .readEnvironment()
                .findGitDir()
                .build()) {
            Git git = new Git(repository);

            String result12Months = calculateAndPrintChurn(git, 12);
            String result24Months = calculateAndPrintChurn(git, 24);
            String result36Months = calculateAndPrintChurn(git, 36);

            System.out.println(result12Months);
            System.out.println(result24Months);
            System.out.println(result36Months);
        }
    }

    private static String calculateAndPrintChurn(Git git, int months) throws Exception {
        int testChurn = calculateChurn(git, months, true);
        int nonTestChurn = calculateChurn(git, months, false);

        StringBuilder result = new StringBuilder();
        result.append("For the last ").append(months).append(" months:\n");
        result.append("Code churn in test files: ").append(testChurn).append("\n");
        result.append("Code churn in non-test files: ").append(nonTestChurn).append("\n");
        result.append("\n");

        return result.toString();
    }

    private static int calculateChurn(Git git, int months, boolean isTestFile) throws Exception {
        int churn = 0;
        LogCommand log = git.log();
        for (RevCommit commit : log.call()) {
            if (isCommitInRange(commit, months)) {
                DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE);
                diffFormatter.setRepository(git.getRepository());
                for (DiffEntry entry : diffFormatter.scan(commit.getParent(0), commit)) {
                    if ((isTestFile(entry.getNewPath()) || isTestFile(entry.getOldPath())) == isTestFile) {
                        churn += diffFormatter.toFileHeader(entry).toEditList().stream()
                                .mapToInt(edit -> Math.abs(edit.getLengthA() + edit.getLengthB()))
                                .sum();
                    }
                }
            }
        }
        return churn;
    }

    private static boolean isCommitInRange(RevCommit commit, int months) {
        long endTime = System.currentTimeMillis();
        long startTime = endTime - ((long) months * 30 * 24 * 60 * 60 * 1000);
        long commitTime = commit.getCommitTime() * 1000L;
        return commitTime >= startTime && commitTime <= endTime;
    }

    private static boolean isTestFile(String filePath) {
        return filePath.endsWith("Test.java");
    }
}
