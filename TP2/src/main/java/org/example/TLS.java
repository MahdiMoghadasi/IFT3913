package org.example;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class TLS {

    int tloc;
    int tassert;

    public static void main(String[] args) {

        String folderPath = "TP2/src/main/resources/jfreechart";

        List<TLS> listTLS = new ArrayList<>();

        try{
            listTLS = createListTLS(folderPath);
        }
        catch (Exception e){

        }

        int tlocAllFiles = 0;
        int tassertAllFiles = 0;
        double ratioAllFiles;

        for(TLS elem: listTLS){

            tlocAllFiles += elem.tloc;
            tassertAllFiles += elem.tassert;
        }

        ratioAllFiles = tlocAllFiles / (double) tassertAllFiles;

        System.out.println("TLS: " + ratioAllFiles);
    }

    public static int numTloc(String filePath) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int count = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                //Check and only counts the lines containing code
                if (!line.isEmpty() && !line.startsWith("//") && !line.startsWith("/*") && !line.startsWith("*")) {
                    count++;
                }
            }

            reader.close();
            return count;
        } catch (Exception e) {
            return -1;
        }

    }
    public  static int numTAssert(String filePath){


        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            int count = 0;
            Pattern pattern = Pattern.compile("\\b(?:assertEquals|assertNotEquals|assertTrue|assertFalse|" +
                    "assertNotNull|assertNull|assertSame|assertNotSame|assertArray|assertThat|assertThrows|fail)\\b");

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // if the line contains any asserts and indeed is not a comment, we increment the counter.
                if (pattern.matcher(line).find()
                        && (!line.isEmpty() && !line.startsWith("//") && !line.startsWith("/*") && !line.startsWith("*"))){
                    count++;}
            }

            reader.close();
            return count;
        } catch (IOException e) {
            return -1;
        }

    }

    public static List<TLS> createListTLS(String folderPath) throws IOException{

        List<TLS> listTLS = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> processJavaFile(path, listTLS));
        }
        return listTLS;
    }

    private static void processJavaFile(Path javaFilePath, List<TLS> listTLS) {
        TLS object = new TLS();

        object.tloc = numTloc(javaFilePath.toString());
        object.tassert = numTAssert(javaFilePath.toString());
        listTLS.add(object);
    }

}