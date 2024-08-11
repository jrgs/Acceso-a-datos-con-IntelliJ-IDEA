package com.jrgs2122.unit2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String filename;
         Scanner scanner = new Scanner(System.in);

        System.out.print("Please type the name of the file to search in: ");
        filename = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader( new FileReader( filename ));
            String currentLine;
            int lineNumber = 0;
            while ( ( currentLine = reader.readLine() ) != null ) {
                System.out.println(currentLine);
                lineNumber++;
                if ( lineNumber >= 23 ) {
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    lineNumber = 0;
                }
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
