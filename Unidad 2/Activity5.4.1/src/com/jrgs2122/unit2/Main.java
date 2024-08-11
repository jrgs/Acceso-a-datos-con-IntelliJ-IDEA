package com.jrgs2122.unit2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

   public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the name of the file: ");
        String fileName = scanner.nextLine();

        PrintWriter writer = null;
        try {
            boolean append = false;
            long lineNumber = 1;
            if ( new File( fileName ).exists() ) {
                System.out.print("The file exists, would you like to overwrite it (y/n)? ");
                String answer = scanner.nextLine();
                while ( !answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n") ) {
                   System.out.print("Incorrect answer, please choose (Y)es or (N)o ");
                    answer = scanner.nextLine();
                }
                if ( answer.toLowerCase().equals("n") ) {
                    append = true;
                    lineNumber = Files.lines(Paths.get(fileName)).count() + 1;
                 }
            }
            writer = new PrintWriter( new BufferedWriter( new FileWriter( fileName, append )));

            System.out.println("Enter a sentence to add to the notepad. Empty sentence to finish.");
            String sentence = scanner.nextLine();
            while ( !sentence.isBlank() ) {
                writer.printf("%d %s", lineNumber++, sentence);
                writer.println();
                sentence = scanner.nextLine();
            }
            System.out.println("Sentences stored on notepad...");
        }
        catch (IOException e) {
            System.out.println("An error ocurred: "+e.getMessage());
        }
        finally {
            if ( writer != null )
                writer.close();
        }
    }
}
