package icarus.operatingsoftware;

import icarus.operatingsoftware.Components;
import static org.junit.Assert.*;
import org.junit.Test;

public class ComponentsTest {

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
