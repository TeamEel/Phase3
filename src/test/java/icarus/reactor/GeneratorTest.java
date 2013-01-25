package icarus.reactor;

import icarus.reactor.Generator;
import icarus.reactor.Turbine;
import static org.junit.Assert.*;
import org.junit.Test;

public class GeneratorTest {

    Turbine tb = new Turbine();
    Generator gn = new Generator(tb);

    @Test
    public void testCalculatePower() {
        tb.calculateRPM(0);
        gn.calculatePower();
        assertEquals(gn.getPower(), 0);
    }
}
