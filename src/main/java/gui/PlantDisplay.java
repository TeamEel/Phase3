package gui;

import drawing.ImageLoader;
import drawing.SpriteCanvas;
import gui.componentsprites.ComponentSprite;
import gui.componentsprites.CondenserSprite;
import gui.componentsprites.ControlRodSprite;
import gui.componentsprites.CoolingTowerSprite;
import gui.componentsprites.PumpSprite;
import gui.componentsprites.ReactorSprite;
import gui.componentsprites.TurbineSprite;
import gui.componentsprites.ValveSprite;
import icarus.operatingsoftware.OperatingSoftware;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author drm
 */
public class PlantDisplay extends JPanel {

    private SpriteCanvas canvas;
    private ReactorSprite reactor;
    private ValveSprite valve0;
    private ValveSprite valve1;
    private TurbineSprite turbine;
    private CondenserSprite condenser;
    private ControlRodSprite controlRods;
    private PumpSprite pump0;
    private OperatingSoftware operatingSoftware;

    public PlantDisplay(OperatingSoftware os) throws IOException {
        operatingSoftware = os;
        canvas = new SpriteCanvas(ImageLoader.imageResource("/scaled/plant2_empty.png"));
        
        canvas.setFrameInterval(10);
        canvas.setScaleFactor(0.8);
                add(canvas);
        canvas.start();

        addComponentSprite(new ReactorSprite(), 3, 30);
        addComponentSprite(new ValveSprite(0), 315, 28);
        addComponentSprite(new ValveSprite(1), 392, 130);
        addComponentSprite(new TurbineSprite(), 380, 35);
        addComponentSprite(new CondenserSprite(), 425, 180);
        addComponentSprite(new ControlRodSprite(100), 53, 160);
        addComponentSprite(new PumpSprite(0), 295, 342);
        addComponentSprite(new PumpSprite(1), 295, 430);
        addComponentSprite(new PumpSprite(2), 590, 315);
        addComponentSprite(new CoolingTowerSprite(), 675, 185);
    }

    public SpriteCanvas canvas() {
        return canvas;
    }    

    private void addComponentSprite(ComponentSprite sprite,
                                    int x, int y) {
        sprite.addToCanvas(canvas);
        sprite.moveTo(x, y);
        operatingSoftware.addObserver(sprite);
    }
}
