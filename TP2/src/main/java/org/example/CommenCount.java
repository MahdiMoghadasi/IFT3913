package org.example;
import java.io.*;

public class CommenCount {

    private static int totalCommentLines = 0;
    private static int totalCodeLines = 0;

    public static void main(String[] args) throws IOException {
        File dir = new File("C:/Users/HP/Documents/GitHub/TP1-IFT3913/TP2/src/main/resources/jfreechart"); // Remplacez par votre chemin
        analyzeDirectory(dir);

        // Calculer la densité de commentaire
        double commentDensity = 0.0;
        if (totalCodeLines > 0) { // Prévenir la division par zéro
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
                    analyzeDirectory(file); // Récursion pour les sous-répertoires
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
            // Compter toutes les lignes non vides comme lignes de code (y compris les lignes de commentaire)
            if (!line.isEmpty()) {
                codeLines++;
            }
        }

        // Mise à jour des totaux globaux
        totalCommentLines += commentLines;
        totalCodeLines += codeLines;

        reader.close();
    }
}
