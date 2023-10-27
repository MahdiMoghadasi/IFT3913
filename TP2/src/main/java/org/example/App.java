package org.example;


public class App {

    public static void main(String[]args){

        String[] newArgs = new String[0];
        CodeTestCommitRatio.main(newArgs);
        TLS.main(newArgs);
        Jacoco.main(newArgs);
        try{
            CommenCount.main(newArgs);
            AssertionsCounter.main(newArgs);
        }
        catch (Exception e){}
    }
}

