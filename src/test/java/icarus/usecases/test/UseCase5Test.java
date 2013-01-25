package icarus.usecases.test;

import icarus.parser.CommandParser;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.TestCase;

public class UseCase5Test {

    //results, supporting the scenario, can be seen in the console 
    // number of next commands is not equal to 20 because of a possible game over scenario
    @Test(expected = NoSuchElementException.class)
    public void testMainScenario() {
        ByteArrayInputStream in = new ByteArrayInputStream(("new game\n" +
                                                            "name\n" +
                                                            "\n" +
                                                            "next\n" +
                                                            "save mygame123\n" +
                                                            "load mygame123\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "next\n" +
                                                            "save mygame321\n" +
                                                            "quit").getBytes());

        CommandParser parser = new CommandParser(new Scanner(in));
        parser.startUpMenu();
    }
    //not implemented due to changes in design (user can't choose whether to save file)
    //saving with the same name doesn't provide an override option (due to change in design)

    @Test(expected = NoSuchElementException.class)
    public void testSecondScenario() {
        ByteArrayInputStream in = new ByteArrayInputStream(("new game\n" +
                                                            "name\n" +
                                                            "\n" +
                                                            "next\n" +
                                                            "save mygame123\n" +
                                                            "load mygame1234\n" +
                                                            "save mygame123").getBytes());

        CommandParser parser = new CommandParser(new Scanner(in));
        parser.startUpMenu();
    }
}
