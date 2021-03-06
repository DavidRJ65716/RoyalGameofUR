package application.model;

/*
 * TODO:
 * Might have to add more to gamestate such as
 * 
 * 		PLAYER_ONE -> PLAYER_ONE_ROLLED -> PLAYER_TWO -> PLAYER_TWO_ROLLED
 * 
 * and an int to keep track of dice roll, so that the user can save after
 * rolling and have that persist through a save and load. Can be worked on later
 * as it probably won't need a huge rewrite, just some additions here and in the 
 * save/load functions
 */

/**
 * Enumerator for maintaining a game state.
 */
public enum GameState {
	MAIN, 
	PAUSE, 
	PLAYER_ONE,
	PLAYER_ONE_WIN,
	PLAYER_TWO,
	PLAYER_TWO_WIN;
	
	/**
	 * Converts the given string to a GameState
	 * @param input string to parse
	 * @return GameState value if successfully parsed, null otherwise
	 */
	public static GameState parseString(String input) {
		switch (input) {
		case "MAIN":
			return GameState.MAIN;
			
		case "PAUSE":
			return GameState.PAUSE;
			
		case "PLAYER_ONE":
			return GameState.PLAYER_ONE;
			
		case "PLAYER_TWO":
			return GameState.PLAYER_TWO;
			
		default:
			return null;
		}
	}
}
