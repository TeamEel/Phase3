package gui;

import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author drm
 */
public class StatusDisplay extends ControlWidget implements ActionListener {

    private StatusLabel reactorTemperature;
    private StatusLabel condenserTemperature;
    private StatusLabel reactorWaterLevel;
    private StatusLabel condenserWaterLevel;
    private StatusLabel reactorPressure;
    private StatusLabel condenserPressure;
    private StatusLabel power;
    private JButton nextButton;

    public StatusDisplay(PlantControl plant) {
        super(plant);
        Box vbox = Box.createVerticalBox();
        add(vbox);

        JLabel title = new JLabel("Status");
        vbox.add(Align.left(title));
        vbox.add(Box.createVerticalStrut(6));

        reactorTemperature = new StatusLabel("Reactor Temperature", "");
        vbox.add(Align.left(reactorTemperature));

        condenserTemperature = new StatusLabel("Condenser Temperature", "");
        vbox.add(Align.left(condenserTemperature));

        reactorWaterLevel = new StatusLabel("Reactor Water Level", "");
        vbox.add(Align.left(reactorWaterLevel));
        
        condenserWaterLevel = new StatusLabel("Condenser Water Level", "");
        vbox.add(Align.left(condenserWaterLevel));

        reactorPressure = new StatusLabel("Reactor Pressure", "");
        vbox.add(Align.left(reactorPressure));

        condenserPressure = new StatusLabel("Condenser Pressure", "");
        vbox.add(Align.left(condenserPressure));

        power = new StatusLabel("Power", "");
        vbox.add(Align.left(power));
        
        nextButton = new JButton("Next" + " "); //Seriously, what the hell
        nextButton.addActionListener(this);
        vbox.add(nextButton);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            reactorTemperature.setData(plantControl.temperature(Components.REACTOR) + "");
            condenserTemperature.setData(plantControl.temperature(Components.CONDENSER) + "");

            reactorWaterLevel.setData(plantControl.waterLevel(Components.REACTOR) + "");
            condenserWaterLevel.setData(plantControl.waterLevel(Components.CONDENSER) + "");

            reactorPressure.setData(plantControl.pressure(Components.REACTOR) + "");
            condenserPressure.setData(plantControl.pressure(Components.CONDENSER) + "");

            power.setData(plantControl.getPower() + "");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            plant.next();
        }
    }
}
