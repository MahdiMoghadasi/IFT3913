package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tloc {
    public static void main(String[] args) throws IOException {

        int count = numTloc(args);
        System.out.println(count);

    }

    public static int numTloc(String[] args) {
        if (args.length == 0) {
            System.out.println("Run the program again and provide the file path as an argument.");
            return -1;
        }

        if(args.length > 1){
            System.out.println("Run the program again and provide only ONE file path as an argument.");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
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
}
