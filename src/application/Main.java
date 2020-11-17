/*
 * application/Main.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application;
	
import java.io.IOException;

import application.model.GameState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	/* Game state and window size */
	public static GameState gameState;
	public static int WINDOW_HEIGHT = 600;
	public static int WINDOW_WIDTH = 800;
	
	/* Main stage and scenes for each view */
	private static Scene gameBoardScene;
	private static Scene mainMenuScene;
	private static Scene pauseMenuScene;
	private static Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			/* Load each view into memory */
			Parent mainMenuView = FXMLLoader.load(getClass().getResource( "view/MainMenu.fxml"));
			mainMenuScene = new Scene( mainMenuView, WINDOW_WIDTH, WINDOW_HEIGHT );
			
			Parent pauseMenuView = FXMLLoader.load(getClass().getResource( "view/PauseMenu.fxml"));
			pauseMenuScene = new Scene( pauseMenuView, WINDOW_WIDTH, WINDOW_HEIGHT );
			
			Parent gameBoardView = FXMLLoader.load(getClass().getResource( "view/GameBoard.fxml"));
			gameBoardScene = new Scene( gameBoardView, WINDOW_WIDTH, WINDOW_HEIGHT );
			
			/* Set initial game state and grab primaryStage */
			gameState = GameState.MAIN;
			mainStage = primaryStage;
			
			/* Change to initial view */
			changeView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes and presents mainStage according to the value of
	 * gameState.
	 * @return 0 if successful, -1 otherwise
	 */
	public static int changeView() {
		switch ( Main.gameState ) {
		case MAIN:
			Main.mainStage.hide();
			Main.mainStage.setScene(Main.mainMenuScene);
			Main.mainStage.show();
			return 0;
		case PAUSE:
			Main.mainStage.hide();
			Main.mainStage.setScene(Main.pauseMenuScene);
			Main.mainStage.show();
			return 0;
		case PLAYER_ONE:
		case PLAYER_TWO:
			Main.mainStage.hide();
			Main.mainStage.setScene( Main.gameBoardScene );
			Main.mainStage.show();
			return 0;
		default:
			return -1;
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
