package icarus.operatingsoftware;

import icarus.exceptions.AlreadyAtStateException;
import icarus.exceptions.ComponentFailedException;
import icarus.exceptions.FixAlreadyUnderwayException;
import icarus.exceptions.InvalidComponentException;
import icarus.exceptions.InvalidPumpException;
import icarus.exceptions.InvalidRodsException;
import icarus.exceptions.InvalidValveException;
import icarus.exceptions.MaximumRodsException;
import icarus.exceptions.NoFixNeededException;
import java.io.File;
import java.util.Observer;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class OperatingSoftwareTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    Observer observer;
    @Mock
    ProbabilitySource probability;
    OperatingSoftware os;

    @Before
    public void setup() {
        os = new OperatingSoftware(new PowerPlant(), probability);
    }

    // OperatingSoftwareTest class which tests the methods in the actual
    // OperatingSoftware class (whether they work, given a valid and invalid
    // parameters)
    @Test
    public void testSetPlayerName() {
        os.setPlayerName("player1");
        assertEquals("player1", os.getPlayerName());
    }

    @Test
    public void testGetPlayerName() {
        os.setPlayerName("player1");
        assertEquals("player1", os.getPlayerName());
    }

    @Test
    public void testCheckIfGameOver() {
        assertFalse(os.checkIfGameOver());
    }

    @Test(expected = InvalidPumpException.class)
    public void testTurnoffInvalidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.turnOff(-4);
    }

    @Test
    public void testTurnoffValidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.turnOff(2);
    }

    @Test(expected = InvalidPumpException.class)
    public void testTurnonInvalidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.turnOn(-4);
    }

    @Test(expected = AlreadyAtStateException.class)
    public void testTurnonValidInput() throws InvalidPumpException, AlreadyAtStateException, ComponentFailedException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.turnOn(2);
    }

    @Test(expected = InvalidRodsException.class)
    public void testRaiseInvalidInput() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.movecontrolrods(-43);
    }

    @Test(expected = InvalidRodsException.class)
    public void testRaiseInvalidInput2() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.movecontrolrods(101);
    }

    @Test
    public void testRaiseValidInput() throws InvalidRodsException, MaximumRodsException, ComponentFailedException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.movecontrolrods(20);
    }

    @Test(expected = InvalidValveException.class)
    public void testOpenInvalid() throws InvalidValveException, AlreadyAtStateException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.open(-4);
    }

    @Test(expected = AlreadyAtStateException.class)
    public void testOpenValid() throws InvalidValveException, AlreadyAtStateException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.open(1);
    }

    @Test(expected = InvalidValveException.class)
    public void testCloseInvalid() throws InvalidValveException, AlreadyAtStateException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.close(-4);
    }

    @Test
    public void testCloseValid() throws InvalidValveException, AlreadyAtStateException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.close(1);
    }

    @Test(expected = NoFixNeededException.class)
    public void testFixWithoutWaterPump() throws InvalidComponentException, FixAlreadyUnderwayException,
                                                 NoFixNeededException, NoFixNeededException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.fix(Components.CONDENSER);
        os.fix(Components.CONDENSERPUMP);
        os.fix(Components.REACTOR);
        os.fix(Components.TURBINE);
        os.fix(Components.WATERPUMP);
    }

    @Test(expected = NoFixNeededException.class)
    public void testFixWaterPump() throws NumberFormatException, InvalidComponentException, FixAlreadyUnderwayException,
                                          NoFixNeededException, InvalidPumpException {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.fix(Components.WATERPUMP, 1);
        os.fix(Components.WATERPUMP, 3);
    }

    @Test
    public void testSaveToFile() {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.saveToFile("test");
        File file = new File("test.ser");
        assertTrue(file.exists());
    }

    @Test
    public void testLoadFromFile() {
        context.checking(new Expectations() {
            {
                allowing(probability).trueOnceIn(8);
                will(returnValue(false));
            }
        });
        os.saveToFile("test");
        assertTrue(os.loadFromFile("test.ser"));
    }

    @Test
    public void shouldUpdateObservers() {        
        context.checking(new Expectations() {
            {
                oneOf(observer).update(os, null);
            }
        });
        os.addObserver(observer);
        os.next();
    }
}
