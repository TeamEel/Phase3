package gui.componentsprites;

import drawing.SpriteCanvas;
import java.util.Observer;

/**
 *
 * @author drm
 */
public interface ComponentSprite extends Observer {

    public void addToCanvas(SpriteCanvas canvas);

    public void moveTo(int x, int y);
}
