package gui.controlwidgets;

import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JToggleButton;

/**
 *
 * @author James
 */
public class CondenserControl extends ControlWidget {

    JToggleButton repairButton;

    public CondenserControl(PlantControl plant) {
        super(plant);
        addTitle("Condenser");
        repairButton = addToggleButton("Repair");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source == repairButton) {
                plant.fix(Components.CONDENSER);

            }
        } catch (Exception e) {
            Logger.getLogger(PumpControl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            repairButton.setEnabled(!plantControl.functional(Components.CONDENSER) && !plantControl.fixUnderway());
            repairButton.setSelected(plantControl.isRepairing(Components.CONDENSER));
        }
    }
}
