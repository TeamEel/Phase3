package icarus.commandfactory.test;

import icarus.commandfactory.CommandFactory;
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
import icarus.operatorsoftware.Component;
import icarus.parser.CommandWords;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.Test;

public class CommandFactoryTest {

    CommandFactory cf = new CommandFactory();
    CommandWords words = new CommandWords();

    // CommandFactoryTest class which tests the methods in the actual
    // CommandFactory class (whether they work, given a valid and invalid
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
        cf.raise(-43);
    }

    @Test
    public void testRaiseValidInput() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        cf.raise(20);
    }

    @Test(expected = InvalidRodsException.class)
    public void testLowerInvalidInput() throws InvalidRodsException, ComponentFailedException, MinimumRodsException {
        cf.lower(-43);
    }

    @Test
    public void testLowerValidInput() throws InvalidRodsException, ComponentFailedException, MinimumRodsException {
        cf.lower(20);
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
        cf.fix(Component.CONDENSER);
        cf.fix(Component.CONDENSERPUMP);
        cf.fix(Component.REACTOR);
        cf.fix(Component.TURBINE);
        cf.fix(Component.WATERPUMP);
    }

    @Test(expected = NoFixNeededException.class)
    public void testFixWaterPump() throws NumberFormatException, InvalidComponentException, FixAlreadyUnderwayException,
                                          NoFixNeededException, InvalidPumpException {
        cf.fix(Component.WATERPUMP, 1);
        cf.fix(Component.WATERPUMP, 3);
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
        assertTrue(cf.loadFromFile("test"));
    }
}
