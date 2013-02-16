package gui;

import icarus.exceptions.InvalidComponentException;
import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drm
 */
public class StatusDisplay extends ControlWidget {

    StatusLabel temperatureLabel;

    public StatusDisplay(PlantControl plant) {
        super(plant);
        temperatureLabel = new StatusLabel("Temperature", "");
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            //temperatureLabel.setData(String.valueOf(plantControl.temperature(Components.REACTOR)));
        }
    }
}
