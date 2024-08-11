package com.jrgs2122.unit1.activities;

import java.io.*;

class City implements Serializable {
    protected String code;  // properties should be used here
    protected String name;

    public City( String _cod, String _name ) {
        code = _cod;
        name = _name;
    }

    public void write() {
        System.out.println(code + ": " + name);
    }
}

public class Activity151 {
    public static void serialize() throws FileNotFoundException, IOException {
        ObjectOutputStream objectsFile = new ObjectOutputStream( new FileOutputStream( new File("cities.dat")));
        objectsFile.writeInt(2);
        City aCity = new City("A", "Alicante");
        objectsFile.writeObject( aCity );
        aCity = new City("Gr", "Granada");
        objectsFile.writeObject( aCity );
        objectsFile.close();
    }

    public static void unserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectsFile = new ObjectInputStream( new FileInputStream( new File("cities.dat") ));
        int numObjects = objectsFile.readInt();
        for ( ; numObjects > 0 ; numObjects-- ) {
            City aCity = (City) objectsFile.readObject();
            aCity.write();
        }
        objectsFile.close();
    }


public static void main(String[] args) {
    try {
    serialize();
    System.out.println("Press a key...");
    System.in.read();
    unserialize();
    }
    catch (Exception e) {

    }
}
}
