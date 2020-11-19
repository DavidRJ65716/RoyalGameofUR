/*
 * application/control/GameBoardController.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application.control;

import application.Main;
import application.model.GameState;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * MVC controller for the Game Board view.
 */
public class GameBoardController {
	private GraphicsContext graphics;
	private Image boardImage;
	private Image diceZero;
	private Image diceOne;
	
	/* Board coordinates on canvas */
	private static int BOARD_X = 0;
	private static int BOARD_Y = 150;
	private static int BOARD_CELL_W = 100;
	private static int BOARD_CELL_H = 100;
	
	@FXML
	private Canvas canvas;

	/**
	 * Initialize board assets and draw initial state
	 */
	@FXML
	public void initialize() {
		this.boardImage = new Image("UrBoard.png");
		this.diceZero = new Image("diceZero.png");
		this.diceOne = new Image("diceOne.png");
		this.graphics = canvas.getGraphicsContext2D();
		
		draw();
	}
	
	@FXML
	private void handleOnMouseClicked(MouseEvent event) {
		int mouseX = ((int) event.getX());
		int mouseY = ((int) event.getY());
		System.out.print("GameBoard - coordinates (" + mouseX + ", " + mouseY + ") clicked.\n"); //sponge
		drawDice(0);
		
		/* Check if mouse clicked anywhere on board */
		if((BOARD_X <= mouseX) && (mouseX < BOARD_X + BOARD_CELL_W * 8) 
				&& (BOARD_Y <= mouseY) && (mouseY < BOARD_Y + BOARD_CELL_H * 3)) {
			
			/* Loop through board cells */
			for( int cellX = 0; cellX < 8; cellX++) {
				for( int cellY = 0; cellY < 3; cellY++) {
					
					/* Check which board cell was clicked */
					if(	((100 * cellX + BOARD_X) <= mouseX) && (mouseX < (100 * (cellX + 1) + BOARD_X)) 
							&& ((100 * cellY + BOARD_Y) <= mouseY) && (mouseY < (100 * (cellY + 1) + BOARD_Y)) ) {
							
						/* At this point: user clicked on board at cell (cellX, cellY) */
						System.out.print("GameBoard - board cell (" + cellX + ", " + cellY + ") clicked.\n"); //sponge
					}
				}
			}
		}
		
		/* Check if user clicked on top piece stack */
		if(	BOARD_X <= mouseX && mouseX < BOARD_X + BOARD_CELL_W 
				&& BOARD_Y-BOARD_CELL_H <= mouseY && mouseY < BOARD_Y ) {
			System.out.print("GameBoard - Top piece stack clicked.\n"); //sponge
		}
		
		/* Check if user clicked on bottom piece stack */
		if( BOARD_X <= mouseX && mouseX < BOARD_X + BOARD_CELL_W 
				&& BOARD_Y+BOARD_CELL_H * 3 <= mouseY && mouseY < BOARD_Y+BOARD_CELL_H * 4 ) {
			System.out.print("GameBoard - Bottom piece stack clicked.\n"); //sponge
			
		}
	}
	
	@FXML
	private void handleOnKeyPressed(KeyEvent event) {
		KeyCode key = event.getCode();

		/* Open pause menu when ESC pressed */
		if( key.equals(KeyCode.ESCAPE)) {
			System.out.print( "GameBoard - " + key.toString() + " pressed.\n"); //sponge
			Main.changeView(GameState.PAUSE);
		}
	}
	
	/**
	 * Method to draw the board view to the screen
	 */
	private void draw() {
		graphics.setFill(Color.DARKCYAN);
		graphics.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		graphics.setGlobalAlpha( 1.0 );
		graphics.drawImage(boardImage, BOARD_X, BOARD_Y);
	}
	
	/**
	 * Draws rolled dice onto the screen
	 * @param numberUp the number of dice with a marked corner facing up
	 */
	private void drawDice(int numberUp) {
		int i;
		numberUp -= 1;
		
		if(Main.gameState == GameState.PLAYER_ONE) {
			/* Draw marked dice */
			for(i = 0; i <= numberUp; i++) {
				graphics.drawImage(diceOne, 400+i*100, 50);
			}
			
			/* Draw unmarked dice */
			for(; i <= 3; i++) {
				graphics.drawImage(diceZero, 400+i*100, 50);
			}
		}
		
		if(Main.gameState == GameState.PLAYER_TWO) {
			/* Draw marked dice */
			for(i = 0; i <= numberUp; i++) {
				graphics.drawImage(diceOne, 400+i*100, 450);
			}
			
			/* Draw unmarked dice */
			for(; i <= 3; i++) {
				graphics.drawImage(diceZero, 400+i*100, 450);
			}
		}
	}
}
