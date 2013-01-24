package icarus.parser.test;

import static org.junit.Assert.*;
import icarus.parser.Command;
import icarus.parser.CommandWord;
import icarus.parser.CommandWords;

import org.junit.Test;

public class CommandWordsTest {

	@Test
	public void testGetCommandWord() {
		CommandWords cws = new CommandWords();
		assertEquals("Raise", CommandWord.RAISE, cws.getCommandWord("raise"));
		assertEquals("Lower", CommandWord.LOWER, cws.getCommandWord("lower"));
		assertEquals("Close", CommandWord.CLOSE, cws.getCommandWord("close"));
		assertEquals(CommandWord.OPEN, cws.getCommandWord("open"));
		assertEquals(CommandWord.TURNOFF, cws.getCommandWord("turnoff"));
		assertEquals(CommandWord.TURNON, cws.getCommandWord("turnon"));
		assertEquals(CommandWord.LOAD, cws.getCommandWord("load"));
		assertEquals(CommandWord.SAVE, cws.getCommandWord("save"));
		assertEquals(CommandWord.HELP, cws.getCommandWord("help"));
		assertEquals(CommandWord.QUIT, cws.getCommandWord("quit"));
		assertEquals(CommandWord.UNKNOWN, cws.getCommandWord("test"));
	}

	@Test
	public void testIsCommand() {
		CommandWords cws = new CommandWords();
		assertTrue(cws.isCommand("raise"));
		assertTrue(cws.isCommand("lower"));
		assertTrue(cws.isCommand("open"));
		assertTrue(cws.isCommand("close"));
		assertTrue(cws.isCommand("turnoff"));
		assertTrue(cws.isCommand("turnon"));
		assertTrue(cws.isCommand("load"));
		assertTrue(cws.isCommand("save"));
		assertTrue(cws.isCommand("quit"));
		assertTrue(cws.isCommand("help"));
		assertFalse(cws.isCommand("test"));
		assertFalse(cws.isCommand("try with any possible string"));
	}

}
