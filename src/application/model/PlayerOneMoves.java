package application.model;

/**
 * Moves the pieces for player1
 */
public class PlayerOneMoves {
	
	/*
	 * Moves pieces already on the board
	 * 
	 * @perm Player player1 Player player2 int x int y int dice Board board
	 */
	public static void MovePiece(Player player1, Player player2, int x, int y, Dice dices, Board board) {
		
		int loc = cellConverter(x, y);
		int newLoc = loc + dices.total;
		
		if (loc != -1) {
			if ((newLoc < 14) && (player2.getPlayerB(newLoc) == 0) && (board.getBoard(newLoc) == 0)
					&& (player1.getPlayerB(newLoc) == 0) && (player1.getPlayerB(loc) == 1)
						) {// No second player no event
				
			
				player1.setPlayerB(loc, newLoc, 1);
				player1.inplay = false;
				player2.inplay = true;
				dices.reset();
			} else if ((newLoc < 14) && (board.getBoard(newLoc) == 1)&& (player1.getPlayerB(newLoc) == 0) 
						&& (player1.getPlayerB(loc) == 1)) {// No second player first event
			
				player1.setPlayerB(loc, newLoc, 1);
				dices.reset();
			} else if ((newLoc < 12) && (newLoc > 3) && (player2.getPlayerB(newLoc) == 2) && (board.getBoard(newLoc) != 2)
					&& (player1.getPlayerB(newLoc) == 0) && (player1.getPlayerB(loc) == 1)
						) {// Second player covers for second event
				
				player1.setPlayerB(loc, newLoc, 1);
				player2.setPlayerB(newLoc, newLoc, 0);
				player2.pieces++;
				player1.inplay = false;
				player2.inplay = true;
				dices.reset();
			} else if ((newLoc < 14) && (player2.getPlayerB(newLoc) != 2) && (board.getBoard(newLoc) == 2)
					&& (player1.getPlayerB(newLoc) == 0) && (player1.getPlayerB(loc) == 1)
						) {// no second player second event
				
				player1.setPlayerB(loc, newLoc, 1);
			} else if ((newLoc < 14) && (board.getBoard(newLoc) == 3) && (player1.getPlayerB(newLoc) == 0)
					&& (player1.getPlayerB(loc) == 1)) {// no second player third event
				
				player1.setPlayerB(loc, newLoc, 1);
				dices.reset();
			} else if (newLoc >= 14) {
				if ((newLoc - 13) == 1) {
					
					player1.setPlayerB(loc, loc, 0);
					player1.offpiecese++;
					player1.inplay = false;
					player2.inplay = true;
					dices.reset();
				}else {
					
					player1.inplay = false;
					player2.inplay = true;
				}
			}
		}
	}
	
	/*
	 * Adds new pieces to the board
	 * 
	 * @perm Player player1 Player player2 int x int y int dice Board board
	 */
	public static void NewPiece(Player player1, Player player2, Dice dices, Board board) {
		
		int Loc = dices.total - 1;	
		
		if ((board.getBoard(Loc) == 0) && (player1.getPlayerB(Loc) == 0) && (player1.pieces > 0)) {// Checks for piece no event
			player1.setPlayerB(Loc, Loc, 1);
			player1.inplay = false;
			player2.inplay = true;
			player1.pieces--;
			dices.reset();
		} else if ((board.getBoard(Loc) == 1) && (player1.getPlayerB(Loc) == 0) && (player1.pieces > 0)) { // Checks for piece first event
			player1.setPlayerB(Loc, Loc, 1);
			player1.pieces--;
			dices.reset();
		}
	}
	
	
	/*
	 * Converts coordinates to work with an array
	 * 
	 * @perm int x int y
	 */
	public static int cellConverter(int x, int y) {
		
		int loc = -1;
		
		if (y == 0) {
			
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
