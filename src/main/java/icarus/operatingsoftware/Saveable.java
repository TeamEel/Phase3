/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icarus.operatingsoftware;

import icarus.util.SaveState;

/**
 *
 * @author drm511
 */
public interface Saveable {

    public SaveState getGameState();
}
