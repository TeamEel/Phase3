package icarus.reactor.test;

import static org.junit.Assert.*;
import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.ComponentFailedException;
import icarus.exceptions.NoFixNeededException;
import icarus.reactor.CondenserPump;

import org.junit.Test;

public class CondenserPumpTest {

    CondenserPump cp = new CondenserPump();

    @Test
    public void testCheckFail() {

        assertFalse(cp.checkFail());
    }

    @Test(expected = AlreadyAtStateException.class)
    public void testTurnOn() throws Exception {
        cp.turnOn();
        assertTrue(cp.isActive());
    }

    @Test
    public void testTurnOff() throws Exception {
        cp.turnOff();
        assertFalse(cp.isActive());
    }

    @Test
    public void testIsActive() {
        assertTrue(cp.isActive());
    }

    @Test(expected = NoFixNeededException.class)
    public void testBeginFixIfNotBroken() throws NoFixNeededException {
        cp.beginFix();
    }
}
