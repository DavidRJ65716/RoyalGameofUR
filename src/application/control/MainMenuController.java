/*
 * application/control/MainMenuController.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application.control;


import application.Main;
import application.model.GameState;
import application.model.SaveFile;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * MVC controller for the Main Menu view.
 */
public class MainMenuController implements EventHandler<MouseEvent> {
	
	@FXML
	Label errorLabel;

	@Override
	public void handle( MouseEvent event ) {
		String source = ((Button) event.getSource()).getId();
		
		/* Check which button was pressed */
		switch ( source ) {
		case "newGameButton":
			System.out.print("MainMenu - New Game button pressed.\n"); //sponge
			
			Main.changeView(GameState.PLAYER_ONE);
			break;
		
		case "loadGameButton":
			System.out.print("MainMenu - Load Game button pressed.\n"); //sponge
			if ( SaveFile.loadGame() < 0 ) {
				errorLabel.setText("Error loading save file.");
			}
			break;
			
		case "quitButton":
			System.out.print("MainMenu - Quit button pressed.\n"); //sponge
			Platform.exit();
			break;
			
		default:
			break;
		}
	}



}
