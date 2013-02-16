package gui;

import icarus.operatingsoftware.PlantControl;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author drm
 */
public abstract class ControlWidget extends JPanel implements Observer {
    PlantControl plant;
    public ControlWidget(PlantControl plantControl) {
        this.plant = plantControl;
    }
}
