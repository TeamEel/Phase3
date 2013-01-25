package icarus.reactor.test;

import icarus.exceptions.NoFixNeededException;
import icarus.reactor.Condenser;
import icarus.reactor.CondenserPump;
import static org.junit.Assert.*;
import org.junit.Test;

public class CondenserTest {

    CondenserPump pump = new CondenserPump();
    Condenser condenser = new Condenser(100, pump, 50, 20000, 20000, 600, 50, 90, 50, 0.5, 1, true, false, 5);
    CondenserPump pump2 = new CondenserPump();
    Condenser condenser2 = new Condenser(100, pump, 50, 1000, 1000, 200, 50, 90, 50, 0.5, 1, true, false, 5);

    @Test(expected = NoFixNeededException.class)
    public void testBeginFixIfNotBroken() throws NoFixNeededException {
        condenser.beginFix();
        condenser.getWaterLevel();
    }

    @Test
    public void testCheckFail() {
        assertTrue(condenser.checkFail());
    }

    @Test
    public void testAddSteam() {
        condenser2.addSteam(30, 1000);
        assertEquals(condenser2.getSteamLevel(), 80);
    }

    @Test
    public void testCondense() {
        condenser2.condense();
        assertEquals(condenser2.getSteamLevel(), 20);
        assertEquals(condenser2.getWaterLevel(), 230, 0.001);
    }

    @Test
    public void testOutOfBoundsCondense() {
        condenser.condense();
        assertEquals(condenser.getSteamLevel(), 50);
        assertEquals(condenser.getWaterLevel(), 600, 0.001);
    }

    @Test
    public void testCalculateTemperature() {
        //steamlevel = 50
        //temperature = 1000
        //condensation = 0
        //steamIn = 100
        //steamInTemperature = 0
        condenser2.addSteam(30, 2000);
        //steamlevel = 80
        //steamIn = 30
        //steamInTemperature = 2000
        condenser2.calculateTemperature();
        //50 * 1000 + 30 * 2000 / 80 = 1375
        assertEquals(condenser2.getTemperature(), 1375, 0.001);

    }
}
