package application.model;

/**
 * Moves the pieces for player1
 */
public class PlayerOneMoves {
	
	/*
	 * Moves pieces on the board
	 * 
	 * @Player player1
	 * @Player player2
	 * @int x
	 * @int y
	 * @int dice@
	 * Board board
	 */
	public static void MovePiece(Player player1, Player player2, int x, int y, Dice dice, Board board) {
		
		int loc = cellConverter(x, y);
		int newLoc = loc + dice.total;
		
		
		if ((player2.getPlayerB(newLoc) == 0) && (board.getBoard(newLoc) == 0)
					&& (player1.getPlayerB(newLoc) == 0) && (player1.getPlayerB(loc) == 1)) {// No second player no event
			
			player1.setPlayerB(loc, newLoc, 1);
			player1.inplay = false;
			player2.inplay = true;
		} else if ((board.getBoard(newLoc) == 1)&& (player1.getPlayerB(newLoc) == 0) 
					&& (player1.getPlayerB(loc) == 1)) {// No second player first event
			
			player1.setPlayerB(loc, newLoc, 1);
		} else if ((player2.getPlayerB(newLoc) == 2) && (board.getBoard(newLoc) == 0)
				&& (player1.getPlayerB(newLoc) == 0) && (player1.getPlayerB(loc) == 1)) {// Second player no event
			
			player1.setPlayerB(loc, newLoc, 1);
			player2.setPlayerB(newLoc, newLoc, 0);
			player2.pieces += 1;
			player1.inplay = false;
			player2.inplay = true;
		} else if ((player2.getPlayerB(newLoc) == 2) && (board.getBoard(newLoc) == 0)
			&& (player1.getPlayerB(newLoc) == 0) && (player1.getPlayerB(loc) == 1)) {// second player second event
			
			player1.setPlayerB(loc, newLoc, 1);
			player2.setPlayerB(newLoc, newLoc, 0);
			player1.inplay = false;
			player2.inplay = true;
			dice.reset();
		} 
		
	}
	
	/*
	 * Moves pieces on the board
	 * 
	 * @Player player1
	 * @Player player2
	 * @int x
	 * @int y
	 * @int dice@
	 * Board board
	 */
	public static void NewPiece(Player player1, Player player2, Dice dices, Board board) {
		
		int Loc = dices.total - 1;
		System.out.println(Loc);		
		
		if ((board.getBoard(Loc) == 0) && (player1.getPlayerB(Loc) == 0) && (player1.pieces > 0)) {// Checks for piece no event
			player1.setPlayerB(Loc, Loc, 1);
			player1.inplay = false;
			player2.inplay = true;
			player1.pieces -= 1;
			dices.reset();
		} else if ((board.getBoard(Loc) == 1) && (player1.getPlayerB(Loc) == 0) && (player1.pieces > 0)) { // Checks for piece first event
			player1.setPlayerB(Loc, Loc, 1);
			dices.reset();
		}
	}
	
	
	/*
	 * Converts coordinates to work with an array
	 * 
	 * @int x
	 * @int y
	 */
	public static int cellConverter(int x, int y) {
		
		int loc = -1;
		
		switch (y) {
		case 0:
			loc = (x - 3) * (-1);
			break;
		case 1:
			loc = x + 4;
			break;
		default:
			loc = -1;
			break;			
		}
		
		return loc;
	}
}
