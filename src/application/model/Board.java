package application.model;

/**
 * Stores all the information of the board
 */
public class Board {
	
	private int board[] = {0,0,0,1,0,0,0,2,0,0,0,0,0,3};
	
	public int getBoard(int i) {
		
		return board[i];
	}
}
