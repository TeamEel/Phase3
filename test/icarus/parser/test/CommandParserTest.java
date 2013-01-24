package icarus.parser.test;

import static org.junit.Assert.*;

import icarus.parser.Command;
import icarus.parser.CommandParser;
import icarus.parser.CommandWord;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

public class CommandParserTest {

	@Test
	public void testGetCommandInvalid() {
		ByteArrayInputStream in = new ByteArrayInputStream("random invalid command".getBytes());

		CommandParser parser = new CommandParser(new Scanner(in));

		Command commandTest = parser.getCommand();
		assertEquals(CommandWord.UNKNOWN, commandTest.getCommandWord());
		assertEquals("invalid", commandTest.getSecondWord());
		assertEquals("command", commandTest.getThirdWord());
	}

	@Test
	public void testGetCommandValid() {
		ByteArrayInputStream in = new ByteArrayInputStream("help".getBytes());
		
		CommandParser parser = new CommandParser(new Scanner(in));

		Command commandTest = parser.getCommand();
		assertEquals(CommandWord.HELP, commandTest.getCommandWord());
		assertNull(commandTest.getSecondWord());
		assertNull(commandTest.getThirdWord());
	}

	@Test
	public void testGetCommandCaseInsensitive() {
		ByteArrayInputStream in = new ByteArrayInputStream("hElP".getBytes());

		CommandParser parser = new CommandParser(new Scanner(in));

		Command commandTest = parser.getCommand();
		assertEquals(CommandWord.HELP, commandTest.getCommandWord());
		assertNull(commandTest.getSecondWord());
		assertNull(commandTest.getThirdWord());
	}

	@Test
	public void testAskForPlayersName() {
		ByteArrayInputStream in = new ByteArrayInputStream("Djokovic".getBytes());
		// String playerName = "";
		CommandParser parser = new CommandParser(new Scanner(in));
		parser.askForPlayersName();
		assertEquals("Djokovic", parser.getPlayersName());
	}

	@Test
	public void testGetPlayersName() {
		CommandParser parser = new CommandParser(new Scanner(System.in));
		assertNull(parser.getPlayersName());
	}

	@Test
	public void testGetCommandWordInvalid() {
		CommandParser parser = new CommandParser();

		assertEquals(CommandWord.UNKNOWN, parser.getCommandWord("invalid command here"));
	}

	@Test
	public void testGetCommandWordValid() {
		CommandParser parser = new CommandParser();

		assertEquals(CommandWord.HELP, parser.getCommandWord("help"));
	}

}
