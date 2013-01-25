package icarus.reactor.test;

import static org.junit.Assert.*;
import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.InvalidValveException;
import icarus.exceptions.NoFixNeededException;
import icarus.reactor.Condenser;
import icarus.reactor.CondenserPump;
import icarus.reactor.Reactor;
import icarus.reactor.SteamValve;
import icarus.reactor.Turbine;
import icarus.reactor.WaterPump;

import org.junit.Test;

public class SteamValveTest {

    CondenserPump condeserPump = new CondenserPump();
    Reactor reactor = new Reactor();
    Condenser condenser = new Condenser(condeserPump);
    WaterPump waterPump = new WaterPump(reactor, condenser);
    Turbine turbine = new Turbine();
    SteamValve steamValve = new SteamValve(reactor, turbine, condenser);

    @Test(expected = AlreadyAtStateException.class)
    public void testOpen() throws Exception {
        steamValve.open(1);
        assertTrue(steamValve.isOpen(1));
    }

    @Test
    public void testClose() throws Exception {
        steamValve.close(0);
        assertFalse(steamValve.isOpen(0));
    }

    @Test
    public void testEqualise() {
        //inpress = 900, outpress = 400
        //insteam = 300 , outsteam = 300 
        //pressdiff = 500
        //310 * 250 / 500 = 155
        //insteam = 145, outsteam = 455
        steamValve.equalise();
        assertEquals(condenser.getSteamLevel(), 455, 0.001);
        assertEquals(reactor.getSteamLevel(), 145, 0.001);

    }
}
