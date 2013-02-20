/**
 * This package provides Sprite-based display classes for each of the
 * components of the Power Plant.
 * <p>
 * The classes in this package can be seen as instances of the Proxy and
 * Observer patterns (GoF). Each one owns a sprite, which they
 * manipulate according to the reported status of the reactor.
 * <p>
 * The ValveSprite animates into position but is otherwise static.
 * <p>
 * The PumpSprite animates when turned on, and changes to a different
 * static image when the relevant pump has failed.
 * <p>
 * The CondenserSprite animates continuously, and changes to a different
 * static image when the condenser fails.
 * <p>
 * The ReactorSprite is a static image, and changes to a different
 * static image when the reactor fails.
 * <p>
 * The TurbineSprite animates continuously, and changes to a different
 * static image when the turbine fails.
 * <p>
 * The ControlRodSprite is a static image, which is moved around the
 * canvas when the control rods move.
 * <p>
 * The CoolingTowerSprite does not represent a controllable component
 * of the reactor, but is a sprite so that it can be animated.
 * <p>
 * All of these classes implement the ComponentSprite interface,
 * which allows the PlantDisplay to handle them in a simple and consistent
 * manner.
 * 
 */
package gui.componentsprites;
