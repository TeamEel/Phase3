package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import java.util.Observable;

import static drawing.builders.BuildAnimation.range;
import static drawing.builders.BuildAnimationSet.singleAnimation;
import java.io.IOException;

/**
 *
 * @author david
 */
public class CoolingTowerSprite implements ComponentSprite {

    private Sprite sprite;

    public CoolingTowerSprite() throws IOException {
        sprite = new Sprite(singleAnimation(
                                range()
                                    .format("/scaled/cooling_tower_2_%03d.png")
                                    .from(0)
                                    .to(39)
                                    .loop()));
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
        // do nothing
    }
}
