package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


/**
 *
 * @author drm511
 */
public class MainWindow extends JFrame implements ActionListener, ChangeListener {

    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuBar menuBar;
    private JPanel controlPanel;
    private JPanel viewPanel;
    private JSlider rodSlider;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        viewPanel = new JPanel();
        controlPanel = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        helpMenu = new JMenu();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout viewPanelLayout = new GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
                viewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));
        viewPanelLayout.setVerticalGroup(
                viewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 399, Short.MAX_VALUE));

        GroupLayout jPanel2Layout = new GroupLayout(controlPanel);
        controlPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 912, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 121, Short.MAX_VALUE));

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        helpMenu.setText("Help");
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
              
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
            // TODO: Decide what to do with exceptions
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
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
        repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
