package gui.controlwidgets;

import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author drm
 */
public class StatusDisplay extends ControlWidget implements ActionListener {

    private JButton nextButton;
    private StatusTableModel status;
    JTable table;

    public StatusDisplay(PlantControl plant) {
        super(plant);
        Box vbox = Box.createVerticalBox();
        add(vbox);

        JLabel title = new JLabel("Status");


        status = new StatusTableModel();

        table = new JTable(status);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.setBackground(Color.lightGray);

        vbox.add(Align.left(title));
        vbox.add(table);


        status.rows[0] = new StatusRow("Reactor Temperature", 0.0);
        status.rows[1] = new StatusRow("Condenser Temperature", 0.0);
        status.rows[2] = new StatusRow("Reactor Water Level", 0.0);
        status.rows[3] = new StatusRow("Condenser Water Level", 0.0);
        status.rows[4] = new StatusRow("Reactor Pressure", 0.0);
        status.rows[5] = new StatusRow("Condenser Temperature", 0.0);
        status.rows[6] = new StatusRow("Power Output", 0.0);


        nextButton = new JButton("Next" + " "); //Seriously, what the hell
        nextButton.addActionListener(this);
        vbox.add(nextButton);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;

            status.rows[0].update(plantControl.temperature(Components.REACTOR));
            status.rows[1].update(plantControl.temperature(Components.CONDENSER));
            status.rows[2].update(plantControl.waterLevel(Components.REACTOR));
            status.rows[3].update(plantControl.waterLevel(Components.CONDENSER));
            status.rows[4].update(plantControl.pressure(Components.REACTOR));
            status.rows[5].update(plantControl.pressure(Components.CONDENSER));
            status.rows[6].update((double)plantControl.getPower());
            table.updateUI();

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            plant.next();
        }
    }
}