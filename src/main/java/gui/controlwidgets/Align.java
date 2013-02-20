package gui.controlwidgets;

import java.awt.Component;
import javax.swing.Box;

/**
 * Helper classes to create hboxes with appropriate glue to
 * align GUI elements on the left or in the centre of a vbox
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
