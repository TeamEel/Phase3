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
public class TurbineSprite implements ComponentSprite {
    Sprite sprite;

    public TurbineSprite() throws IOException {
        sprite = new Sprite(singleAnimation(
                            range()
                            .format("/fullsize/turbine_%03d.png")
                            .from(0).to(121)
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
        //do nothing;
    }
    
    
}
