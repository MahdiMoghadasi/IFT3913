package org.example;
import java.io.*;

public class CommenCount {

    private static int totalCommentLines = 0;
    private static int totalCodeLines = 0;

    public static void main(String[] args) throws IOException {
        File dir = new File("TP2/src/main/resources/jfreechart");
        analyzeDirectory(dir);
        
        double commentDensity = 0.0;
        if (totalCodeLines > 0) {
            commentDensity = (double) totalCommentLines / (double) totalCodeLines * 100;
        }

        System.out.println("Total comment lines (CLOC) : " + totalCommentLines);
        System.out.println("Total lines of code (TLOC) : " + totalCodeLines);
        System.out.println("Comment Density (DC) : " + String.format("%.2f", commentDensity) + "%");
    }

    public static void analyzeDirectory(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith("Test.java")) {
                    analyzeFile(file);
                } else if (file.isDirectory()) {
                    analyzeDirectory(file);
                }
            }
        }
    }

    public static void analyzeFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int commentLines = 0;
        int codeLines = 0;
        boolean insideBlockComment = false;

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("/*")) {
                insideBlockComment = true;
            }
            if (insideBlockComment || line.startsWith("//")) {
                commentLines++;
            }
            if (line.endsWith("*/")) {
                insideBlockComment = false;
            }

            if (!line.isEmpty()) {
                codeLines++;
            }
        }

        totalCommentLines += commentLines;
        totalCodeLines += codeLines;

        reader.close();
    }
}
