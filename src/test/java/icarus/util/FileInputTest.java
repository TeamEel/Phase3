package icarus.util;

import icarus.operatingsoftware.OperatingSoftware;
import icarus.operatingsoftware.PowerPlant;
import icarus.util.FileInput;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

import org.junit.Test;

public class FileInputTest {

    @Test
    public void testLoadObjectFromFilePos() throws FileNotFoundException {
        OperatingSoftware cf = new OperatingSoftware(new PowerPlant());
        cf.saveToFile("test123");
        assertNotNull(FileInput.loadObjectFromFile("test123"));
    }

    @Test
    public void testLoadObjectFromFileNeg() throws FileNotFoundException {
        assertNull(FileInput.loadObjectFromFile("2nju234uj2"));
    }
}
