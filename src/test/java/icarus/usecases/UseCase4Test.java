package icarus.usecases;

import icarus.parser.CommandParser;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.TestCase;

public class UseCase4Test {

    //results, supporting the scenario can be seen in the console
    @Test(expected = NoSuchElementException.class)
    public void testMainScenario() {
        ByteArrayInputStream in = new ByteArrayInputStream(("new game\n" +
                                                            "name\n" +
                                                            "\n\n" +
                                                            "raise 20\n" +
                                                            "next\n" +
                                                            "raise 50\n" +
                                                            "turn off 0\n" +
                                                            "turn off 1\n" +
                                                            "close 0\n" +
                                                            "close 1\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "next").getBytes());

        CommandParser parser = new CommandParser(new Scanner(in));
        parser.startUpMenu();
    }

    //executed immediately after testMainScenario() (need to distinguish between the two outputs in the console)
    //reactor/condenser failure is not guaranteed (because of the chance to fail)
    @Test(expected = NoSuchElementException.class)
    public void testSecondScenario() {
        ByteArrayInputStream in = new ByteArrayInputStream(("new game\n" +
                                                            "name\n" +
                                                            "\n\n" +
                                                            "raise 99\n" +
                                                            "close 0\n" +
                                                            "close 1\n" +
                                                            "next\n" +
                                                            "next").getBytes());

        CommandParser parser = new CommandParser(new Scanner(in));
        parser.startUpMenu();
    }
}
