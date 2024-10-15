import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

public class PruebaActividades44 {
    @Test
    public void pruebaActividad441BMP() {
        try {
            Assertions.assertEquals(Actividades44.actividad442("3.bmp"), Actividades44.ImgFileTypes.BMP);
        }
        catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void pruebaActividad441PNG() {
        try {
            Assertions.assertEquals(Actividades44.actividad442("1.png"), Actividades44.ImgFileTypes.PNG);
        }
        catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void pruebaActividad441JPG() {
        try {
        Assertions.assertEquals(Actividades44.actividad442("2.jpg"), Actividades44.ImgFileTypes.JPG);
        }
        catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void pruebaActividad441GIF() {
        try {
            Assertions.assertEquals(Actividades44.actividad442("4.gif"), Actividades44.ImgFileTypes.GIF);
        }
        catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void pruebaActividad441FileNotFound()  {
        Assertions.assertThrows( IOException.class, () -> Actividades44.actividad442("Noname") );
    }

    @Test
    public void pruebaActividad441UnknownFileType() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> Actividades44.actividad442("5.tif") );
    }

    @ParameterizedTest
    @CsvSource({"3.bmp,768,307,707382", "otro.bmp,670,618,1243470"})
    public void pruebaactividad444(String filename, int width, int height, int size) {
        try {
            Actividades44.actividad444(filename);
        }
        catch(Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(Actividades44.width, width);
        Assertions.assertEquals(Actividades44.height, height);
        Assertions.assertEquals(Actividades44.size, size);
    }

    @Test
    public void pruebaactividad444FileNotFound() {
        IOException exception = Assertions.assertThrows(IOException.class, () -> Actividades44.actividad444("Noname") );
        Assertions.assertNotEquals(exception.getMessage(), Actividades44.ERR_NOT_A_BMP_FILE);
    }

    @ParameterizedTest
    @CsvSource({"1.png","2.jpg","4.gif","5.tif"})
    public void pruebaActividad442NotABMPFile(String filename) {
        IOException exception = Assertions.assertThrows(IOException.class, () -> Actividades44.actividad444(filename) );
        Assertions.assertEquals(exception.getMessage(), Actividades44.ERR_NOT_A_BMP_FILE);
    }

}
