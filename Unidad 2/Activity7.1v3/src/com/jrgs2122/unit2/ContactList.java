package com.jrgs2122.unit2;

import java.io.*;
import java.util.ArrayList;

public class ContactList extends ArrayList<Contact> {

   public void readContactFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File( fileName );

        if ( file.exists() ) {
            ObjectInputStream objectsFile = new ObjectInputStream(
                new FileInputStream( file ) );
            int numObjects = objectsFile.readInt();
            for ( ; numObjects > 0 ; numObjects-- ) {
                add((Contact) objectsFile.readObject());
            }
            objectsFile.close();
        }
    }

    public void writeContactFile(String fileName) throws  IOException {
        ObjectOutputStream objectsFile = new ObjectOutputStream(
                new FileOutputStream( fileName ));
        objectsFile.writeInt( size() );
        for ( Contact c : this )
            objectsFile.writeObject( c );
        objectsFile.close();
    }
}
