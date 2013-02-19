/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
 * @author drm
 */
public class ValveControl extends ControlWidget implements ActionListener {
    int valveNumber;
    JToggleButton openButton;
    
    public ValveControl(PlantControl plant, int valveNumber) {
        super(plant);
        this.valveNumber = valveNumber;
        Box box = Box.createVerticalBox();
        JLabel title = new JLabel("Valve " + valveNumber);
        openButton = new JToggleButton("Open");
        add(box);
        box.add(Align.left(title));
        box.add(Align.centerVertical(openButton));

        addActionListeners();
    }


    private void addActionListeners()
    {
        openButton.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        
        Object source = event.getSource();
        try
        {
            if(source==openButton)
            {
                if(!plant.isValveOpened(valveNumber))
                {
                    plant.open(valveNumber);
                }
                else
                {
                    plant.close(valveNumber);
                }
            }
            
            plant.next();
            
        }
        catch(Exception  e)
        {
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
