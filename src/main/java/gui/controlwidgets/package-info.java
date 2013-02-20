/**
 * This package provides widgets to control---and display the status
 * of---each component of the power plant.
 * <p>
 * The classes in this package are instances of the Observer pattern (GoF); they can also be
 * seen as instances of the Proxy pattern (GoF).
 * <p>
 * All of the classes in this package are entirely stateless; their state is determined by
 * Observer updates from the OperatingSoftware.
 * <p>
 * All of the widgets in this package derive from the abstract class ControlWidget, which
 * provides a common interface, and holds the reference to the PlantControl interface
 * instance which is needed for the controls on each widget.
 * <p>
 * The XXXControl classes in this package map obviously to plant components, and will
 * not be discussed further here.
 * <p>
 * The Align class provides static methods to ease vbox/hbox based GUI layouts. While
 * this is not the most powerful GUI layout manager available, the lack of verbosity, combined
 * with the good mapping to existing knowledge of typesetting software which uses
 * a similar layout scheme (e.g. TeX) and good resize handling make this a good layout management
 * option for layouts at this level of complexity.
 * <p>
 * The StatusDisplay class is responsible for displaying numerical data about the state
 * of the plant - component temperature/pressure, water levels, energy generated, etc.
 * <p>
 * The StatusDisplay widget also contains the "Next" button used for stepping the simulation.
 * <p>
 * The StatusLabel, StatusRow, and StatusTableModel classes are utility classes used by
 * StatusDisplay; they are fairly trivial examples of Java Swing TableModels. The
 * reader is directed to the Java table documentation at:
 * <a href="http://docs.oracle.com/javase/tutorial/uiswing/components/table.html">
 * http://docs.oracle.com/javase/tutorial/uiswing/components/table.html</a>
 */
package gui.controlwidgets;
