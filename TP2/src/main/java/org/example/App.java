package org.example;


public class App {

    public static void main(String[]args){

        String[] newArgs = new String[0];
        TLS.main(newArgs);
        CodeTestRatio.main(newArgs);
        try{
            CommenCount.main(newArgs);
        }
        catch (Exception e){}
        Jacoco.main(newArgs);

    }
}

