/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icarus.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author James
 */
public class GameFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String extension = getExtension(file);
        if (extension != null) {
            if (extension.equals(".ser")) {
                return true;
            }

        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Game files (.ser)";
    }

    private String getExtension(File file) {
        int pos = file.getName().lastIndexOf('.');
        if (pos > 0 && pos < file.getName().length() - 1) {
            return file.getName().substring(pos);
        }
        return null;
    }
}
