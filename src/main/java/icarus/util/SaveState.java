/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icarus.util;

import icarus.operatingsoftware.Player;
import icarus.reactor.*;
import java.io.Serializable;

/**
 *
 * @author drm
 */
public class SaveState implements Serializable {

    public Reactor reactor;
    public Condenser condenser;
    public Turbine turbine;
    public Generator generator;
    public WaterPump[] waterPumps;
    public SteamValve steamValve;
    public CondenserPump condenserPump;
    public Player player;
}
