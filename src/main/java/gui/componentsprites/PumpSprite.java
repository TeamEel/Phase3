package gui.componentsprites;

import drawing.Coordinate;
import drawing.Sprite;
import drawing.SpriteCanvas;
import static drawing.builders.BuildAnimation.*;
import static drawing.builders.BuildAnimationSet.buildAnimationSet;
import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PlantControl;
import java.io.IOException;
import java.util.Observable;

/**
 * Render and animate a pump onto the sprite canvas
 * @author david
 */
public class PumpSprite implements ComponentSprite {

    private Sprite sprite;
    private int pumpNumber;

    public PumpSprite(int number) throws IOException {
        pumpNumber = number;
        sprite = new Sprite(buildAnimationSet()
                                .animation(singleFrame("/scaled/pump_000.png"))
                                .animation(
                                    range()
                                        .format("/scaled/pump_%03d.png")
                                        .from(0)
                                        .to(121)
                                        .loop())
                                .animation(singleFrame("/scaled/pump_failed.png"))
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
            if (plantControl.functional(Components.WATERPUMP, pumpNumber) &&
                plantControl.isWaterPumpActive(pumpNumber)) {
                sprite.ensureAnimationSelected(1);
            } else if (!plantControl.functional(Components.WATERPUMP, pumpNumber)) {
                sprite.ensureAnimationSelected(2);
            } else {
                sprite.ensureAnimationSelected(0);
            }
        }
    }
}
