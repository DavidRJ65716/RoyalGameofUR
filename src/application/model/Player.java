package application.model;

/**
 * Hold The player information
 * resets player pieces
 * swaps location on the board
 */
public class Player {
	
	private int playerB[] = new int[14]; 
	public int pieces;
	public int offpiecese;
	public boolean inplay; 
	
	
	public int getPlayerB(int i) {
		
		return playerB[i];
	}
	
	/*
	 * Sets the player pieces on the board
	 * 
	 *  @ int i piece location
	 *  @ int j new location
	 *  @ int player 
	 */
	public void setPlayerB(int i, int j, int player) {
		
		playerB[i] = 0;
		playerB[j] = player;
	}
	
	/*
	 * Resets players pieces
	 */
	public void reset() {
		
		for (int i = 0; i < 14; i++) {
			playerB[i] = 0;
		}
		
		pieces = 7;
		offpiecese = 0;
	}
}
