package com.jrgs2122.unit2;

import java.io.*;
import java.util.Scanner;

public class Main {
    protected static Scanner scanner;
    protected static ContactList contactList;
    protected static final String fileName = "contacts.obj";

    protected static int menu() {
        System.out.println("MENU");
        System.out.println("====");
        System.out.println();
        System.out.println("1.- Add a contact.");
        System.out.println("2.- Show contact list.");
        System.out.println("3.- Search a contact.");
        System.out.println("0.- Terminate.");

       while ( !scanner.hasNextInt() ) {
           System.out.println("Choose an option between 0 and 3, please.");
           scanner.next();
       }

        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public static Contact readContact() {
        Contact contact = new Contact();

        System.out.print("Name: ");
        contact.setName( scanner.nextLine() );
        System.out.print("Surname: ");
        contact.setSurname( scanner.nextLine() );
        System.out.print("Email: ");
        contact.setEmail( scanner.nextLine() );
        System.out.print("Phone: ");
        contact.setPhone( scanner.nextLine() );
        System.out.print("Description: ");
        contact.setDescription( scanner.nextLine() );
        System.out.println();

        return contact;
    }

    public static void showContact(Contact contact) {
        System.out.print("Name: ");
        System.out.println( contact.getName() );
        System.out.print("Surname: ");
        System.out.println( contact.getSurname() );
        System.out.print("Email: ");
        System.out.println( contact.getEmail() );
        System.out.print("Phone: ");
        System.out.println( contact.getPhone() );
        System.out.print("Description: ");
        System.out.println( contact.getDescription() );
        System.out.println();
    }

    public static Contact searchContact() {
        String searchType;
        do {
            System.out.println("Search by (N)ame or (P)hone?");
            searchType = scanner.next().toLowerCase();
        } while ( !searchType.equals("n") && !searchType.equals("p") );

        System.out.println("Please type search criteria: ");
        String searchCriteria = scanner.next();
        for ( Contact c : contactList ) {
            String searching = searchType.equals("n") ? c.getName() + c.getSurname() : c.getPhone();
            if ( searching.contains(searchCriteria) )
                return c;
            }
        return null;
    }


    public static void main(String[] args) {
	// write your code here
        contactList = new ContactList();
        scanner = new Scanner(System.in);

        try {
            contactList.readContactFile( fileName );

            int option = menu();
            while (option != 0) {
                switch (option) {
                    case 1:
                        contactList.add(readContact());
                        break;
                    case 2:
                        for (Contact contact : contactList) {
                            showContact(contact);
                        }
                        break;
                    case 3:
                        Contact c = searchContact();
                        if ( c != null )
                            showContact(c);
                        else
                            System.out.println("Contact not found.");
                }
                option = menu();
            }

            contactList.writeContactFile( fileName );
        }
        catch ( IOException e ) {
            System.out.println("An error ocurred: ");
            e.printStackTrace();
        }
        catch ( ClassNotFoundException e ) {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }
    }
}
