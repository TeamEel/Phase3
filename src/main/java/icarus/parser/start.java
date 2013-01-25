package icarus.parser;

public class start {

    private static CommandParser cp;

    /**
     * Starts the game
     *
     * @param args
     */
    public static void main(String[] args) {
        cp = new CommandParser();

        Picture.showPicture();
        cp.startUpMenu();

    }
}
