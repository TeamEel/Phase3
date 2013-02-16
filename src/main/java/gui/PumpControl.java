package gui;

import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author drm
 */
public class PumpControl extends ControlWidget {
    int pumpNumber;
    JToggleButton onButton;
    JToggleButton offButton;
    JToggleButton repairButton;
    public PumpControl (PlantControl plant, int pumpNumber) {
        super(plant);
        this.pumpNumber = pumpNumber;
        Box box = Box.createVerticalBox();
        JLabel title = new JLabel("Pump " + pumpNumber);
        onButton = new JToggleButton("On");
        offButton = new JToggleButton("Off");
        repairButton = new JToggleButton("Repair");
        add(box);
        box.add(Align.left(title));
        box.add(Align.centerVertical(onButton));
        box.add(Align.centerVertical(offButton));
        box.add(Box.createVerticalGlue());
        box.add(Align.centerVertical(repairButton));
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            onButton.setSelected(plantControl.isWaterPumpActive(pumpNumber));
            offButton.setSelected(!plantControl.isWaterPumpActive(pumpNumber));
            repairButton.setEnabled(!plantControl.functional(Components.WATERPUMP, pumpNumber));
            repairButton.setSelected(plantControl.isRepairing(Components.WATERPUMP, pumpNumber));
        }
    }
}
