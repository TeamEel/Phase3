package icarus.operatorsoftware.test;

import static org.junit.Assert.*;
import icarus.operatorsoftware.Component;

import org.junit.Test;

public class ComponentTest {

	@Test
	public void testToString() {
		assertEquals("reactor", Component.REACTOR.toString());
		assertEquals("condenser", Component.CONDENSER.toString());
		assertEquals("waterpump", Component.WATERPUMP.toString());
		assertEquals("condenserpump", Component.CONDENSERPUMP.toString());
		assertEquals("generator", Component.GENERATOR.toString());
		assertEquals("turbine", Component.TURBINE.toString());
	}

}
