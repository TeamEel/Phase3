package icarus.util.test;

import icarus.util.FileOutput;
import java.io.File;
import java.util.Hashtable;
import static org.junit.Assert.*;
import org.junit.Test;

public class FileOutputTest {

    @Test
    public void testSaveObjectToFile() {
        Hashtable h = new Hashtable();
        FileOutput.saveObjectToFile(h, "test123");
        File file = new File("test123.ser");
        assertTrue(file.exists());
    }
}
