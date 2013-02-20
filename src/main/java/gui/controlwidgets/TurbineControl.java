/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TurbineControl extends ControlWidget {

    JToggleButton repairButton;

    public TurbineControl(PlantControl plant) {
        super(plant);
        addTitle("Turbine");
        repairButton = addToggleButton("Repair");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source == repairButton) {
                plant.fix(Components.TURBINE);

            }
        } catch (Exception e) {
            Logger.getLogger(PumpControl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            repairButton.setEnabled(!plantControl.functional(Components.TURBINE) &&
                                    !plantControl.fixUnderway());
            repairButton.setSelected(plantControl.isRepairing(Components.TURBINE));
        }
    }
}
