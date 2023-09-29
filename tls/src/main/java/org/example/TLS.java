package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class TLS {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Veuillez spécifier le chemin du dossier source Java.");
            System.exit(1);
        }

        String folderPath = args[0];
        String outputPath = args.length > 2 && args[0].equals("-o") ? args[1] : null;

        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> processJavaFile(path, outputPath));
        }
    }

    private static void processJavaFile(Path javaFilePath, String outputPath) {
        String javaFileAbsolutePath = javaFilePath.toString();
        String packageName = extractPackageName(javaFileAbsolutePath);
        String className = javaFilePath.getFileName().toString().replace(".java", "");

        int tloc = Tloc.numTloc(new String[]{javaFileAbsolutePath});
        int tassert = TAssert.numTAssert(new String[]{javaFileAbsolutePath});

        double tcmp = tloc / (double) tassert;

        String outputLine = String.format("%s, %s, %s, %d, %d, %.2f", javaFileAbsolutePath, packageName, className, tloc, tassert, tcmp);

        if (outputPath != null) {
            writeToFile(outputPath, outputLine);
        } else {
            System.out.println(outputLine);
        }
    }

    private static String extractPackageName(String javaFileAbsolutePath) {
        // Implémentez cette méthode pour extraire le nom du paquet à partir du chemin absolu du fichier Java.
        return "Not Implemented";
    }

    private static void writeToFile(String outputPath, String outputLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath, true))) {
            writer.write(outputLine);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
