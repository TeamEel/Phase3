/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controlwidgets;

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
public class ValveControl extends ControlWidget {

    int valveNumber;
    JToggleButton openButton;

    public ValveControl(PlantControl plant, int valveNumber) {
        super(plant);
        this.valveNumber = valveNumber;
        addTitle("Valve " + valveNumber);
        openButton = addToggleButton("Open");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source == openButton) {
                if (!plant.isValveOpened(valveNumber)) {
                    plant.open(valveNumber);
                } else {
                    plant.close(valveNumber);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(PumpControl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            openButton.setSelected(plantControl.isValveOpened(valveNumber));
        }
    }
}
