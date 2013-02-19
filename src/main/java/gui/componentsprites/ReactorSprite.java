package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import static drawing.builders.BuildSprite.staticSprite;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author drm
 */
public class ReactorSprite implements ComponentSprite {

    private Sprite sprite;

    public ReactorSprite() throws IOException {
        sprite = staticSprite("/fullsize/reactor.png");
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
