package gui;

import drawing.ImageLoader;
import drawing.SpriteCanvas;
import gui.componentsprites.CondenserSprite;
import gui.componentsprites.ReactorSprite;
import gui.componentsprites.TurbineSprite;
import gui.componentsprites.ValveSprite;
import icarus.operatingsoftware.OperatingSoftware;
import java.awt.Color;
import java.io.IOException;
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

    public PlantDisplay(OperatingSoftware os) throws IOException {
        canvas = new SpriteCanvas(ImageLoader.imageResource("/scaled/plant_empty.png"));
        add(canvas);
        reactor = new ReactorSprite();
        reactor.addToCanvas(canvas);
        reactor.moveTo(3, 25);
        valve0 = new ValveSprite(0, ValveSprite.Orientation.NORMAL);
        valve0.addToCanvas(canvas);
        valve0.moveTo(275, 63);
        valve1 = new ValveSprite(1, ValveSprite.Orientation.ROTATED);
        valve1.addToCanvas(canvas);
        valve1.moveTo(400, 63);
        turbine = new TurbineSprite();
        turbine.addToCanvas(canvas);
        turbine.moveTo(360, 28);
        condenser = new CondenserSprite();
        condenser.addToCanvas(canvas);
        condenser.moveTo(423, 190);
        setPreferredSize(canvas.getPreferredSize());
        setBorder(BorderFactory.createLineBorder(Color.black));
        canvas.setFrameInterval(10);
        canvas.start();
        os.addObserver(valve0);
        os.addObserver(valve1);
    }

    public SpriteCanvas canvas() {
        return canvas;
    }
}
