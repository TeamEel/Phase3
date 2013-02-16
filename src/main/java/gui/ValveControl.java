/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import icarus.operatingsoftware.PlantControl;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author drm
 */
public class ValveControl extends ControlWidget {
    int valveNumber;
    JToggleButton openButton;
    JToggleButton closeButton;
    
    public ValveControl(PlantControl plant, int valveNumber) {
        super(plant);
        this.valveNumber = valveNumber;
        Box box = Box.createVerticalBox();
        JLabel title = new JLabel("Valve " + valveNumber);
        openButton = new JToggleButton("Open");
        closeButton = new JToggleButton("Close");
        add(box);
        box.add(Align.left(title));
        box.add(Align.centerVertical(openButton));
        box.add(Align.centerVertical(closeButton));
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            openButton.setSelected(plantControl.isValveOpened(valveNumber));
            closeButton.setSelected(!plantControl.isValveOpened(valveNumber));
        }
    }
}
