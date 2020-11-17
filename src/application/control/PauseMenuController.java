/*
 * application/control/PauseMenuController.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application.control;

import application.Main;
import application.model.GameState;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * MVC controller for the Pause Menu view.
 */
public class PauseMenuController implements EventHandler<MouseEvent> {
	@Override
	public void handle( MouseEvent event ) {
		String source = ((Button) event.getSource()).getId();
		
		switch ( source ) {
		case "resumeGameButton":
			System.out.print("PauseMenu - Resume Game button pressed.\n"); //sponge
			
			/* Testing pause menu */
			Main.gameState = GameState.MAIN;
			Main.changeView();
			/* sponge */
			break;
		case "saveAndQuitButton":
			System.out.print("PauseMenu - Save and Quit button pressed.\n"); //sponge
			break;
		case "saveButton":
			System.out.print("PauseMenu - Save button pressed.\n"); //sponge
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
