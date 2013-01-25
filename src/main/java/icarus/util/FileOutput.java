package icarus.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Saves a file
 *
 * @author Team Haddock
 */
public class FileOutput {

    /**
     * Saves a hashtable (containing reactor objects) to a file
     *
     * @param fileName The name of the file
     * @param HashTable h The objects to be saved
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveObjectToFile(Hashtable<String, Serializable> h, String fileName) {
        try {

            FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(h);

            out.close();
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
