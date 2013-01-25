package icarus.usecases;

import icarus.parser.CommandParser;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.TestCase;

public class UseCase1Test {

    //executed immediately after testMainScenario() (need to distinguish between the two outputs in the console)
    @Test(expected = NoSuchElementException.class)
    public void testSecondScenario() {
        ByteArrayInputStream in = new ByteArrayInputStream(("invalid command\n" +
                                                            "load mygame123\n").getBytes());

        CommandParser parser = new CommandParser(new Scanner(in));
        parser.startUpMenu();
    }
    //results, supporting the scenario can be seen in the console 

    @Test(expected = NoSuchElementException.class)
    public void testMainScenario() {
        ByteArrayInputStream in = new ByteArrayInputStream(("new game\n" +
                                                            "name\n" +
                                                            "\n").getBytes());

        CommandParser parser = new CommandParser(new Scanner(in));
        parser.startUpMenu();
    }
}
