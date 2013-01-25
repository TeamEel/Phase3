package icarus.operatorsoftware.test;

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
import icarus.operatorsoftware.OperatorSoftware;
import icarus.reactor.Generator;
import icarus.reactor.Reactor;
import icarus.reactor.Turbine;
import static org.junit.Assert.*;
import org.junit.Test;

public class OperatorSoftwareTest {

    OperatorSoftware op = new OperatorSoftware();

    // OperatorSoftwareTest class tests whether the methods in the actual
    // OperatorSoftware class actually do what they are supposed to do
    @Test
    public void testSetPlayerName() {
        op.setPlayerName("player");
        assertEquals("player", op.getPlayerName());
    }

    @Test
    public void testGetPlayerName() {
        op.setPlayerName("player");
        assertEquals("player", op.getPlayerName());
    }

    @Test
    public void testRaise() throws Exception {
        int rodheight = 0;
        rodheight = op.raise(0);
        assertEquals(rodheight + 5, op.raise(5));
    }

    @Test
    public void testLower() throws Exception {
        int rodheight = 0;
        rodheight = op.lower(0);
        assertEquals(rodheight - 5, op.lower(5));
    }

    @Test
    public void testOpen() throws Exception {
        op.close(0);
        assertFalse(op.isValveOpened(0));
        op.open(0);
        assertTrue(op.isValveOpened(0));
    }

    @Test
    public void testClose() throws Exception {
        op.close(0);
        assertFalse(op.isValveOpened(0));
    }

    @Test
    public void testTurnOn() throws Exception {
        op.turnOff(0);
        assertFalse(op.isWaterPumpActive(0));
        op.turnOn(0);
        assertTrue(op.isWaterPumpActive(0));
    }

    @Test
    public void testTurnOff() throws Exception {
        op.turnOff(0);
        assertFalse(op.isWaterPumpActive(0));
    }

    @Test(expected = InvalidComponentException.class)
    public void testInvalidTemperature() throws InvalidComponentException {
        op.temperature(Component.TURBINE);
    }

    @Test
    public void testValidTemperature() throws InvalidComponentException {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getTemperature(), op.temperature(Component.REACTOR), 0);
    }

    @Test(expected = InvalidComponentException.class)
    public void testInvalidWaterLevel() throws InvalidComponentException {
        op.waterLevel(Component.TURBINE);
    }

    @Test
    public void testValidWaterLevel() throws InvalidComponentException {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getWaterLevel(), op.waterLevel(Component.REACTOR), 0);
    }

    @Test
    public void testValidFunctionalComponent() throws InvalidComponentException {
        assertTrue(op.functional(Component.REACTOR));
    }

    @Test(expected = InvalidComponentException.class)
    public void testInvalidFunctionalComponent() throws InvalidComponentException {
        op.functional(Component.WATERPUMP);
    }

    @Test
    public void testValidFunctionalWaterPump() throws InvalidComponentException {
        assertTrue(op.functional(Component.WATERPUMP, 0));
    }

    @Test(expected = InvalidComponentException.class)
    public void testInvalidFunctionalWaterPump() throws InvalidComponentException {
        op.functional(Component.CONDENSERPUMP, 0);
    }

    @Test
    public void testRodHeight() {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getRodHeight(), op.rodHeight(), 0);
    }

    @Test
    public void testValidPressure() throws InvalidComponentException {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getPressure(), op.pressure(Component.REACTOR), 0);
    }

    @Test(expected = InvalidComponentException.class)
    public void testInvalidPressure() throws InvalidComponentException {
        op.pressure(Component.TURBINE);
    }

    @Test
    public void testGetPower() {
        Turbine turbine = new Turbine();
        Generator generator = new Generator(turbine);
        assertEquals(generator.getPower(), op.getPower(), 0);

    }

    @Test(expected = InvalidComponentException.class)
    public void testFixInvalidComponent() throws InvalidComponentException, FixAlreadyUnderwayException,
                                                 NoFixNeededException {
        op.fix(Component.WATERPUMP);
    }

    @Test(expected = InvalidPumpException.class)
    public void testFixInvalidWaterPump() throws NumberFormatException, InvalidComponentException,
                                                 FixAlreadyUnderwayException, NoFixNeededException, InvalidPumpException {
        op.fix(Component.WATERPUMP, 2);
    }

    @Test
    public void testCheckFailures() {
        assertFalse(op.checkFailures());
    }

    @Test
    public void testDoFix() {
        assertFalse(op.doFix());
    }

    @Test
    public void testFixUnderway() {
        assertFalse(op.fixUnderway());
    }

    @Test
    public void testIsRepairingComponent() {
        assertFalse(op.isRepairing(Component.REACTOR));
    }

    @Test
    public void testIsRepairingWaterPump() {
        assertFalse(op.isRepairing(Component.WATERPUMP, 0));
    }

    @Test
    public void testGetGameState() {
        assertNotNull(op.getGameState());
    }
}
