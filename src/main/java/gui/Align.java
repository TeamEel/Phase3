package gui;

import java.awt.Component;
import javax.swing.Box;

/**
 *
 * @author drm
 */
public class Align {
    static Component left(Component c) {
        Box b = Box.createHorizontalBox();
        b.add(c);
        b.add(Box.createHorizontalGlue());
        return b;
    }
    static Component centerVertical(Component c) {
        Box b = Box.createHorizontalBox();
        b.add(Box.createHorizontalGlue());
        b.add(c);
        b.add(Box.createHorizontalGlue());
        return b;
    }
}
