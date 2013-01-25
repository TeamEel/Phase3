package icarus.usecases.test;

import icarus.parser.CommandParser;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.TestCase;

public class UseCase3Test {

    //results, supporting the scenario, can be seen in the console 
    @Test(expected = NoSuchElementException.class)
    public void testMainScenario() {
        ByteArrayInputStream in = new ByteArrayInputStream(("new game\n" +
                                                            "name\n" +
                                                            "\n" +
                                                            "next\n" +
                                                            "save mygame123").getBytes());

        CommandParser parser = new CommandParser(new Scanner(in));
        parser.startUpMenu();
    }
    //not implemented due to changes in design (user can't choose whether to save file)

    public void testSecondScenario() {
    }
}
