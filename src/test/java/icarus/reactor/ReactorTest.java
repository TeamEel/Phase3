package icarus.reactor;

import icarus.exceptions.ComponentFailedException;
import icarus.exceptions.InvalidRodsException;
import icarus.exceptions.MaximumRodsException;
import icarus.exceptions.MinimumRodsException;
import icarus.exceptions.NoFixNeededException;
import icarus.reactor.Reactor;
import static org.junit.Assert.*;
import org.junit.Test;

public class ReactorTest {

    Reactor reactor = new Reactor(50, 100,
                                  200, 10000, 10000, 400,
                                  50, 100, 100,
                                  0.5, 5, true, false, 1);
    Reactor reactor2 = new Reactor(50, 200,
                                   200, 1000, 1000, 200,
                                   50, 100, 600,
                                   0.5, 5, true, false, 1);

    @Test
    public void testCheckFail() {
        assertTrue(reactor.checkFail());
    }

    @Test(expected = NoFixNeededException.class)
    public void testBeginFixIfNotBroken() throws NoFixNeededException {
        reactor.beginFix();
    }

    @Test(expected = InvalidRodsException.class)
    public void testRaiseExcessive() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        reactor.movecontrolrods(1000);

    }

    @Test(expected = InvalidRodsException.class)
    public void testLowerExcessive() throws InvalidRodsException, MinimumRodsException, ComponentFailedException {
        reactor.movecontrolrods(-1);
    }
    
    @Test
    public void testMoveRod() throws InvalidRodsException, MinimumRodsException, ComponentFailedException {
        reactor.movecontrolrods(60);
        assertEquals(reactor.getRodHeight(), 60);
        reactor.movecontrolrods(20);
        assertEquals(reactor.getRodHeight(), 20);
    }


    @Test
    public void testAddWater() {
        reactor.addWater(25);
        assertEquals(reactor.getWaterLevel(), 425, 0.001);
    }

    @Test
    public void testDrainWater() {
        double waterOut = reactor.drainWater(25);
        assertEquals(waterOut, 25, 0.001);
        assertEquals(reactor.getWaterLevel(), 375, 0.001);
    }

    @Test
    public void testCalculateActivity() {
        //(100/10 + 50) * 5 = 300
        reactor.calculateActivity();
        assertEquals(reactor.getActivity(), 300, 0.001);
    }

    @Test
    public void testCalculateTemperature() {
        //temp = 1000, activity = 200, water = 200, waterIn = 0
        reactor2.calculateTemperature();
        assertEquals(reactor2.getTemperature(), 1040, 0.001);
    }

    public void testCalculatePressure() {
        // 200 / (600 - 200) * 1000 = 500
        reactor2.calculatePressure();
        assertEquals(reactor2.getPressure(), 500, 0.001);
    }
}
