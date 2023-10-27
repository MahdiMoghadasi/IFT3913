package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssertionsCounter {

    public static void main(String[] args) throws Exception {
        String path = "TP2/src/main/resources/jfreechart/src/test/java/org/jfree";
        File directory = new File(path);
        Results results = countAssertionsInDirectory(directory.toPath());

        System.out.println("Total tests: " + results.totalTests);
        System.out.println("Total assertions: " + results.totalAssertions);
        if (results.totalTests > 0) {
            double averageAssertions = (double) results.totalAssertions / results.totalTests;
            System.out.println("Average assertions per test: " + averageAssertions);
        } else {
            System.out.println("No tests found.");
        }
    }

    private static int countAssertions(String content) {
        // Regex to match assertion statements
        Pattern pattern = Pattern.compile("\\bassert[A-Za-z]*\\(");
        Matcher matcher = pattern.matcher(content);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static int countTests(String content) {
        Pattern pattern = Pattern.compile("@Test");
        Matcher matcher = pattern.matcher(content);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static Results countAssertionsInDirectory(Path directory) throws Exception {
        int totalAssertions = 0;
        int totalTests = 0;
        for (File file : directory.toFile().listFiles()) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                Path filePath = file.toPath();
                String content = Files.readString(filePath);
                totalAssertions += countAssertions(content);
                totalTests += countTests(content);
            } else if (file.isDirectory()) {
                Results results = countAssertionsInDirectory(file.toPath());
                totalAssertions += results.totalAssertions;
                totalTests += results.totalTests;
            }
        }
        return new Results(totalTests, totalAssertions);
    }

    static class Results {
        int totalTests;
        int totalAssertions;

        Results(int totalTests, int totalAssertions) {
            this.totalTests = totalTests;
            this.totalAssertions = totalAssertions;
        }
    }
}
