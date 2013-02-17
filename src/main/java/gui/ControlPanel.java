package gui;

import icarus.operatingsoftware.OperatingSoftware;
import icarus.operatingsoftware.Plant;
import icarus.operatingsoftware.PlantControl;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 *
 * @author drm
 */
public class ControlPanel extends JPanel implements Observer {
        ArrayList<ControlWidget> controlWidgets;
    
    public ControlPanel(OperatingSoftware plant) {
        controlWidgets = new ArrayList<ControlWidget>();
        controlWidgets.add(new ReactorControl(plant));
        controlWidgets.add(new PumpControl(plant, 0));
        controlWidgets.add(new PumpControl(plant, 1));
        controlWidgets.add(new PumpControl(plant, 2));
        controlWidgets.add(new ValveControl(plant, 0));
        controlWidgets.add(new ValveControl(plant, 1));
        controlWidgets.add(new TurbineControl(plant));
        controlWidgets.add(new CondenserControl(plant));
        controlWidgets.add(new StatusDisplay(plant));
        Box box = Box.createHorizontalBox();
        add(box);
        for (ControlWidget cw : controlWidgets) {
            box.add(cw);
            plant.addObserver(cw);
        }
        setBorder(BorderFactory.createLineBorder(Color.black));
        //propagate changes to children
        update(plant, null);
    }

    @Override
    public final void update(Observable o, Object o1) {
        for (ControlWidget cw : controlWidgets) {
            cw.update(o, o1);
        }
    }

}
