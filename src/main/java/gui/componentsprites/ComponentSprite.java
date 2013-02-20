package gui.componentsprites;

import drawing.SpriteCanvas;
import java.util.Observer;

/**
 * Common interface for all Sprite-based plant renderers
 * 
 * @author drm
 */
public interface ComponentSprite extends Observer {

    public void addToCanvas(SpriteCanvas canvas);

    public void moveTo(int x, int y);
}
