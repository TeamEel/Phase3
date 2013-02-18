package drawing;

/**
 *
 * @author drm
 */
public class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordinate plus(Coordinate other) {
        return new Coordinate(x + other.x, y + other.y);
    }
}
