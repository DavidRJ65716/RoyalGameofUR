package application.model;

/**
 * Controls the main logic and flow of the game
 * It will set up the game and move the pieces as well as call upon save
 *and load
 */
public class GameEngine {
	
	public static Player player1;
	public static Player player2;
	
	
	public static void initPlayers() {
		
		player1 = new Player();
		player2  = new Player();
		
		player1.reset();
		player1.inplay = true;
		player2.reset();
		player2.inplay = false;
	}
	
	public static void move() {
		
		if (player1.inplay) {
			PlayerOneMoves.movePiece(player1, player2);
		}
	}
	
	public static boolean load() {
		
		return true;
	}
	
	public static void save() {
		
	}
	
	public static void rollDice() {
		
	}
	
	public static void newPiece() {
		
	}
	
	
	
	
	
	
}
