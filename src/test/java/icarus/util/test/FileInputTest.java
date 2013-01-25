package icarus.util.test;

import icarus.commandfactory.CommandFactory;
import icarus.util.FileInput;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

import org.junit.Test;

public class FileInputTest {

    @Test
    public void testLoadObjectFromFilePos() throws FileNotFoundException {
        CommandFactory cf = new CommandFactory();
        cf.saveToFile("test123");
        FileInput in = new FileInput();
        assertNotNull(in.loadObjectFromFile("test123"));
    }

    @Test
    public void testLoadObjectFromFileNeg() throws FileNotFoundException {
        FileInput in = new FileInput();
        assertNull(in.loadObjectFromFile("2nju234uj2").get("reactor"));
    }
}
