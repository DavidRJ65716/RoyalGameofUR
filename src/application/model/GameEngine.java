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
		dices.reset();
	}
	
	/*Moves pieces with given coordinates*/
	public static void move(int x, int y) {
				
		if (player1.inplay && dices.flag) {
			PlayerOneMoves.MovePiece(player1, player2, x, y, dices, board);

		} else if (player2.inplay && dices.flag) {
			PlayerTwoMoves.MovePiece(player1, player2, x, y, dices, board);
		}
	}
	
	/*Loads a saved game 
	 * 
	 *Does not work 
	 */
	public static boolean Load() {
		
		initPlayers();
		
		return true;
	}
	
	/*Saves current Game
	 *
	 * Does not work
	 */
	public static boolean Save() {
		
		return true;
	}
	
	/*Rolls the dice*/
	public static void RollDice() {
		
		if (!dices.flag) {
			dices.rollDice();
			dices.flag = true;
		}
		
		if (dices.total == 0) {//checks for if dices total is 0
			
			if (player1.inplay) {
				player1.inplay = false;
				player2.inplay = true;
			} else {
				player1.inplay = true;
				player2.inplay = false;
			}
			
			dices.reset();
		}
	}
	
	/*Adds a new piece on to the board*/
	public static void NewPiece(int player) {
		
		if (player1.inplay && player == 1  && dices.flag) {
			PlayerOneMoves.NewPiece(player1, player2, dices, board);
		}
		if (player2.inplay && player == 2 && dices.flag) {
			PlayerTwoMoves.NewPiece(player1, player2, dices, board);
		}
	}
}
