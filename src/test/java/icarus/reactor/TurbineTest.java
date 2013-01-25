package icarus.reactor;

import icarus.exceptions.NoFixNeededException;
import icarus.reactor.Turbine;
import static org.junit.Assert.*;
import org.junit.Test;

public class TurbineTest {

    Turbine turbine = new Turbine();

    @Test(expected = NoFixNeededException.class)
    public void testBeginFixIfNotBroken() throws NoFixNeededException {
        turbine.beginFix();
    }

    @Test
    public void testCalculateRPM() {
        turbine.calculateRPM(30);
        assertEquals(turbine.getRPM(), 300);
    }

    @Test
    public void testCheckFail() {
        turbine.calculateRPM(40000);
        assertTrue(turbine.checkFail());
    }
}
