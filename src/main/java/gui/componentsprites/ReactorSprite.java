package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import static drawing.builders.BuildAnimationSet.buildAnimationSet;
import static drawing.builders.BuildAnimation.singleFrame;
import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.io.IOException;
import java.util.Observable;

/**
 * Render the reactor onto the sprite canvas 
 * 
 * @author drm
 */
public class ReactorSprite implements ComponentSprite {

    private Sprite sprite;

    public ReactorSprite() throws IOException {
        sprite = new Sprite(buildAnimationSet()
                                .animation(singleFrame("/fullsize/reactor.png"))
                                .animation(singleFrame("/fullsize/reactor_failed.png"))
                                .done());
    }

    @Override
    public void addToCanvas(SpriteCanvas canvas) {
        canvas.add(sprite, 1);
    }

    @Override
    public void moveTo(int x, int y) {
        sprite.moveTo(new Coordinate(x, y));
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PlantControl) {
            PlantControl plantControl = (PlantControl)o;
            if (plantControl.functional(Components.REACTOR)) {
                sprite.ensureAnimationSelected(0);
            } else {
                sprite.ensureAnimationSelected(1);
            }
        }
    }
}
