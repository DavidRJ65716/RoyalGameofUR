/*
 * application/control/MainMenuController.java
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
 * MVC controller for the Main Menu view.
 */
public class MainMenuController implements EventHandler<MouseEvent> {
	@Override
	public void handle( MouseEvent event ) {
		String source = ((Button) event.getSource()).getId();
		
		switch ( source ) {
		case "newGameButton":
			System.out.print("MainMenu - New Game button pressed.\n"); //sponge
			break;
		
		case "loadGameButton":
			System.out.print("MainMenu - Load Game button pressed.\n"); //sponge
			
			/* Testing pause menu */
			Main.gameState = GameState.PAUSE;
			Main.changeView();
			/* sponge */
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
