package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import application.Main;

/**
 * MVC model component for handling saving and loading the game state
 * to a text file
 */
public class SaveFile {
	
	/**
	 * Attempts to load a game state from save.txt
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
	
	public static int saveGame() {
		BufferedWriter bw;
		File saveFile = new File("resources/save.txt");
		FileWriter fw;
		
		/* Check if save file already exists */
		if(!saveFile.exists()) {
			try {
				
				/* Create a save file if one doesn't exist */
				saveFile.createNewFile();
			} catch (IOException e) {
				
				/* Couldn't create a save file */
				e.printStackTrace();
				return -1;
			}
		}
		
		/* 
		 * Save file exists at this point:
		 * write gamestate to save file
		 */
		try {
			fw = new FileWriter(saveFile, false);
			bw = new BufferedWriter(fw);
			
			bw.write(Main.prevState.toString());
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
