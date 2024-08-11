package com.jrgs.libro.unit2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Unidad2 {
	static Scanner miScanner = new Scanner(System.in);

	/**
	 * Lee un valor entero del stream System.in
	 * La función se repite hasta que el valor es entero
	 * @param suffix Descripción textual del valor a leer
	 *               que se mostrará al usuario
	 * @return El valor entero leído
	 */
	private static int readIntValue(String suffix) {
		System.out.println("Introduzca el valor de "+suffix+":");
		while ( miScanner.hasNextInt() == false ) {
			System.out.println("El valor de "+suffix+" tiene que ser entero!");
			miScanner.next();
		}
		return miScanner.nextInt();
	}

	public static void ejercicio331() {
			// Exercise 1
			System.out.print("Introduzca su nombre: ");
			String name = miScanner.next();
			System.out.print("Introduzca sus apellidos: ");
			String surname = miScanner.next();

			System.out.println("Hola, " + name + " " + surname);
	}

	public static void ejercicio333() {
			int day = readIntValue("día");
			int month = readIntValue("mes");
			int year = readIntValue("año");

			try {
				MyDate mydate = new MyDate(day, MyDate.Months.toMonth(month), year);
				year = readIntValue("otro año");
				mydate.setYear(year);
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		 }

	public static void ejercicio444(String imageFileName) {
		byte[] header = new byte[6];

		try ( FileInputStream myFile = new FileInputStream(imageFileName) ) {
			myFile.read(header);

			if ( header[0]==(byte)0x42 && header[1]==(byte)0x4D )
				System.out.println("Formato detectado: BMP.");
			else
			if ( header[0]==(byte)0x47 && header[1]==(byte)0x49 && header[2]==(byte)0x46 && header[3]==(byte)0x38 && header[5]==(byte)0x61 &&
					( header[4]==(byte)0x39 || header[4]==(byte)0x37 ) )
				System.out.println("Formato detectado: GIF.");
			else
			if ( header[0]==(byte)0xFF && header[1]==(byte)0xD8 && header[2]==(byte)0xFF  )
				System.out.println("Formato detectado: JPG.");
			else
			if ( header[0]==(byte)0x89 && header[1]==(byte)0x50 && header[2]==(byte)0x4E && header[3]==(byte)0x47 )
				System.out.println("Formato detectado: PNG.");
			else
				System.out.println("Formato no detectado.");
		}
		catch (IOException ex) {
			System.out.println("No se puede abrir el fichero  "+imageFileName+": "+ex.getMessage());
		}
	}

	// Las tres funciones siguientes son equivalentes
	//
	static Integer littleIndianToInt( byte a, byte b, byte c, byte d ) {
		Integer theInt = Byte.toUnsignedInt(d);
		theInt = (theInt << 8) + Byte.toUnsignedInt(c);
		theInt = (theInt << 8) + Byte.toUnsignedInt(b);
		theInt = (theInt << 8) + Byte.toUnsignedInt(a);
		return theInt;
	}

	static Integer littleIndianToIntv2( byte a, byte b, byte c, byte d ) {
		Integer theInt = Byte.toUnsignedInt(d);
        theInt = (theInt * 256) + Byte.toUnsignedInt(c);
        theInt = (theInt * 256) + Byte.toUnsignedInt(b);
        theInt = (theInt * 256) + Byte.toUnsignedInt(a);
		return theInt;
	}

	static Integer littleIndianToIntv3( byte a, byte b, byte c, byte d ) {
		Integer theInt = 256*256*256*Byte.toUnsignedInt(d) +
				         256*256*Byte.toUnsignedInt(c) +
	                     256*Byte.toUnsignedInt(b) +
				         Byte.toUnsignedInt(a);
		return theInt;
	}

	public static void ejercicio442(String imageFileName) {
		byte[] header = new byte[26];

		try ( FileInputStream myFile = new FileInputStream(imageFileName) ) {
			myFile.read(header);
			if (header[0] == (byte) 0x42 && header[1] == (byte) 0x4D) {
				System.out.println("Tamaño: " + littleIndianToInt(header[2], header[3], header[4], header[5]).toString());
				System.out.println("Alto: " + littleIndianToInt(header[18], header[19], header[20], header[21]).toString());
				System.out.println("Ancho: " + littleIndianToInt(header[22], header[23], header[24], header[25]).toString());
			}
		} catch (IOException e) {
			System.out.println("No se puede abrir el fichero  " + imageFileName + ": " + e.getMessage());
		}
	}

	public static void main(String[] args) {

		System.out.println("Ejercicios de la unidad 2");

		System.out.println("Seleccione un ejercicio:");
		System.out.println("1.- Ejercicio 3.3.1.");
		System.out.println("2.- Ejercicio 3.3.3.");
		System.out.println("3.- Ejercicio 4.4.1.");
		System.out.println("4.- Ejercicio 4.4.2.");

		switch (readIntValue("opcion")) {
			case 1: ejercicio331();
			        break;
			case 2: ejercicio333();
			        break;
			case 3: System.out.println("Introduzca el nombre del fichero: ");
					ejercicio444(miScanner.next());
				    break;
			case 4: System.out.println("Introduzca el nombre del fichero: ");
					ejercicio442(miScanner.next());
					break;
			default: System.out.println("Opción no válida.");
			         break;
		}

	}

}