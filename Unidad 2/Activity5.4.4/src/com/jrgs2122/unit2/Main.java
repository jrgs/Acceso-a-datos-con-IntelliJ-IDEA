package com.jrgs2122.unit2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String filename;
        String searchFor;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please type the name of the file to search in: ");
        filename = scanner.nextLine();
        System.out.print("Please type the string to search for: ");
        searchFor = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader( new FileReader( filename ));
            String currentLine;
            int lineNumber = 0;
            while ( ( currentLine = reader.readLine() ) != null ) {
                if (currentLine.contains(searchFor))
                    System.out.printf("Found in line %03d: %s \n", lineNumber, currentLine);
                lineNumber++;
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
