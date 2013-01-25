package icarus.parser.test;

import static org.junit.Assert.*;
import icarus.parser.CommandWord;

import org.junit.Test;

public class CommandWordTest {

    @Test
    public void testToString() {
        assertEquals("Raise", "raise", CommandWord.RAISE.toString());
        assertEquals("Lower", "lower", CommandWord.LOWER.toString());
        assertEquals("Open", "open", CommandWord.OPEN.toString());
        assertEquals("Close", "close", CommandWord.CLOSE.toString());
        assertEquals("Turn off", "turnoff", CommandWord.TURNOFF.toString());
        assertEquals("Turn on", "turnon", CommandWord.TURNON.toString());
        assertEquals("Save", "save", CommandWord.SAVE.toString());
        assertEquals("Load", "load", CommandWord.LOAD.toString());
        assertEquals("Quit", "quit", CommandWord.QUIT.toString());
        assertEquals("Help", "help", CommandWord.HELP.toString());
        assertEquals("Fix", "fix", CommandWord.FIX.toString());

    }
}
