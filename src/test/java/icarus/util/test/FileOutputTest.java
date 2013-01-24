package icarus.util.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Hashtable;

import icarus.util.FileOutput;

import org.junit.Test;

public class FileOutputTest {

	@Test
	public void testSaveObjectToFile() {
		FileOutput out = new FileOutput();
		Hashtable h = new Hashtable();
		out.saveObjectToFile(h, "test123");
		File file = new File("test123.ser");
		assertTrue(file.exists());
	}

}
