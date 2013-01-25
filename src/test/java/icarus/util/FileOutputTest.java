package icarus.util;

import icarus.util.FileOutput;
import icarus.util.SaveState;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.Test;

public class FileOutputTest {

    @Test
    public void testSaveObjectToFile() {
        SaveState s = new SaveState();
        FileOutput.saveObjectToFile(s, "test123");
        File file = new File("test123.ser");
        assertTrue(file.exists());
    }
}
