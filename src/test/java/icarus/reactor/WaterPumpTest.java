package icarus.reactor;

import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.NoFixNeededException;
import icarus.reactor.Condenser;
import icarus.reactor.CondenserPump;
import icarus.reactor.Reactor;
import icarus.reactor.WaterPump;
import static org.junit.Assert.*;
import org.junit.Test;

public class WaterPumpTest {

    CondenserPump condeserPump = new CondenserPump();
    Reactor reactor = new Reactor();
    Condenser condenser = new Condenser(condeserPump);
    WaterPump waterPump = new WaterPump(condenser, reactor);

    @Test(expected = NoFixNeededException.class)
    public void testBeginFixIfNotBroken() throws NoFixNeededException {
        waterPump.beginFix();
    }

    @Test
    public void testCheckFail() {
        assertFalse(waterPump.checkFail());
    }

    @Test(expected = AlreadyAtStateException.class)
    public void testTurnOn() throws Exception {
        waterPump.turnOn();
    }

    @Test
    public void testTurnOff() throws Exception {
        waterPump.turnOff();
        assertFalse(waterPump.isActive());
    }

    @Test
    public void testPumpWater() {
        waterPump.pumpWater();
        assertEquals(reactor.getWaterLevel(), 315, 0.001);
        assertEquals(condenser.getWaterLevel(), 265, 0.001);
    }

    @Test
    public void testExtremePumpWater() {
        condenser.drainWater(280);
        waterPump.pumpWater();
        assertEquals(reactor.getWaterLevel(), 300, 0.001);
        assertEquals(condenser.getWaterLevel(), 0, 0.001);
    }
}
