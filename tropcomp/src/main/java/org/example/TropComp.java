package org.example;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;


public class TropComp {

    public static void main(String[] args){

        if (args.length < 1) {
            System.err.println("please specify the file path.");
            System.exit(1);
        }

        String folderPath;
        String outputFilePath;
        int limit;


        if(args[0].equals("-o") && args.length > 3){
            folderPath = args[2];
            outputFilePath = args[1];
            limit = Integer.parseInt(args[3]);
        }
        else{
            folderPath = args[0];
            limit = Integer.parseInt(args[1]);
            outputFilePath = null;
        }

        try {
            // Deleting the output file if it exists and creating it again
            if(outputFilePath != null) {
                Path outputPath = Paths.get(outputFilePath);
                Files.deleteIfExists(outputPath);
                Files.createFile(outputPath);
            }
        }
        catch(Exception e ){
            System.out.println("File did not get deleted");
        }

        try {
            List<TLS> listTLS = TLS.createListTLS(folderPath);

            List<TLS> filteredList = filterTropComp(listTLS, limit);

            for(TLS elem: filteredList){
                String outputLine = String.format("%s, %s, %s, %d, %d, %.2f", elem.javaFileAbsolutePath, elem.packageName,
                        elem.className, elem.tloc, elem.tassert, elem.tcmp);

                if (outputFilePath != null) {
                    TLS.writeToFile(outputFilePath, outputLine);
                } else {
                    System.out.println(outputLine);
                }
            }

            if(filteredList.isEmpty()) System.out.println("No file satisfies the TLOC and TCMP limit entered.");
        }
        catch(Exception e ){
            System.out.println("List could not be created.");
        }
    }

    private static List<TLS> filterTropComp(List<TLS> listTLS, int limit){

        int length = listTLS.size();

        int topNumber = (int) Math.ceil(limit * length / 100.0);


        List<TLS> sortedByTloc = listTLS.stream()
                .sorted(Comparator.comparingInt(o -> -o.tloc)) // - for descending sort
                .limit(topNumber)
                .toList();

        List<TLS> sortedByTcmp = listTLS.stream()
                .sorted(Comparator.comparingDouble(o -> -o.tcmp)) // - for descending sort
                .limit(topNumber)
                .toList();

        // Finding common elements between sortedByTloc and sortedByTcmp
        List<TLS> commonList = sortedByTloc.stream()
                .filter(sortedByTcmp::contains)
                .toList();

        return commonList;
    }

}