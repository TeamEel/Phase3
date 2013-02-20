package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import static drawing.builders.BuildAnimationSet.buildAnimationSet;
import static drawing.builders.BuildAnimation.range;
import icarus.operatingsoftware.PlantControl;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author drm
 */
public class ValveSprite implements ComponentSprite {

    private Sprite sprite;
    private int valveNumber;

    public ValveSprite(int number) throws IOException {
        String pathFormat = "/fullsize/valve_%03d.png";

        valveNumber = number;
        sprite = new Sprite(buildAnimationSet()
                                .animation(range()
                                    .format(pathFormat)
                                    .from(0)
                                    .to(39)
                                    .stop())
                                .animation(range()
                                    .format(pathFormat)
                                    .from(39)
                                    .to(0)
                                    .stop())
                                .done());
    }

    @Override
    public void addToCanvas(SpriteCanvas canvas) {
        canvas.add(sprite, 2);
    }

    @Override
    public void moveTo(int x, int y) {
        sprite.moveTo(new Coordinate(x, y));
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            if (plantControl.isValveOpened(valveNumber)) {
                sprite.ensureAnimationSelected(0);
            } else {
                sprite.ensureAnimationSelected(1);
            }
        }
    }
}
