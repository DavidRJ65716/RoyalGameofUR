package application.model;

public class PlayerTwoMoves {
	
	
	public static void MovePiece(Player player1, Player player2, int x, int y, Dice dice, Board board) {
		
	}
	
	public static void NewPiece(Player player1, Player player2, Dice dice, Board board) {
		
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
			
		}
		
		return loc;
	}
}
