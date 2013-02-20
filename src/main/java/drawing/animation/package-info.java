/**
 * This package contains classes for managing animations and sets of animations.
 * <p>
 * An animation is a sequence of frames, each of which is a separate image.
 * Depending on the concrete Animation instance used, it is possible to create
 * both LoopingAnimations (which, when advanced beyond the end of the sequence of
 * frames, restart at the beginning) and OneShotAnimations (which remain on the final
 * image no matter how many times they are advanced).
 * <p>
 * All kinds of animation derive from the Animation class. They are constructed from
 * an array of awt Image objects. Sequential entries in the array are considered to
 * be sequential frames.
 * <p>
 * Animations are stepped frame-by-frame using the advance() method, and can paint themselves
 * onto an awt Graphics object using their paint() methods. Typically, however, both of these
 * functions are managed by their owning Sprite/SpriteCanvas.
 * <p>
 * An AnimationSet is a set of animations (startlingly). It is similarly constructed
 * from an array, this time of Animation objects. While the individual Images that
 * make up an Animation are sequential frames of an animation, the individual Animations
 * within an AnimationSet are relatively unrelated.
 * <p>
 * They typically represent the different animations for the different states that
 * a Sprite can be in - for example, a Sprite could have one animation for running forwards,
 * another for running backwards, and a third static image for the Sprite in a 'failed' state.
 * <p>
 * Animations within an AnimationSet are selected by their 0-based integer index, using
 * either the select() or ensureSelected() calls. These calls differ primarily in whether
 * they reset the currently-playing animation - select() does, ensureSelected() does not.
 * Therefore ensureSelected() can be called very frequently from GUI controls, whereas select()
 * is more likely to be called from more tightly controlled code, timers, or from ensureSelected()
 * itself.
 * <p>
 * While the animations in an AnimationSet may be entirely unrelated, it is important to note that
 * they will typically be drawn as different states of the same entity; therefore, typically all of
 * the animations are variations on a theme, and share the same image size and basic layout.
 * 
 */
package drawing.animation;
