package icarus.parser;

/**
 *
 * This class holds information about a command that was issued by the user. A command currently consists of three
 * parts: a CommandWord and a string (for example, if the command was "open valve 1", then the three parts are OPEN
 * ,"valve" and "1").
 *
 * The way this is used is: Commands are already checked for being valid command words. If the user entered an invalid
 * command (a word that is not known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second and third words are <null>.
 *
 * @author Team Haddock
 * @version 2012/2013
 */
public class Command {

    private CommandWord commandWord;
    private String secondWord;
    private String thirdWord;

    /**
     * Create a command object. First and second words must be supplied, but the second may be null.
     *
     * @param commandWord The CommandWord. UNKNOWN if the command word was not recognised.
     * @param secondWord  The second word of the command. May be null.
     */
    public Command(CommandWord commandWord, String secondWord, String thirdWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    /**
     * Return the command word (the first word) of this command.
     *
     * @return The command word.
     *
     * @uml.property name="commandWord"
     */
    public CommandWord getCommandWord() {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no second word.
     *
     * @uml.property name="secondWord"
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }

    /**
     * @return The third word of this command. Returns null if there was no third word.
     */
    public String getThirdWord() {
        return thirdWord;
    }

    /**
     * @return true if the command has a third word.
     */
    public boolean hasThirdWord() {
        return (thirdWord != null);
    }
}
