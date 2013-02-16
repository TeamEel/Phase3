/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

/**
 *
 * @author drm
 */
public class ReactorControl extends ControlWidget {
    JSlider controlRodPosition;
    JToggleButton repairButton;
    
    public ReactorControl (PlantControl plant) {
        super(plant);
        Box box = Box.createVerticalBox();        
        JLabel title = new JLabel("Reactor");
        controlRodPosition = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        repairButton = new JToggleButton("Repair");        
        add(box);
        box.add(Align.left(title));
        box.add(Align.centerVertical(controlRodPosition));
        box.add(Box.createVerticalGlue());
        box.add(Align.centerVertical(repairButton));
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            repairButton.setEnabled(!plantControl.functional(Components.REACTOR));
            repairButton.setSelected(plantControl.isRepairing(Components.REACTOR));
            controlRodPosition.setValue(plantControl.rodHeight());
        }
    }
}
