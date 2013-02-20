/**
 * This package provides a Sprite Canvas, Sprites, and sprite management functions.
 * <p>
 * The SpriteCanvas is a Java Swing widget which provides a surface on which to draw
 * moving, animated sprites. It holds a background image over which the Sprites are
 * drawn.
 * <p>
 * Sprites are drawn onto the canvas according to their Z-order. This allows sprites
 * to be stacked on top of each other in a systematic way, with some sprites being
 * closer to the foreground than others. Transparency in sprites is also handled correctly
 * (natively by Java).
 * <p>
 * The Sprite class encapsulates a set of animations which have a position. A Sprite
 * can be animated (with different sets of animations selected according to the state of the
 * object which the sprite is representing), drawn onto an awt Graphics object, and moved
 * according to both relative and absolute coordinates.
 * <p>
 * The critical classes for clients of this package are SpriteCanvas and Sprite; Coordinate will
 * also be needed but is a trivial container.
 * <p>
 * SpriteSet is a first-class collection of sprites, for the convenience of SpriteCanvas.
 * ZSprite is a Sprite wrapper which associate a Z-value with a sprite, and makes it easy
 * for SpriteSet to order sprites by Z-value. ImageLoader is a helper class that
 * loads images stored in .jar resources into awt Image objects. None of these classes need concern
 * clients of the package; none of them are sufficiently complex to require more detailed exposition here.
 * 
 */
package drawing;
