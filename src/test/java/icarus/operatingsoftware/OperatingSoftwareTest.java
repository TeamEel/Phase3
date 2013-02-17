package icarus.operatingsoftware;

import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.ComponentFailedException;
import icarus.exceptions.FixAlreadyUnderwayException;
import icarus.exceptions.InvalidComponentException;
import icarus.exceptions.InvalidPumpException;
import icarus.exceptions.InvalidRodsException;
import icarus.exceptions.InvalidValveException;
import icarus.exceptions.MaximumRodsException;
import icarus.exceptions.MinimumRodsException;
import icarus.exceptions.NoFixNeededException;
import java.io.File;
import java.util.Observer;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;

public class OperatingSoftwareTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    Observer observer;
    OperatingSoftware cf = new OperatingSoftware(new PowerPlant());

    // OperatingSoftwareTest class which tests the methods in the actual
    // OperatingSoftware class (whether they work, given a valid and invalid
    // parameters)
    @Test
    public void testSetPlayerName() {
        cf.setPlayerName("player1");
        assertEquals("player1", cf.getPlayerName());
    }

    @Test
    public void testGetPlayerName() {
        cf.setPlayerName("player1");
        assertEquals("player1", cf.getPlayerName());
    }

    @Test
    public void testCheckIfGameOver() {
        assertFalse(cf.checkIfGameOver());
    }

    @Test(expected = InvalidPumpException.class)
    public void testTurnoffInvalidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        cf.turnOff(-4);
    }

    @Test
    public void testTurnoffValidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        cf.turnOff(2);
    }

    @Test(expected = InvalidPumpException.class)
    public void testTurnonInvalidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        cf.turnOn(-4);
    }

    @Test(expected = AlreadyAtStateException.class)
    public void testTurnonValidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        cf.turnOn(2);
    }

    @Test(expected = InvalidRodsException.class)
    public void testRaiseInvalidInput() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        cf.movecontrolrods(-43);
    }

    @Test(expected = InvalidRodsException.class)
    public void testRaiseInvalidInput2() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        cf.movecontrolrods(101);
    }

    @Test
    public void testRaiseValidInput() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        cf.movecontrolrods(20);
    }

    @Test(expected = InvalidValveException.class)
    public void testOpenInvalid() throws InvalidValveException, AlreadyAtStateException {
        cf.open(-4);
    }

    @Test(expected = AlreadyAtStateException.class)
    public void testOpenValid() throws InvalidValveException, AlreadyAtStateException {
        cf.open(1);
    }

    @Test(expected = InvalidValveException.class)
    public void testCloseInvalid() throws InvalidValveException, AlreadyAtStateException {
        cf.close(-4);
    }

    @Test
    public void testCloseValid() throws InvalidValveException, AlreadyAtStateException {
        cf.close(1);
    }

    @Test(expected = NoFixNeededException.class)
    public void testFixWithoutWaterPump() throws InvalidComponentException, FixAlreadyUnderwayException,
                                                 NoFixNeededException, NoFixNeededException {
        cf.fix(Components.CONDENSER);
        cf.fix(Components.CONDENSERPUMP);
        cf.fix(Components.REACTOR);
        cf.fix(Components.TURBINE);
        cf.fix(Components.WATERPUMP);
    }

    @Test(expected = NoFixNeededException.class)
    public void testFixWaterPump() throws NumberFormatException, InvalidComponentException, FixAlreadyUnderwayException,
                                          NoFixNeededException, InvalidPumpException {
        cf.fix(Components.WATERPUMP, 1);
        cf.fix(Components.WATERPUMP, 3);
    }

    @Test
    public void testSaveToFile() {
        cf.saveToFile("test");
        File file = new File("test.ser");
        assertTrue(file.exists());
    }

    @Test
    public void testLoadFromFile() {
        cf.saveToFile("test");
        assertTrue(cf.loadFromFile("test.ser"));
    }

    @Test
    public void shouldUpdateObservers() {
        context.checking(new Expectations() {
            {
                oneOf(observer).update(cf, null);
            }
        });
        cf.addObserver(observer);
        cf.next();
    }
}
