package com.jrgs2122.unit2;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void mergeFiles(String filename1, String filename2, String outFile)
            throws IOException
    {
        BufferedReader file1 = null;
        BufferedReader file2 = null;
        PrintWriter writer = null;
        try {
            file1 = new BufferedReader(new FileReader(filename1));
            file2 = new BufferedReader(new FileReader(filename2));
            writer = new PrintWriter(new FileWriter(outFile));
            String line1 = null;
            String line2 = null;

            line1 = file1.readLine();
            line2 = file2.readLine();
            while (line1 != null || line2 != null) {
                int comparison = 0;
                if (line1 == null)
                    comparison = 1;
                else if (line2 == null)
                    comparison = -1;
                else
                    comparison = line1.compareTo(line2);
                if (comparison < 0) {
                    writer.println(line1);
                    line1 = file1.readLine();
                } else {
                    writer.println(line2);
                    line2 = file2.readLine();
                }
            }
        } finally {
            if ( file1 != null )
                file1.close();
            if ( file2 != null )
                file2.close();
            if ( writer != null )
                writer.close();
        }
    }

   public static void main(String[] args) throws IOException {
        // write your code here

        String filename1, filename2;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the name of the first file: ");
        filename1 = scanner.nextLine();
        System.out.print("Please enter the name of the second file: ");
        filename2 = scanner.nextLine();

       try {
            mergeFiles(filename1, filename2, "sorted.txt");
            System.out.println("Sorted!!!");
        } catch (IOException e) {
           System.out.println("An error ocurred! " + e.getMessage());
       }
    }
}
