import com.jrgs2122.unit2.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestActivity52 {
    boolean compareFiles(String file1, String file2) throws FileNotFoundException, IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        while ( line1 != null || line2 != null) {
            if ( line1==null || line2==null )
                return false;
            if ( !line1.equals(line2) )
                return false;
            line1 = reader1.readLine();
            line2 = reader2.readLine();
        }
        return true;
    }

    @ParameterizedTest
    @CsvSource({"file1.txt, file2.txt, sorted1.txt", "file3.txt, file4.txt, sorted2.txt",
            "file5.txt, file6.txt, sorted3.txt", "file7.txt, file8.txt, sorted4.txt"})
    public void testMergeFileMethod(String file1, String file2, String outFile)
    {
        try {
            Main.mergeFiles(file1, file2, "sorted.txt");
            Assertions.assertTrue(compareFiles("sorted.txt", outFile));
        }
        catch(Exception e) {
            Assertions.fail();
        }
    }
}
