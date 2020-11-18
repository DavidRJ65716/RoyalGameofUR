/*
 * application/model/GameState.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application.model;

/**
 * Enumerator for maintaining a game state.
 */
public enum GameState {
	MAIN, PAUSE, PLAYER_ONE, PLAYER_TWO;
	
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
