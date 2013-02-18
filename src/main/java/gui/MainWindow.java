package gui;

import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.FailableOperatingSoftware;
import icarus.operatingsoftware.OperatingSoftware;
import icarus.operatingsoftware.PlantControl;
import icarus.operatingsoftware.PowerPlant;
import icarus.util.GameFileFilter;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author drm511
 */
public class MainWindow extends JFrame implements ActionListener, ChangeListener, Observer {
    private JFileChooser fc;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuBar menuBar;
    private JPanel controlPanel;
    private JPanel viewPanel;
    private JSlider rodSlider;
    private OperatingSoftware os;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        os = new FailableOperatingSoftware(new PowerPlant());
        viewPanel = new JPanel();
        controlPanel = new ControlPanel(os);
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        helpMenu = new JMenu();
        fc = new JFileChooser();
        GameFileFilter ff = new GameFileFilter();
        fc.addChoosableFileFilter(ff);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout viewPanelLayout = new GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
                viewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));
        viewPanelLayout.setVerticalGroup(
                viewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 399, Short.MAX_VALUE));

        

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(viewPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                              Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE,
                              GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(viewPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                              GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE,
                              GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));


        pack();
        os.addObserver(this);
        createMenus();
        setUsername();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // Ignore all exceptions here :(
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    /*
     public void stateChanged(ChangeEvent e){
     repaint();
     }
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
            
            if(e.getActionCommand().equals("New Game"))
            {
                
                os.resetPlant();
                setUsername();
                
            }
            else if(e.getActionCommand().equals("Save Game"))
            {
                int retval = fc.showSaveDialog(this);
                if(retval == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    os.saveToFile(file.getAbsolutePath());
                }
                
            }
            else if(e.getActionCommand().equals("Load Game"))
            {
                loadGame();
                
            }
            else if(e.getActionCommand().equals("Quit"))
            {
                quit();
            }
            else if(e.getActionCommand().equals("Online Help"))
            {
                java.awt.Desktop.getDesktop().browse( new URI("http://www.teameel.com/help"));
            }
            else if(e.getActionCommand().equals("About"))
            {
                AboutDialog aboutDialog = new AboutDialog(this,true);
                aboutDialog.pack();
                aboutDialog.setVisible(true);
            }
        }
        catch(Exception ex)
        {
        }
        repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }

    @Override
    public void update(Observable o, Object o1) {
        
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            
            if(plantControl.checkIfGameOver())
            {
                Object[] options = {"New Game",
                    "Load Game",
                    "Quit"};
                int n = JOptionPane.showOptionDialog(this,
                    os.getPlayerName()+ ", you have blown up the nuclear power plant. The game is now over\n"
                    + "Start new game?",
                    "Game Over",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                
                switch(n)
                {
                    case JOptionPane.YES_OPTION:
                        os.resetPlant();
                        break;
                    case JOptionPane.NO_OPTION:
                        loadGame();
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        quit();
                        break;
                        
                }
            }
        }
    }

    private void createMenus() {
        JMenuItem tmpMenuItem;
        fileMenu.setText("File");
        
        
        tmpMenuItem = new JMenuItem("New Game");
        tmpMenuItem.addActionListener(this);
        fileMenu.add(tmpMenuItem);
        
        tmpMenuItem = new JMenuItem("Load Game");
        tmpMenuItem.addActionListener(this);
        fileMenu.add(tmpMenuItem);
        
        tmpMenuItem = new JMenuItem("Save Game");
        tmpMenuItem.addActionListener(this);
        fileMenu.add(tmpMenuItem);
        
        tmpMenuItem = new JMenuItem("Quit");
        tmpMenuItem.addActionListener(this);
        fileMenu.add(tmpMenuItem);
        
        
        menuBar.add(fileMenu);
        helpMenu.setText("Help");
        
        
        tmpMenuItem = new JMenuItem("Online Help");
        tmpMenuItem.addActionListener(this);
        helpMenu.add(tmpMenuItem);
        tmpMenuItem = new JMenuItem("About");
        tmpMenuItem.addActionListener(this);
        helpMenu.add(tmpMenuItem);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private void quit() {
        this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }

    private void setUsername() {
        String userName = JOptionPane.showInputDialog(null, "Enter username: ", "Enter Username", 1);
        
        if(userName == null || userName.trim().isEmpty())
        {
            setUsername();
        }
        
        os.setPlayerName(userName);        
    }

    private void loadGame() {
        int retval = fc.showOpenDialog(this);
        if(retval == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            if(!os.loadFromFile(file.getAbsolutePath()))
            {
                JOptionPane.showMessageDialog(null,"Unable to load this file");
                System.err.println("Error Loading File");
            }
        }
    }
}
