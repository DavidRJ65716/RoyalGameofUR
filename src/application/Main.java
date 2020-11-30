/*
 * application/Main.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application;
	
import java.io.IOException;

import application.control.GameBoardController;
import application.control.GameOverController;
import application.model.GameState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	/* Game state and window size */
	public static GameState gameState = null;
	public static GameState prevState = null;
	public static int WINDOW_HEIGHT = 600;
	public static int WINDOW_WIDTH = 800;
	
	/* Main stage and scenes for each view */
	private static Scene gameBoardScene;
	private static Scene mainMenuScene;
	private static Scene pauseMenuScene;
	private static Scene gameOverScene;
	private static Stage mainStage;
	
	/* Controller references */
	private static GameOverController gameOverController;
	private static GameBoardController gameBoardController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			/* Load each view into memory */
			Parent mainMenuView = FXMLLoader.load(getClass().getResource( "view/MainMenu.fxml"));
			mainMenuScene = new Scene( mainMenuView, WINDOW_WIDTH, WINDOW_HEIGHT );
			mainMenuScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Parent pauseMenuView = FXMLLoader.load(getClass().getResource( "view/PauseMenu.fxml"));
			pauseMenuScene = new Scene( pauseMenuView, WINDOW_WIDTH, WINDOW_HEIGHT );
			pauseMenuScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource( "view/GameOver.fxml"));
			Parent gameOverView = gameOverLoader.load();
			gameOverScene = new Scene(gameOverView, WINDOW_WIDTH, WINDOW_HEIGHT);
			gameOverController = gameOverLoader.getController();
			
			FXMLLoader gameBoardLoader = new FXMLLoader(getClass().getResource( "view/GameBoard.fxml"));
			Parent gameBoardView = gameBoardLoader.load();
			gameBoardScene = new Scene(gameBoardView, WINDOW_WIDTH, WINDOW_HEIGHT);
			gameBoardController = gameBoardLoader.getController();
			
			/* Set initial game state and grab primaryStage */
			mainStage = primaryStage;
			
			/* Change to initial view */
			changeView( GameState.MAIN );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes and presents mainStage according to newState,
	 * and updates prevState.
	 * @param newState Changes the view to match this state
	 * @return 0 if successful, -1 otherwise
	 */
	public static int changeView( GameState newState ) {
		prevState = gameState;
		gameState = newState;
		
		switch ( gameState ) {
		case MAIN:
			Main.mainStage.hide();
			Main.mainStage.setScene(Main.mainMenuScene);
			Main.mainMenuScene.getRoot().requestFocus();
			Main.mainStage.show();
			return 0;
			
		case PAUSE:
			Main.mainStage.hide();
			Main.mainStage.setScene(Main.pauseMenuScene);
			Main.pauseMenuScene.getRoot().requestFocus();
			Main.mainStage.show();
			return 0;
			
		case PLAYER_ONE:
		case PLAYER_TWO:
			gameBoardController.update();
			
			Main.mainStage.hide();
			Main.mainStage.setScene(Main.gameBoardScene);
			Main.gameBoardScene.getRoot().requestFocus();
			Main.mainStage.show();
			return 0;
			
		case PLAYER_ONE_WIN:
		case PLAYER_TWO_WIN:
			gameOverController.init(gameState);
			
			Main.mainStage.hide();
			Main.mainStage.setScene(Main.gameOverScene);
			Main.gameOverScene.getRoot().requestFocus();
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
