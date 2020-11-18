/*
 * application/control/PauseMenuController.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application.control;

import application.Main;
import application.model.SaveFile;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * MVC controller for the Pause Menu view.
 */
public class PauseMenuController implements EventHandler<MouseEvent> {
	@FXML
	Label errorLabel;
	
	@Override
	public void handle( MouseEvent event ) {
		String source = ((Button) event.getSource()).getId();
		
		/* Check which button was pressed */
		switch ( source ) {
		case "resumeGameButton":
			System.out.print("PauseMenu - Resume Game button pressed.\n"); //sponge
			Main.changeView(Main.prevState);
			break;
			
		case "saveAndQuitButton":
			System.out.print("PauseMenu - Save and Quit button pressed.\n"); //sponge
			if (SaveFile.saveGame() < 0) {
				errorLabel.setText("Error saving game.");
				break;
			}
			
			errorLabel.setText("Game successfully saved.");
			Platform.exit();
			break;
			
		case "saveButton":
			System.out.print("PauseMenu - Save button pressed.\n"); //sponge
			if (SaveFile.saveGame() < 0) {
				errorLabel.setText("Error saving game.");
			}
			
			errorLabel.setText("Game successfully saved.");
			break;
			
		case "quitButton":
			System.out.print("PauseMenu - Quit button pressed.\n"); //sponge
			Platform.exit();
			break;
			
		default:
			break;
		}
	}
}
