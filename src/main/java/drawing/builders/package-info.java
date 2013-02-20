/**
 * This package contains helper and Builder classes to ease constructing Animations and Sprites
 * <p>
 * Constructing Sprites using the constructors and command-query interface of the Sprite and Animation
 * classes is time-consuming and verbose, in particular since these classes are based on arrays
 * and are not designed to be modified after construction (this pattern allows the Sprite/Animation classes
 * themselves to be significantly simpler, since they have very little work to do to maintain their invariants).
 * <p>
 * This package therefore contains a number of classes designed to aid in incrementally constructing
 * Animation, AnimationSet, and Sprite instances, as well as managing the loading of images from
 * .jar resources.
 * <p>
 * The core classes of this package are instances of the Expression Builder pattern
 * from the Martin Fowler & Rebecca Parsons book "Domain Specific Languages". They are based
 * primarily on method chaining, with some nested function calls.
 * <p>
 * Animations are built using the FrameListBuilder, RangeBuilder, and BuildAnimation classes.
 * FrameListBuilder and RangeBuilder are Expression Builders, which chain together calls to
 * incrementally construct an animation.
 * <p>
 * FrameListBuilder allows the programmer to simply specify, in order, a list of the image resources
 * that they would like to include in the animation. 
 * <p>
 * RangeBuilder aids common pattern of having animation image assets as a set of files with names like
 * turbine_XXX.png, where XXX is the number of the frame in the animation.
 * <p>
 * RangeBuilder allows the programmer to specify a format string, or template for the path,
 * and start and end numbers for the frame numbers. By reversing the values given for the
 * start and end numbers, it is easy to construct both forwards and backwards variants of an animation.
 * <p>
 * Both FrameListBuilder and RangeBuilder potentially suffer from the method chaining issue of knowing when
 * to terminate the method chain. This is resolved by each having two methods: stop() and loop().
 * stop() converts the images in the builder into a one-shot animation; loop() converts the images in the
 * builder into a looping animation. When written out in DSL form, this reads quite nicely: display the following
 * images then stop(); display the following images then loop().
 * <p>
 * BuildAnimation is a helper class with static methods that make it slightly easier to construct the
 * Expression Builder objects. When these methods are statically imported, the improvement in readability
 * is significant.
 * <p>
 * A similar pattern is followed for the classes AnimationSetBuilder and BuildAnimationSet. In this case,
 * AnimationSetBuilder is the Expression Builder, and BuildAnimationSet is the helper class (this naming scheme is
 * followed for all the classes in the package: XXXBuilder is an expression builder, and BuildXXX is a helper class
 * with static methods). The method-chain termination issue is harder to resolve neatly in this case; a done() method
 * is used, which does not read quite as nicely but gets the job done, and is still much nicer than the raw
 * array-manipulation code.
 * <p>
 * Finally, the BuildSprite class contains a single static helper method for creating Sprites which do not need
 * to be animated, and which therefore contain a single animation with a single frame.
 * 
 */
package drawing.builders;
