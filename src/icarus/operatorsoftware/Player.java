package icarus.operatorsoftware;

import java.io.Serializable;

/**
 * Serializable class containing the player's name
 * for saving and loading
 * @author Team Haddock
 *
 */
public class Player implements Serializable {

	private String playerName;
	private static final long serialVersionUID = 1;

	public Player() {
	}

	public void setName(String name) {
		playerName = name;
	}

	public String getName() {
		return playerName;
	}

}
