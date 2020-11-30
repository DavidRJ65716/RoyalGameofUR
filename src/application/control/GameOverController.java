package application.control;

import application.Main;
import application.model.GameState;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameOverController implements EventHandler<MouseEvent> {
	@FXML
	Label gameOverLabel;
	
	@Override
	public void handle( MouseEvent event ) {
		String source = ((Button) event.getSource()).getId();
		
		switch ( source ) {
		case "mainMenuButton":
			System.out.print("GameOverMenu - Main Menu button pressed.\n"); //sponge
			Main.changeView(GameState.MAIN);
			break;
			
		case "quitButton":
			System.out.print("GameOverMenu - Quit button pressed.\n"); //sponge
			Platform.exit();
			break;
		}
	}
	
	public void init(GameState state) {
		switch(state) {
		case PLAYER_ONE_WIN:
			gameOverLabel.setText("Player One Won");
			break;
			
		case PLAYER_TWO_WIN:
			gameOverLabel.setText("Player Two Won");
			break;
			
		default:
			break;
		}
	}
}
