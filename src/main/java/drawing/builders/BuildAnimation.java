package drawing.builders;

/**
 *
 * @author drm
 */
public class BuildAnimation {
    public static FrameListBuilder frames() {
        return new FrameListBuilder();
    }
    
    public static RangeBuilder range() {
        return new RangeBuilder();
    }
}
