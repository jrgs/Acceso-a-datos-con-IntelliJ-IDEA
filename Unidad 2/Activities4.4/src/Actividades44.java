
import java.io.FileInputStream;
import java.io.IOException;


public class Actividades44 {
    public enum ImgFileTypes { BMP, GIF, JPG, PNG };

    public static int width = 0;
    public static int height = 0;
    public static int size = 0;

    public static String ERR_NOT_A_BMP_FILE = "Error. El fichero no es un BMP.";
    public static String ERR_UNKNOWN_FILE_FORMAT = "Formato de fichero desconocido";

    public Actividades44() {
    }

    //region actividad-4-4-1

    private static final byte[] BMP_HEADER = { (byte)0x42, (byte)0x4D };
    private static final byte[] JPG_HEADER = { (byte)0xFF, (byte)0xD8, (byte)0xFF };
    private static final byte[] PNG_HEADER = { (byte)0x89, (byte)0x50, (byte)0x4E, (byte)0x47 };
    private static final byte[] GIF1_HEADER = { (byte)0x47, (byte)0x49, (byte)0x46, (byte)0x38, (byte)0x39, (byte)0x61 };
    private static final byte[] GIF2_HEADER = { (byte)0x47, (byte)0x49, (byte)0x46, (byte)0x38, (byte)0x37, (byte)0x61 };


    static boolean comprobarDescriptor(byte[] descriptor, byte fileFormat[]) {
        for ( int i = 0; i < fileFormat.length; i++ )
            if ( descriptor[i] != fileFormat[i] )
                return false;
        return true;
    }

    public static Actividades44.ImgFileTypes actividad442(String filename)  throws IOException
    {
        byte[] header = new byte[6];

        FileInputStream myFile = new FileInputStream(filename);
        myFile.read(header);

        if ( comprobarDescriptor(header, BMP_HEADER) )
            return ImgFileTypes.BMP;
        else
        if ( comprobarDescriptor( header, JPG_HEADER))
            return ImgFileTypes.JPG;
        else
        if ( comprobarDescriptor( header, PNG_HEADER) )
            return ImgFileTypes.PNG;
        else
        if ( comprobarDescriptor(header, GIF1_HEADER) || comprobarDescriptor(header, GIF2_HEADER) )
            return ImgFileTypes.GIF;
        else
            throw new IllegalArgumentException(ERR_UNKNOWN_FILE_FORMAT + ": " + filename);
    }

    //endregion

    //region actividad-4-4-2

    static Integer littleIndianToInt( byte a, byte b, byte c, byte d ) {
        Integer theInt = Byte.toUnsignedInt(d);
        theInt = (theInt << 8) + Byte.toUnsignedInt(c);
        theInt = (theInt << 8) + Byte.toUnsignedInt(b);
        theInt = (theInt << 8) + Byte.toUnsignedInt(a);
        return theInt;
    }

    public static void actividad444(String filename) throws IOException {
        byte[] header = new byte[26];
        FileInputStream myFile = null;

        myFile = new FileInputStream(filename);
        myFile.read(header);
        if (comprobarDescriptor(header, BMP_HEADER)) {
            size = littleIndianToInt(header[2], header[3], header[4], header[5]);
            width = littleIndianToInt(header[18], header[19], header[20], header[21]);
            height = littleIndianToInt(header[22], header[23], header[24], header[25]);
        }
        else {
            myFile.close();
            throw new IOException(ERR_NOT_A_BMP_FILE);
        }
        myFile.close();
    }

    //endregion
}