package icarus.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Loads a file
 *
 * @author Team Haddock
 */
public class FileInput {

    /**
     * Reads the specified file return it as a Hashtable
     *
     * @param fileName The name of the file to be loaded
     *
     * @return A hashtable
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static SaveState loadObjectFromFile(String fileName) throws FileNotFoundException {
        SaveState s = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            s = (SaveState)in.readObject();

            in.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
        
        return s;
    }
}
