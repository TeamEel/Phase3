package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import drawing.builders.BuildSprite;
import icarus.operatingsoftware.PlantControl;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author david
 */
public class ControlRodSprite implements ComponentSprite {

    private Sprite sprite;
    private int yRange;
    private Coordinate position;
    private int yOffset;

    public ControlRodSprite(int yRange) throws IOException {
        sprite = BuildSprite.staticSprite("/scaled/control_rods.png");
        this.yRange = yRange;
        yOffset = 0;
    }

    @Override
    public void addToCanvas(SpriteCanvas canvas) {
        canvas.add(sprite, 3);
    }

    @Override
    public void moveTo(int x, int y) {
        position = new Coordinate(x, y);
        updateLocation();
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            yOffset = ((100 - plantControl.rodHeight()) * yRange) / 100;
            updateLocation();
        }
    }

    private void updateLocation() {
        sprite.moveTo(position.plus(new Coordinate(0, yOffset)));
    }
}
