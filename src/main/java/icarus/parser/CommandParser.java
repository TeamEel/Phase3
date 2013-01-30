package icarus.parser;

/**
 *
 * CommandParser class holds the processing of all commands, registered through the console. It is also the main
 * starting point of the game.
 *
 * @author Team Haddock
 * @version 2012/2013
 */
import icarus.operatingsoftware.OperatingSoftware;
import icarus.exceptions.*;
import icarus.operatingsoftware.Components;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CommandParser {

    private Scanner reader; // source of command input
    private String playerName = "";  //playerName will hold the player's name throughout the game, which will be used when the player is addressed
    private CommandWords commands;
    private OperatingSoftware cf;

    /**
     * Create a parser to read from the terminal window.
     */
    public CommandParser() {
        reader = new Scanner(System.in);
        commands = new CommandWords();
        cf = new OperatingSoftware();
    }

    /**
     * Create a parser to read from the terminal window.
     *
     * @param reader
     */
    public CommandParser(Scanner reader) {
        this.reader = reader;
        commands = new CommandWords();
        cf = new OperatingSoftware();
    }

    /**
     * The welcome() method is called when a player starts a fresh game. It introduces the background story, shows the
     * player the available commands and gives the player some instruction as to how to play the game.
     */
    public void welcomeNewPlayer() {
        //story introduction, asks the new player their name
        System.out.println("\n\n\nWhen you joined the secret service, you had dreams of becoming the" + '\n' +
                           "worlds greatest secret agent. However your dreams of being the next 007 were" + '\n' +
                           "shattered when you landed a job in the secret service, as a desk jockey...");
        System.out.println();
        System.out.println("Little did you know that today would be the day your dreams came" + '\n' +
                           "true, but at the price of your biggest nightmare...");
        System.out.println("RING RING");
        System.out.println("RING RING");
        System.out.println("It's 3am and you answer the phone sleepily and a little annoyed...");
        System.out.println("\"Hello?! Hello?! Agent? Oh God, what's your name? Answer quick!\""); //prompt the player for her name
        System.out.print("> ");

        askForPlayersName();
        //gets the player's name


        System.out.println();

        System.out.println(String.format("%s, this is your president, Barack Obama. Aliens are invading Earth!" + '\n' +
                                         "Far beyond our stars, an alien race have exhausted their solar system. " +
                                         '\n' +
                                         "Having destroyed the homes of many inferior species, the aliens have scoured" +
                                         '\n' +
                                         "their galaxy and have setttled on us as their next target." + '\n' +
                                         "We knew this was coming, and we need you, to power the weapons that could" +
                                         '\n' +
                                         "save Earth. Go to the nuclear plant and create as much power as possible." +
                                         '\n' +
                                         "Be as fast as you can, most of our agents are already dead and the aliens won't" +
                                         '\n' +
                                         "take long to work out what you are going to do!\"", cf.getPlayerName()));

        System.out.println("Press enter to continue");
        System.out.println("> ");
        reader.nextLine();

        System.out.println();

        //when software failures are implemented, will say "watch out, the operating software doesn't always work perfectly..."
        //gives the player some instruction on how to play the game
        System.out.println(String.format("%s , your aim is to PRODUCE AS MUCH POWER AS POSSIBLE. But" + '\n' +
                                         "be careful, if you overheat the reactor it'll be game over, and you'll need to fix" +
                                         '\n' +
                                         "any of the components that may break along the way with a fix command. Only one fix may" +
                                         '\n' +
                                         "take place at a time, and it will take a while for each fix to complete!" +
                                         '\n' +
                                         "Below are the controls you'll need, just type your command into the text window and the " +
                                         '\n' +
                                         "nuclear reactor's computer will take care of the rest.\"", cf.getPlayerName()));
        System.out.println(String.format(
                "\"Oh and %s, don't forget to progress your reactor by using the \'next\'" + '\n' +
                "command after you've made all the modifications to the systems components that you want!" + '\n' +
                "The display will let you know how the reactor ,is doing every time you progress with \'next\'\"", cf
                .getPlayerName()));

        System.out.println();
        showCommands();  // print a list of all available commands

    }

    /**
     * Called when a player loads a saved game
     *
     */
    public void welcomeBackPlayer() {
        playerName = cf.getPlayerName();
        System.out.println("Welcome back, " + playerName + ", the world has been wating for you!" + '\n' +
                           "Here is the state of your reactor: ");
    }

    /**
     * Called when a new game is started
     *
     */
    public void startUpMenu() {
        System.out.println("Hey, there! You have before you the amazing game ICARUS!! ");
        System.out.println("Would you like to start a \"New game \",  \"Load \" an existing one or \"Quit\"?");
        System.out.println("If you want to start a new game, type \"new game \".");
        System.out.println("If you want to load a previously saved game, type \"load <name of saved game>");
        System.out.println("If you want to quit the game, type \"quit\"");

        boolean finished = false;
        while (!finished) {
            System.out.print(">");
            String inputLine = reader.nextLine();
            Scanner tokenizer = new Scanner(inputLine);
            if (tokenizer.hasNext()) {
                String word1 = tokenizer.next().toLowerCase();
                if (word1.equals("quit") && !tokenizer.hasNext()) {
                    System.out.println("See you next time!");
                    finished = true;
                    System.exit(0);
                } else if (!word1.equals("quit") && !word1.equals("new") && !word1.equals("load")) {
                    System.out.println("Command not recognised");
                }
                if (tokenizer.hasNext()) {
                    String word2 = tokenizer.next().toLowerCase();
                    if (word1.equals("new") && (word2.equals("game")) && !tokenizer.hasNext()) {
                        welcomeNewPlayer();
                        play();
                        tokenizer.close();
                        finished = true;
                    } else if (word1.equals("load") && (!word2.equals(null))) {
                        if (cf.loadFromFile(word2)) {
                            welcomeBackPlayer();
                            play();
                            tokenizer.close();
                            finished = true;
                        }
                    } else if (word1.equals("new") && ((!word2.equals("game")) || tokenizer.hasNext())) {
                        System.out.println("Command not recognised");
                    } else if (word1.equals("quit") && !word2.equals(null)) {
                        System.out.println("Command not recognised");
                    }
                }
            }
        }


    }

    /**
     * The play() method sets up the parser and the loop. The finished local variable serves as a flag to the while
     * loop. A Scanner object is used for reading input. A case for each command is defined in the switch expression.
     */
    public void play() {
        String userResponse = "";
        boolean finished = false;
        playerName = cf.getPlayerName();

        updateDisplay();

        while (!finished) {
            Command command = getCommand(); //process the input
            CommandWord commandWord = command.getCommandWord(); //get the first word of the input line
            String word2 = "";
            String word3 = "";
            if (command.hasSecondWord()) {
                word2 = command.getSecondWord().toLowerCase();
            }
            if (command.hasThirdWord()) {
                word3 = command.getThirdWord().toLowerCase();
            }

            switch (commandWord) {
                case HELP:
                    System.out.println(String.format("%s , you've no idea how to play this game, huh?", playerName));
                    System.out.println("Here's some help");
                    showCommands();  // print a list of all available commands
                    break;
                case UNKNOWN: // define the behaviour when an unknown command has been encountered
                    System.out.println(String.format("I don't know what you're talking about, %s", playerName));
                    System.out.println("Here are the available commands: ");
                    showCommands();
                    break;
                case NEXT:
                    if (finished != true) {
                        cf.next();
                        updateDisplay();
                    }
                    if (cf.checkIfGameOver()) {
                        gameOverScenario();
                        System.out.println('\n' + "Would you like to try again? y/n");

                        while (!userResponse.equals("y") && !userResponse.equals("n")) {
                            userResponse = reader.nextLine();
                        }
                        if (userResponse.equals("y")) {
                            cf = new OperatingSoftware();
                            finished = false;
                            userResponse = "";
                            updateDisplay();
                        } else if (userResponse.equals("n")) {
                            finished = true;
                            userResponse = "";
                            System.out.println(String.format("Game over, %s", playerName));
                        }
                    }

                    break;
                case QUIT:
                    System.out.println("Do you want to save the game? y/n");
                    userResponse = reader.nextLine();
                    while (!userResponse.equals("y") && !userResponse.equals("n")) {
                        userResponse = reader.nextLine();
                    }
                    if (userResponse.equals("y")) {
                        System.out.println("Save the game as (type a name): ");
                        userResponse = reader.nextLine();
                        cf.saveToFile(userResponse);
                        System.out.println("Game saved.");
                        finished = true;
                        System.out.println(String.format("Game over, %s", playerName));
                    } else if (userResponse.equals("n")) {
                        finished = true;
                        System.out.println(String.format("Game over, %s", playerName));
                    }

                    break;
                case TURNOFF:
                    turnOff(word2);
                    break;
                case TURNON:
                    turnon(word2);
                    break;
  /*              case RAISE:
                    raise(word2);
                    break;
                case LOWER:
                    lower(word2);
                    break;*/
                case OPEN:
                    open(word2);
                    break;
                case CLOSE:
                    close(word2);
                    break;
                case SAVE:
                    if (!word2.equals("")) {
                        System.out.println("Saving Game...Done");
                        cf.saveToFile(word2);
                    } else {
                        System.out.println("Please enter a name for the saved game. E.g save mygame");
                    }
                    break;
                case LOAD:
                    if (!word2.equals("")) {
                        if (cf.loadFromFile(word2)) {
                            System.out.println("Loading Game...Done");
                            welcomeBackPlayer();
                            updateDisplay();
                        }
                    } else {
                        System.out.println("Please enter a name for the saved game. E.g load mygame");
                    }
                    break;
                case FIX:
                    try {
                        fix(word2, word3);
                    } catch (NoFixNeededException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }

        reader.close();
        System.exit(0);  // terminate
    }

    /**
     * The method processes each line of input. First word is considered the command, and if there are second and third
     * they are the objects of the command.
     *
     * @return The next command from the user.
     */
    public Command getCommand() {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);

        if (tokenizer.hasNext()) {
            word1 = tokenizer.next().toLowerCase();      // get first word, always convert to lower case to enable flexibility
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next().toLowerCase(); // get second word, if there is one

                if (word2.equals("on")) {   // this if statement covers the "turn off" and "turn on" commands, which are two words, but one command.
                    //By concatenating "turn" and "on/off" we get one-word command to avoid mistakes in the processing later on.
                    word1 = word1 + "on";
                    word2 = null;
                } else if (word2.equals("off")) {
                    word1 = word1 + "off";
                    word2 = null;
                }
                if (tokenizer.hasNext()) {
                    if (word2 == null) {
                        word2 = tokenizer.next().toLowerCase();
                    } else {
                        word3 = tokenizer.next().toLowerCase();
                    }

                    if (tokenizer.hasNext()) {
                        word3 = tokenizer.next().toLowerCase();
                    }
                }
            }
            // note: we just ignore the rest of the input line.
        }
        tokenizer.close();

        //need to change the getCommandWord to point to the method in OperatingSoftware
        return new Command(getCommandWord(word1), word2, word3);
    }

    /**
     * Gets the player name and passes it to the Player class
     *
     */
    public void askForPlayersName() {
        String playerName = reader.nextLine(); // gets the player's name from the console

        while (playerName.equals("")) {
            System.out.println(">");
            playerName = reader.nextLine();
        }
        cf.setPlayerName(playerName);
    }

    /**
     * @return the player's name
     *
     */
    public String getPlayersName() {
        return cf.getPlayerName();
    }

    //--
    /**
     * Print out a list of valid command words.
     */
    public void showCommands() {
        commands.showAll();
        System.out.println("open	 - opens a valve in the system allowing steam to move, use \'open 0/1\'");
        System.out.println("close	 - closes a valve in the system stopping steam from moving, use \'close 0/1\'");
        System.out.println("turn on	 - turns on a pump in the system, moving water to the reactor, use \'turn on 0-2\'");
        System.out.println("turn off - turns off a pump in the system, stopping water flow, use \'turn off 0-2\'");
        System.out.println("raise	 - raise the control rods to increase reaction rate, use \'raise 1-100\'");
        System.out.println("lower	 - lower the control rods to reduce reaction rate, use \'lower 1-100\'");
        System.out.println("fix		 - fixes a component in the system, only one fix at a time, use \'fix <component>\'");
        System.out.println("next	 - advances to the next timestep, use \'next\'");
        System.out.println("save	 - saves the game to the current directory, use \'save <filename>\'");
        System.out.println("load	 - loads the named game from the current directory, use \'load <filename>\'");
        System.out.println("quit	 - quits the game");
        System.out.println("help	 - shows this help menu" + '\n');
    }

    /**
     * @return CommandWord
     *
     */
    public CommandWord getCommandWord(String word) {
        return commands.getCommandWord(word);
    }

    /**
     * Updates the display with information about all of the components
     *
     */
    public void updateDisplay() {
        DecimalFormat df = new DecimalFormat("#.##");
        try {
            System.out.println(String.format("%-15s%15s%15s", " ", "Reactor", "Condenser"));
            System.out.println(String.format("%-15s%15s%15s", "Water level",
                                             df.format(cf.waterLevel(Components.REACTOR)) + " cm", df.format(cf
                    .waterLevel(Components.CONDENSER)) + " cm"));
            System.out.println(String.format("%-15s%15s%15s", "Pressure level", df
                    .format(cf.pressure(Components.REACTOR)) + " kPa", df.format(cf.pressure(Components.CONDENSER)) +
                                                                      " kPa"));
            System.out.println(String.format("%-15s%15s%15s", "Temperature", df
                    .format(cf.temperature(Components.REACTOR)) + " K", df.format(cf.temperature(Components.CONDENSER)) +
                                                                       " K"));
            System.out.println(String.format("%-15s%15s", "Rod Height", cf.rodHeight()));
            System.out.println();

        } catch (InvalidComponentException e) {
            e.printStackTrace();
        }
        if (cf.isWaterPumpActive(0)) {
            System.out.println("Pump 0 (water pump): on ");
        } else {
            System.out.println("Pump 0 (water pump): off ");
        }
        System.out.print("Pump 1 (water pump): ");
        if (cf.isWaterPumpActive(1)) {
            System.out.println("on ");
        } else {
            System.out.println("off ");
        }
        System.out.print("Pump 2 (condenser pump): ");
        if (cf.isCondenserPumpActive()) {
            System.out.println("on");
        } else {
            System.out.println("off");
        }
        try {
            if (cf.isValveOpened(0)) {
                System.out.println("Valve 0: open ");
            } else {
                System.out.println("Valve 0: closed ");
            }
        } catch (InvalidValveException e) {
            e.printStackTrace();
        }
        try {
            if (cf.isValveOpened(1)) {
                System.out.println("Valve 1: open ");
            } else {
                System.out.println("Valve 1: closed ");
            }
        } catch (InvalidValveException e) {
            e.printStackTrace();
        }
        System.out.println('\n' + "Power generated: " + cf.getPower() + " MW \n");
        if (cf.doFix()) {
            System.out.println("A component has been repaired.");
        }
        reportFailures();
    }

    /**
     * Updates the display with information about the failures of the components
     *
     */
    public void reportFailures() {
        try {
            if (!cf.functional(Components.REACTOR)) {
                System.out.print("The reactor " + " is not functioning properly");
                if (cf.isRepairing(Components.REACTOR)) {
                    System.out.println(" but is being repaired.");
                } else {
                    System.out.println("!");
                }
            }
            if (!cf.functional(Components.CONDENSER)) {
                System.out.print("The condenser " + "is broken due to an alien attack");
                if (cf.isRepairing(Components.CONDENSER)) {
                    System.out.println(" but is being repaired.");
                } else {
                    System.out.println("!");
                }
            }
            if (!cf.functional(Components.TURBINE)) {
                System.out.print("The turbine " + "is broken and isn't generating any power");
                if (cf.isRepairing(Components.TURBINE)) {
                    System.out.println(" but is being repaired.");
                } else {
                    System.out.println("!");
                }
            }
            if (!cf.functional(Components.CONDENSERPUMP)) {
                System.out.print("The condenser pump (pump 2) " + "was shot in the crossfire and is broken");
                if (cf.isRepairing(Components.CONDENSERPUMP)) {
                    System.out.println(" but is being repaired.");
                } else {
                    System.out.println("!");
                }
            }
            if (!cf.functional(Components.WATERPUMP, 0)) {
                System.out.print("Pump 0 " + "is badly damaged and not functioning");
                if (cf.isRepairing(Components.WATERPUMP, 0)) {
                    System.out.println(" but is being repaired.");
                } else {
                    System.out.println("!");
                }
            }
            if (!cf.functional(Components.WATERPUMP, 1)) {
                System.out.print("Pump 1 " + "is badly damaged and not functioning");
                if (cf.isRepairing(Components.WATERPUMP, 1)) {
                    System.out.println(" but is being repaired.");
                } else {
                    System.out.println("!");
                }
            }
        } catch (InvalidComponentException e) {
            e.printStackTrace();
        }
        if (cf.fixUnderway()) {
            System.out.println("Time remaining on current fix: " + cf.getFixTime());
        }
    }

    /**
     * Called when a game over scenario is reached (updates display accordingly)
     *
     */
    public void gameOverScenario() {

        //local variable to hold the symbol picture printed to the terminal
        //in the GUI an actual graphical explosion will take place instead of a symbolic picture
        String picture;
        //local variable for the power the player achieves (retrieved from the operator software)
        double power = cf.getPower();


        picture =
        "MMMMMMMMNNNNNNNDDD88888OOOOZZZ$$$7$777777777IIII??????????????IIIIII77" + "\n" +
        "NNNNNNNNNNNNNDD88OZZ$$Z$$$$$$$~OZ=,:,.~I7II???+?++++++++?+?????????III" + "\n" +
        "NNNNNNNNNDDDD888Z$++~,:~:7I7:+::~$~I,.~,,I??+++??+++++++?+?++++++?+??I" + "\n" +
        "NNDNDDDDDD888OZ7=Z=$8,7:===+?+II$~+,:,.:~=:=?~,.~~=====+++++==+=+++???" + "\n" +
        "NDNDDDDD88OZOZ$?7:=+~=:I+,I7?ZI=I+$+7~=~Z=.=$:.~.I,~:===+++==++=+=++??" + "\n" +
        "DDDD8888OOZO$7$=78$I$II$D7:~?,7+=7ZZZ~7=~:,~I.:$=I,+=.=====+=++=+++???" + "\n" +
        "DD888888OOZ$?O?DZO7OZ$+O877?ZOZZ?7I8DZ8ZI=+O,,=+$?I+,,:~~=~========+++" + "\n" +
        "D8888OOOZ$$7IO$N?8NZ8Z~D8ZZDDND8NDNN88DON?~$7?=??Z7??==:=~=~=====+====" + "\n" +
        "8888OOOZ$777=$=I?OOZDO~7$888ODNO:=8MDOO$NMD8ZOOI$ZO88OZ:===+=+==+=====" + "\n" +
        "88OOOZ$$77IIZ$7~8OOZON88NNDNN8NNDND8D8D8MDDNDNMD8DD8ZO.~=====+++===+++" + "\n" +
        "OOOZZ$$77II8??OI+?OD8N888NDMDDODDMMDDDDNND88NDDNMDMDZZO~~~~==+===+=+=~" + "\n" +
        "OZZZ$$77III?$O$$DO88M8D8DO$ZDDDM8DN8NDN8MNOOD8NNNMDDZDZ$==~====+======" + "\n" +
        "$$$$77III??+=7DMNOMNNO8O8$+=DDNN8DD8DDN8DII$OODNMNN8ZD~=~====+==+++===" + "\n" +
        "7777I???+++===+8NDD8DOD8$ZZ77DMDDI~N~$8O7ZIZ$ODDN8D8Z~~~~~~~~~~~==~~==" + "\n" +
        "II??++====~~~~~:8N88DO8O8888DNDNI$Z?8ZZOZZ8ODNNDZ,::,:::::::::::~~~~~~" + "\n" +
        "++===~~~~::::~::::,,,,,,,:Z=::NDNND88Z8D8=,D:.,,,,,,,,,,,,,,,,,,,,::::" + "\n" +
        "=~~~~~::::::,,,,,,,,,,,,:,,,,.N8NNN88:,,,:,,,,,,,,,,,,,,,,,,,,,,,:::::" + "\n" +
        "~~~:::::,,,:,:,,,,,,,,,:,,,,,,DO8MN8DI::,,,,,,,,,,,,:::,,,,,,,,,,,,,::" + "\n" +
        "~~~::::,,.,,,,,,,,,,,,,:::,,,D?ONDO8NO?:,,,::,,,,,,:::::,,,,,,,,,,,,,," + "\n" +
        "::,,,,.,,,,.,,,,,,,,,,::::,.$DO~I888NO$Z::,,,,,,,,,,,,:::::::,,:::::::" + "\n" +
        ":,,,,,,,,,,,,,,,,,,::::::::,8N$MDNNNM88O:::::::::::::::::~~:~~~~~~::::" + "\n" +
        ":,,,,,,,,,,,,,,,,:::::::~~~~:IMMMNMMN+==~~=~=~~::::~=~~~~============~" + "\n" +
        "::,,,,,,:::,:::~~~~~===~===??DMNNN88ZZ?==~~:~====~~==++=+===++?+?????+" + "\n" +
        "=~~~~~~~==~~~~==++++++=???II7ID+ONONDOI?++===???+++?????+??+?????+++?I" + "\n" +
        "=+=+++=+???+????+=7+ZZ8++77IZ$$O??O+$I7II8$Z+ZZI88ZI???????+++===+====" + "\n" +
        "::,::::~===+?I++??IZZONDODMDNZDNDD8ZM8MZ$DN8ND8NZN$O:~,:===~~:~::::~~~" + "\n" +
        "ZOZIID8I=OO7Z$NZOZ7$ZI7I77ZZZ$7O8ZZZ$ZI7I7$Z7$Z$7+II7$8D8Z8$OD8OOOOD8D";

        System.out.println(String.format("Your reactor has failed, %s !! It's game over for you. Good luck next time..",
                                         playerName));
        System.out.println();
        System.out.println();
        System.out.println(picture);
        System.out.println();
        System.out.println();
        System.out.println("You generated " + power + " MegaWatts of power!");
        System.out.println("Score: " + cf.getScore());

        //calls the method to calculate the medal earned and prints the game response to the medal achieved
        //in the GUI this will output on the user interface
        System.out.println(cf.achievementResponse(playerName));
        //option to try again??
    }

    /**
     * Turns off a pump
     *
     * @param word3 (string, specifying which pump)
     */
    public void turnOff(String word3) {
        try {
            cf.turnOff(Integer.parseInt(word3));
            System.out.println("Turning off pump " + word3 + "...Done");
        } catch (AlreadyAtStateException e) {
            System.out.println("Pump " + word3 + " is already turned off.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number after the command. E.g turn off 0");
        } catch (InvalidPumpException e) {
            System.out.println(e.toString());
        } catch (ComponentFailedException e) {
            System.out.println("This pump has failed and isn't responding to commands!");
        }
    }

    /**
     * Turns off a pump
     *
     * @param word3 (string,specifying which pump)
     */
    public void turnon(String word3) {
        try {
            cf.turnOn(Integer.parseInt(word3));
            System.out.println("Turning on pump " + word3 + "...Done");
        } catch (AlreadyAtStateException e) {
            System.out.println("Pump " + word3 + " is already turned on.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number after the command. E.g turn on 0");
        } catch (InvalidPumpException e) {
            System.out.println(e.toString());
        } catch (ComponentFailedException e) {
            System.out.println("This pump has failed and isn't responding to commands!");
        }
    }

    /**
     * Raises the rods by the inputed ammount
     *
     * @param word2 (string, specifying the ammount to be raised)
    
    public void raise(String word2) {
        try {
            int amount = cf.raise(Integer.parseInt(word2));
            System.out.println("Raising the control rods by " + word2 + "...Done");
            System.out.println("New rod height is " + amount);
        } catch (MaximumRodsException e) {
            System.out.println(e.toString());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number after the command. E.g raise 50");
        } catch (InvalidRodsException e) {
            System.out.println(e.toString());
        } catch (ComponentFailedException e) {
            System.out.println("The reactor has failed and isn't responding to commands!");
        }
    }
    *  */

    /**
     * Lowers the rods by the inputed ammount
     *
     * @param word2 (string, specifying the ammount to be lower)
     
    public void lower(String word2) {
        try {
            int amount = cf.lower(Integer.parseInt(word2));
            System.out.println("Lowering the control rods by " + word2 + "...Done");
            System.out.println("New rod height is " + amount);
        } catch (MinimumRodsException e) {
            System.out.println(e.toString());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number after the command. E.g lower 50");
        } catch (InvalidRodsException e) {
            System.out.println(e.toString());
        } catch (ComponentFailedException e) {
            System.out.println("The reactor has failed and isn't responding to commands!");
        }
    }
*/
    /**
     * Opens a valve
     *
     * @param word2 (string,specifying which valve)
     */
    public void open(String word2) {


        try {
            cf.open(Integer.parseInt(word2));
            System.out.println("Opening valve " + word2 + " ...Done.");
        } catch (NumberFormatException e) {
            System.out.println("You need to insert a valid number: 0 or 1. Example: open 1");
        } catch (InvalidValveException e) {
            System.out.println(e.toString());
        } catch (AlreadyAtStateException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * Closes a valve
     *
     * @param word2 (string,specifying which valve)
     */
    public void close(String word2) {
        try {
            cf.close(Integer.parseInt(word2));
            System.out.println("Closing valve " + word2 + " ...Done.");
        } catch (AlreadyAtStateException e) {
            System.out.println("Valve " + word2 + " is already closed.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number after the command. E.g close 0");
        } catch (InvalidValveException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Fixes a component
     *
     * @param word2 (string,specifying which component)
     * @param word3 (string )
     */
    public void fix(String word2, String word3) throws NoFixNeededException {
        if (word2.equals(Components.CONDENSER.toString())) {

            try {
                cf.fix(Components.CONDENSER);
                System.out.println("Fixing the condenser...");
            } catch (InvalidComponentException e) {
                System.out.println("Component not recognised");
            } catch (FixAlreadyUnderwayException e) {
                System.out.println("A fix is already underway, only one fix can occur at a time.");
            } catch (NoFixNeededException e) {
                System.out.println(e.toString());
            }
        } else if (word2.equals(Components.REACTOR.toString())) {

            try {
                cf.fix(Components.REACTOR);
                System.out.println("Fixing the reactor...");
            } catch (InvalidComponentException e) {
                System.out.println("Component not recognised.");
            } catch (FixAlreadyUnderwayException e) {
                System.out.println("A fix is already underway, only one fix can occur at a time.");
            } catch (NoFixNeededException e) {
                System.out.println(e.toString());
            }
        } else if (word2.equals(Components.TURBINE.toString())) {

            try {
                cf.fix(Components.TURBINE);
                System.out.println("Fixing the turbine...");
            } catch (InvalidComponentException e) {
                System.out.println("Component not recognised.");
            } catch (FixAlreadyUnderwayException e) {
                System.out.println("A fix is already underway, only one fix can occur at a time.");
            } catch (NoFixNeededException e) {
                System.out.println(e.toString());
            }
        } else if (word2.equals("pump") && word3.equals("2")) {
            try {
                cf.fix(Components.CONDENSERPUMP);
                System.out.println("Fixing the condenser pump...");
            } catch (InvalidComponentException e) {
                System.out.println("Component not recognised.");
            } catch (FixAlreadyUnderwayException e) {
                System.out.println("A fix is already underway, only one fix can occur at a time.");
            } catch (NumberFormatException e) {
            } catch (NoFixNeededException e) {
                System.out.println(e.toString());
            }
        } else if (word2.equals("pump") && (word3.equals("0") || word3.equals("1"))) {

            try {
                cf.fix(Components.WATERPUMP, Integer.parseInt(word3));
                System.out.println("Fixing waterpump " + word3 + " ..");
            } catch (InvalidComponentException e) {
                System.out.println("Component not recognised.");
            } catch (FixAlreadyUnderwayException e) {
                System.out.println("A fix is already underway, only one fix can occur at a time.");
            } catch (NoFixNeededException e) {
                System.out.println(e.toString());
            } catch (InvalidPumpException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println(
                    "What do you want to fix? \ncondenser, turbine, reactor,pump 0, pump 1 or pump 2? \nSample commands: \"fix turbine\", \"fix pump 0\"");
        }
    }
}
