package gui;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author drm
 */
public class StatusLabel extends JPanel {
    private JLabel dataLabel;
    public StatusLabel(String name, String initialText) {
        Box box = Box.createHorizontalBox();
        add(box);
        box.add(new JLabel(name + ":"));
        box.add(Box.createHorizontalGlue());
        box.add(dataLabel = new JLabel(initialText));
    }
    
    public void setData(String text) {
        dataLabel.setText(text);
    }
}
