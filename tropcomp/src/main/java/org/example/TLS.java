package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TLS {

    String javaFileAbsolutePath;
    String packageName;
    String className;
    int tloc;
    int tassert;
    double tcmp;


    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("please specify the file path.");
            System.exit(1);
        }

        String folderPath;
        String outputFilePath;

        if(args[0].equals("-o") && args.length > 2){
            folderPath = args[2];
            outputFilePath = args[1];
        }
        else{
            folderPath = args[0];
            outputFilePath = null;
        }

        // Deleting the output file if it exists and creating it again
        if(outputFilePath != null) {
            Path outputPath = Paths.get(outputFilePath);
            Files.deleteIfExists(outputPath);
        }


        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> processJavaFile(path, outputFilePath));
        }
    }

    private static void processJavaFile(Path javaFilePath, String outputFilePath) {
        TLS object = new TLS();
        object.javaFileAbsolutePath = javaFilePath.toString();
        object.packageName = extractPackageName(object.javaFileAbsolutePath);
        object.className = javaFilePath.getFileName().toString().replace(".java", "");
        object.tloc = Tloc.numTloc(new String[]{object.javaFileAbsolutePath});
        object.tassert = TAssert.numTAssert(new String[]{object.javaFileAbsolutePath});
        object.tcmp = object.tloc / (double) object.tassert;

        String outputLine = String.format("%s, %s, %s, %d, %d, %.2f", object.javaFileAbsolutePath, object.packageName,
                object.className, object.tloc, object.tassert, object.tcmp);


        if (outputFilePath != null) {
            writeToFile(outputFilePath, outputLine);
        } else {
            System.out.println(outputLine);
        }
    }

    private static String extractPackageName(String javaFileAbsolutePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(javaFileAbsolutePath))) {
            String line;
            Pattern pattern = Pattern.compile("^\\s*package\\s+([^;]+);");
            String packageName = "not found";
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    packageName = matcher.group(1);
                }
            }
            return packageName;
        }
        catch (Exception e){
            return "extract package not working";
        }
    }

    private static void writeToFile(String outputFilePath, String outputLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            writer.write(outputLine);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}