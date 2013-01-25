package icarus.operatorsoftware.test;

import icarus.operatorsoftware.Components;
import static org.junit.Assert.*;
import org.junit.Test;

public class ComponentTest {

    @Test
    public void testToString() {
        assertEquals("reactor", Components.REACTOR.toString());
        assertEquals("condenser", Components.CONDENSER.toString());
        assertEquals("waterpump", Components.WATERPUMP.toString());
        assertEquals("condenserpump", Components.CONDENSERPUMP.toString());
        assertEquals("generator", Components.GENERATOR.toString());
        assertEquals("turbine", Components.TURBINE.toString());
    }
}
