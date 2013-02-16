package icarus.operatingsoftware;

import icarus.exceptions.FixAlreadyUnderwayException;
import icarus.exceptions.InvalidComponentException;
import icarus.exceptions.InvalidPumpException;
import icarus.exceptions.NoFixNeededException;
import icarus.operatingsoftware.Components;
import icarus.operatingsoftware.PowerPlant;
import icarus.reactor.Generator;
import icarus.reactor.Reactor;
import icarus.reactor.Turbine;
import static org.junit.Assert.*;
import org.junit.Test;

public class PowerPlantTest {

    PowerPlant op = new PowerPlant();

    // PowerPlantTest class tests whether the methods in the actual
    // PowerPlant class actually do what they are supposed to do
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
    public void testSetControlRodPosition() throws Exception {
        op.movecontrolrods(15);
        assertEquals(15,op.rodHeight());
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

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTemperature() throws InvalidComponentException {
        op.temperature(Components.TURBINE);
    }

    @Test
    public void testValidTemperature() throws InvalidComponentException {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getTemperature(), op.temperature(Components.REACTOR), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWaterLevel() throws InvalidComponentException {
        op.waterLevel(Components.TURBINE);
    }

    @Test
    public void testValidWaterLevel() throws InvalidComponentException {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getWaterLevel(), op.waterLevel(Components.REACTOR), 0);
    }

    @Test
    public void testValidFunctionalComponent() throws InvalidComponentException {
        assertTrue(op.functional(Components.REACTOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFunctionalComponent() throws InvalidComponentException {
        op.functional(Components.WATERPUMP);
    }

    @Test
    public void testValidFunctionalWaterPump() throws InvalidComponentException {
        assertTrue(op.functional(Components.WATERPUMP, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFunctionalWaterPump() throws InvalidComponentException {
        op.functional(Components.CONDENSERPUMP, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void functionalShouldThrowOnIncorrectPumpNo() throws InvalidComponentException {
        op.functional(Components.WATERPUMP, 100);
    }
    
    
    @Test
    public void testRodHeight() {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getRodHeight(), op.rodHeight(), 0);
    }

    @Test
    public void testValidPressure() throws InvalidComponentException {
        Reactor reactor = new Reactor();
        assertEquals(reactor.getPressure(), op.pressure(Components.REACTOR), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPressure() throws InvalidComponentException {
        op.pressure(Components.TURBINE);
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
        op.fix(Components.WATERPUMP);
    }

    @Test(expected = InvalidPumpException.class)
    public void testFixInvalidWaterPump() throws NumberFormatException, InvalidComponentException,
                                                 FixAlreadyUnderwayException, NoFixNeededException, InvalidPumpException {
        op.fix(Components.WATERPUMP, 2);
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
        assertFalse(op.isRepairing(Components.REACTOR));
    }

    @Test
    public void testIsRepairingWaterPump() {
        assertFalse(op.isRepairing(Components.WATERPUMP, 0));
    }

    @Test
    public void testGetGameState() {
        assertNotNull(op.getGameState());
    }
}
