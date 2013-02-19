package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import static drawing.builders.BuildAnimation.range;
import static drawing.builders.BuildAnimationSet.singleAnimation;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author drm
 */
public class CondenserSprite implements ComponentSprite {

    private Sprite sprite;

    public CondenserSprite() throws IOException {
        sprite = new Sprite(singleAnimation(
                range()
                .format("/scaled/condenser_%03d.png")
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
        //do nothing
    }
}
