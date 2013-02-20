package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import static drawing.builders.BuildAnimation.range;
import static drawing.builders.BuildAnimation.singleFrame;
import static drawing.builders.BuildAnimationSet.buildAnimationSet;
import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author drm
 */
public class CondenserSprite implements ComponentSprite {

    private Sprite sprite;

    public CondenserSprite() throws IOException {
        sprite = new Sprite(buildAnimationSet()
                .animation(
                range()
                .format("/scaled/condenser_2_%03d.png")
                .from(0)
                .to(39)
                .loop())
                .animation(singleFrame("/scaled/condenser_failed.png"))
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
            if (plantControl.functional(Components.CONDENSER)) {
                sprite.ensureAnimationSelected(0);
            } else {
                sprite.ensureAnimationSelected(1);
            }
        }
    }
}
