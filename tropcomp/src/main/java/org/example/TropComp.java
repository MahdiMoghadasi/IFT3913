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

        //in case we are outputting to csv file
        if(args[0].equals("-o") && args.length > 3){
            folderPath = args[2];
            outputFilePath = args[1];
            limit = Integer.parseInt(args[3]);
        }
        else{
            // in case we are printing to terminal
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
            //getting the TLS list from TLS.java
            List<TLS> listTLS = TLS.createListTLS(folderPath);


            List<TLS> filteredList = filterTropComp(listTLS, limit);


            //writing the list containing the information for each java file
            for(TLS elem: filteredList){
                String outputLine = String.format("%s, %s, %s, %d, %d, %.2f", elem.javaFileAbsolutePath, elem.packageName,
                        elem.className, elem.tloc, elem.tassert, elem.tcmp);

                if (outputFilePath != null) {
                    // in case we specify a csv file and we write to the csv file
                    TLS.writeToFile(outputFilePath, outputLine);
                } else {
                    //writing to terminal
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

        // number of the files to be considered based on the limit
        int topNumber = (int) Math.ceil(limit * length / 100.0);


        // Create a new list of TLS objects sorted by tloc in descending order
        // and limited to topNumber elements
        List<TLS> sortedByTloc = listTLS.stream()
                .sorted(Comparator.comparingInt(o -> -o.tloc))
                .limit(topNumber)
                .toList();


        // Create a new list of TLS objects sorted by tcmp  in descending order
        // and limited to topNumber elements
        List<TLS> sortedByTcmp = listTLS.stream()
                .sorted(Comparator.comparingDouble(o -> -o.tcmp))
                .limit(topNumber)
                .toList();

        // Finding common elements between sortedByTloc and sortedByTcmp
        List<TLS> commonList = sortedByTloc.stream()
                .filter(sortedByTcmp::contains)
                .toList();

        return commonList;
    }

}