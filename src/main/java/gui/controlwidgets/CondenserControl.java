package gui.controlwidgets;

import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author James
 */
public class CondenserControl extends ControlWidget implements ActionListener {

    JToggleButton repairButton;

    public CondenserControl(PlantControl plant) {
        super(plant);
        Box box = Box.createVerticalBox();
        JLabel title = new JLabel("Condenser");
        repairButton = new JToggleButton("Repair");
        add(box);
        box.add(Align.left(title));
        box.add(Box.createVerticalGlue());
        box.add(Align.centerVertical(repairButton));

        addActionListeners();
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

    private void addActionListeners() {
        repairButton.addActionListener(this);
    }
}
