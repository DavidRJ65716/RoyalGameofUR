package application.model;

/**
 * Controls the main logic and flow of the game
 * It will set up the game and move the pieces as well as call upon save
 *and load
 */
public class GameEngine {
	
	public static Board board;
	public static Player player1;
	public static Player player2;
	public static Dice dices;
	
	/* Allocates memory for player1 and player2 builds the board*/
	public static void initPlayers() {
		
		board = new Board();
		dices = new Dice();
		player1 = new Player();
		player2  = new Player();
		
		player1.reset();
		player1.inplay = true;
		player2.reset();
		player2.inplay = false;
		
		print();
	}
	
	/*Moves pieces with given coordinates*/
	public static void move(int x, int y) {
				
		if (player1.inplay) {
			PlayerOneMoves.MovePiece(player1, player2, x, y, 4, board);
		} else if (player2.inplay) {
			PlayerTwoMoves.MovePiece(player1, player2, x, y, 1, board);
		}
		
		print();
	}
	
	/*Loads a saved game*/
	public static boolean Load() {
		
		board = new Board();
		dices = new Dice();
		player1 = new Player();
		player2  = new Player();
		
		return true;
	}
	
	/*Saves current Game*/
	public static boolean Save() {
		
		return true;
	}
	
	/*Rolls the dice*/
	public static void RollDice() {
		
		
		
	}
	
	/*Adds a new piece on to the board*/
	public static void NewPiece(int player) {
		
		if (player1.inplay || player == 1) {
			PlayerOneMoves.NewPiece(player1, player2, 4, board);
		}
		if (player2.inplay || player == 2) {
			PlayerTwoMoves.NewPiece(player1, player2, 2, board);
		}
		
		print();
	}
	
	//To be removed 
	public static void print() {
		
		for (int i = 0; i < 14; i++) {
			System.out.print(player1.getPlayerB(i) + ",");
		}
		
		System.out.println(player1.inplay + "," + player1.pieces);
		
		for (int i = 0; i < 14; i++) {
			System.out.print(board.getBoard(i) + ",");
		}
		
		System.out.println("");
		
		for (int i = 0; i < 14; i++) {
			System.out.print(player2.getPlayerB(i) + ",");
		}
		
		System.out.println(player2.inplay + "," + player2.pieces);
	}
}
