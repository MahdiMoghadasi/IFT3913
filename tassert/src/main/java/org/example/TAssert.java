package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class TAssert {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Specify the FilePath");
            System.exit(1);
        }

        String filePath = args[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            int count = 0;
            Pattern pattern = Pattern.compile("\\b(?:assertEquals|assertNotEquals|assertTrue|assertFalse|assertNotNull|assertNull|assertSame|assertNotSame|assertArray|assertThat|assertThrows|fail)\\b");

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if ((pattern.matcher(line).find()) && (!line.isEmpty() && !line.startsWith("//") && !line.startsWith("/*") && !line.startsWith("*")) {
                    count++;}
            }

            System.out.println(count);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
