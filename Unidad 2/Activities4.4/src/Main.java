import java.io.IOException;
import java.util.Scanner;

public class Main {
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

    public static void main(String[] args) {

        System.out.println("Ejercicios del apartado 4");

        System.out.println("Seleccione un ejercicio:");
        System.out.println("1.- Ejercicio 4.4.2.");
        System.out.println("2.- Ejercicio 4.4.4.");

        switch (readIntValue("opcion")) {
            case 1:
                System.out.println("Introduzca el nombre del fichero: ");
                try {
                    Actividades44.actividad442(miScanner.next());
                }
                catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
                case 2:
                    System.out.println("Introduzca el nombre del fichero: ");
                    try{
                        Actividades44.actividad444(miScanner.next());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                break;

            default: System.out.println("Opción no válida.");
                break;
        }

    }
}