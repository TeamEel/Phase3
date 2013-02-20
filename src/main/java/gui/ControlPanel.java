package gui;

import gui.controlwidgets.ControlWidget;
import gui.controlwidgets.PumpControl;
import gui.controlwidgets.CondenserControl;
import gui.controlwidgets.StatusDisplay;
import gui.controlwidgets.ValveControl;
import gui.controlwidgets.TurbineControl;
import gui.controlwidgets.ReactorControl;
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
 * Container for control/status widgets
 *
 * @author drm
 */
public class ControlPanel extends JPanel {

    OperatingSoftware plant;
    ArrayList<ControlWidget> controlWidgets;
    Box hbox;

    public ControlPanel(OperatingSoftware plant) {
        this.plant = plant;
        this.controlWidgets = new ArrayList<ControlWidget>();
        this.hbox = Box.createHorizontalBox();
        add(hbox);
        
        addControlWidget(new ReactorControl(plant));
        addControlWidget(new PumpControl(plant, 0));
        addControlWidget(new PumpControl(plant, 1));
        addControlWidget(new PumpControl(plant, 2));
        addControlWidget(new ValveControl(plant, 0));
        addControlWidget(new ValveControl(plant, 1));
        addControlWidget(new TurbineControl(plant));
        addControlWidget(new CondenserControl(plant));
        addControlWidget(new StatusDisplay(plant));
    }

    private void addControlWidget(ControlWidget cw) {
        controlWidgets.add(cw);
        plant.addObserver(cw);
        hbox.add(cw);
    }
}
