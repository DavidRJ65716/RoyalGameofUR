package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import application.Main;

/**
 * MVC model component for loading a gamestate from a text file
 */
public class LoadGame {
	
	/**
	 * Attempts to load a gamestate from save.txt
	 * @return 0 if successful, -1 otherwise
	 */
	public static int loadGame() {
		File saveFile = new File("resources/save.txt");
		GameState gameState = null;
		Scanner scanner = null;
		String data;
		
		/* Save file doesn't exist */
		if( !saveFile.exists() ) {
			return -1;
		}
		
		try {
			scanner = new Scanner(saveFile);
			
			/* Line 1 - gameState */
			data = scanner.nextLine().trim();
			gameState = GameState.parseString(data);
			if(gameState == null) {
				return -1;
			}
			
			
		} catch (FileNotFoundException e) {
			
			/* Save file doesn't exist */
			e.printStackTrace();
			return -1;
		} catch (NoSuchElementException e) {
			
			/* Invalid save file */
			e.printStackTrace();
			return -1;
		} finally {
			scanner.close();
		}
		
		/* Change the view after reading the entire file */
		Main.changeView(gameState);
		
		return 0;
	}
}
