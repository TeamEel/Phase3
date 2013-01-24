package icarus.parser.test;

import static org.junit.Assert.*;
import icarus.parser.Command;
import icarus.parser.CommandWord;

import org.junit.Test;

public class CommandTest {

	@Test
	public void testGetCommandWord() {
		Command command = new Command(CommandWord.RAISE, "12", null);
		assertEquals("Command word", CommandWord.RAISE, command.getCommandWord());
	}

	@Test
	public void testGetSecondWord() {
		Command command = new Command(CommandWord.TURNOFF, "1", null);
		assertEquals("Turn off 1", "1", command.getSecondWord());
	}

	@Test
	public void testIsUnknown() {
		Command command = new Command(CommandWord.CLOSE, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.FIX, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.HELP, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.LOAD, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.LOWER, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.NEXT, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.OPEN, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.QUIT, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.SAVE, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.RAISE, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.TURNOFF, null, null);
		assertFalse(command.isUnknown());
		command = new Command(CommandWord.TURNON, null, null);
		assertFalse(command.isUnknown());

	}

	@Test
	public void testHasSecondWord() {
		Command command = new Command(CommandWord.LOWER, "12", null);
		assertTrue(command.hasSecondWord());
		command = new Command(CommandWord.HELP, null, null);
		assertFalse(command.hasSecondWord());
	}

	@Test
	public void testGetThirdWord() {
		Command command = new Command(CommandWord.FIX, "pump", "1");
		assertEquals("Equals third word: ", "1", command.getThirdWord());
		command = new Command(CommandWord.CLOSE, "1", null);
		assertEquals("Third word equals: ", null, command.getThirdWord());
	}

	@Test
	public void testHasThirdWord() {
		Command command = new Command(CommandWord.FIX, "pump", "0");
		assertTrue(command.hasThirdWord());
		command = new Command(CommandWord.OPEN, "1", null);
		assertFalse(command.hasThirdWord());
	}

}
