package application.model;
/**
 * Moves pieces for player two 
 */
public class PlayerTwoMoves {
	
	/*
	 * Moves pieces already on the board
	 * 
	 * @Player player1
	 * @Player player2
	 * @int x
	 * @int y
	 * @int dice@
	 * Board board
	 */
	public static void MovePiece(Player player1, Player player2, int x, int y, Dice dices, Board board) {
		
		int loc = cellConverter(x, y);
		int newLoc = loc + dices.total;
		
		if (loc != -1) {
			if ((newLoc < 14) && (player2.getPlayerB(newLoc) == 0) && (board.getBoard(newLoc) == 0)
					&& (player1.getPlayerB(newLoc) == 0) && (player2.getPlayerB(loc) == 2)) {// No second player no event
			
				player2.setPlayerB(loc, newLoc, 2);
				player1.inplay = true;
				player2.inplay = false;
				dices.reset();
			} else if ((newLoc < 14) && (board.getBoard(newLoc) == 1) && (player2.getPlayerB(newLoc) == 0) 
						&& (player2.getPlayerB(loc) == 2)) {// No first player first event
			
				player2.setPlayerB(loc, newLoc, 2);
				dices.reset();
			} else if ((newLoc < 12) && (newLoc > 3) && (player1.getPlayerB(newLoc) == 1) && (board.getBoard(newLoc) != 2)
					&& (player2.getPlayerB(newLoc) == 0) && (player2.getPlayerB(loc) == 2)
						) {// first player covers for second event
			
				player2.setPlayerB(loc, newLoc, 2);
				player1.setPlayerB(newLoc, newLoc, 0);
				player1.pieces++;
				player2.inplay = false;
				player1.inplay = true;
				dices.reset();
			} else if ((newLoc < 14) && (player2.getPlayerB(newLoc) != 2) && (board.getBoard(newLoc) == 2)
					&& (player2.getPlayerB(newLoc) == 0) && (player2.getPlayerB(loc) == 2)
						) {// no second player second event
				
				player2.setPlayerB(loc, newLoc, 2);
				dices.reset();
			} else if ((newLoc < 14) && (board.getBoard(newLoc) == 3) && (player2.getPlayerB(newLoc) == 0) 
					&& (player2.getPlayerB(loc) == 2)) {// no second player third event
				
				player2.setPlayerB(loc, newLoc, 2);
				dices.reset();
			} else if (newLoc >= 14) {
				if ((newLoc - 13) == 1) {
					
					player2.setPlayerB(loc, loc, 0);
					player2.offpiecese++;
					player2.inplay = false;
					player1.inplay = true;
					dices.reset();
				} else {
					
					player2.inplay = false;
					player1.inplay = true;
				}
				
			}
		}
		
	}
	
	/*
	 * Add pieces to the board 
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
		
		if ((board.getBoard(Loc) == 0) && (player2.getPlayerB(Loc) == 0) && (player2.pieces > 0)) {// Checks for piece no event
			player2.setPlayerB(Loc, Loc, 2);
			player2.inplay = false;
			player1.inplay = true;
			player2.pieces -= 1;
			dices.reset();
		} else if ((board.getBoard(Loc) == 1) && (player2.getPlayerB(Loc) == 0) && (player2.pieces > 0)) { // Checks for piece first event
			player2.setPlayerB(Loc, Loc, 2);
			player2.pieces -= 1;
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
				
		if (y == 2) {
			
			if(x <=3) {
				
				loc = (x - 3) * (-1);
			} else {
				
				if(x == 7){
					
					loc = x + 5;
				} else {
					
					loc = x + 7;
				}
			}
		} else if (y == 1) {
			
			loc = x + 4;
		} else {
			
			loc = -1;
		}
		
		return loc;
	}
}
