/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icarus.reactor;

import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.ComponentFailedException;
import icarus.exceptions.NoFixNeededException;

/**
 *
 * @author James
 */
public interface Pump {
    void turnOn() throws AlreadyAtStateException, ComponentFailedException;
    void turnOff() throws AlreadyAtStateException, ComponentFailedException;
    boolean isActive();
    boolean getFunctional();
    boolean getRepairing();
    void beginFix() throws NoFixNeededException;
}
