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
 * @author drm
 */
public class PumpControl extends ControlWidget {

    int pumpNumber;
    JToggleButton onButton;
    JToggleButton repairButton;

    public PumpControl(PlantControl plant, int pumpNumber) {
        super(plant);
        this.pumpNumber = pumpNumber;

        addTitle("Pump " + pumpNumber);

        onButton = addToggleButton("On");
        repairButton = addToggleButton("Repair");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source == onButton) {
                if (!plant.isWaterPumpActive(pumpNumber)) {
                    plant.turnOn(pumpNumber);
                } else {
                    plant.turnOff(pumpNumber);
                }
            } else if (source == repairButton) {
                plant.fix(Components.WATERPUMP, pumpNumber);
            }

        } catch (Exception e) {
            Logger.getLogger(PumpControl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;

            onButton.setSelected(plantControl.isWaterPumpActive(pumpNumber));
            onButton.setEnabled(plantControl.functional(Components.WATERPUMP, pumpNumber));


            repairButton.setEnabled(!plantControl.functional(Components.WATERPUMP, pumpNumber) &&
                                    !plantControl.fixUnderway());
            repairButton.setSelected(plantControl.isRepairing(Components.WATERPUMP, pumpNumber));
        }
    }
}
