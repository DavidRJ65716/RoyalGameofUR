package application.model;

/**
 * Stores all the event information of the board
 */
public class Board {
	
	private int board[] = {0,0,0,1,0,0,0,2,0,0,0,0,0,3};
	
	/*
	 * Gets event from board
	 * 
	 * @perm int i
	 * @returns board array
	 */
	public int getBoard(int i) {
		
		return board[i];
	}
}
