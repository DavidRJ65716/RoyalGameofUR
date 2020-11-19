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
import javafx.geometry.VPos; //sponge
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment; //sponge

/**
 * MVC controller for the Game Board view.
 */
public class GameBoardController {
	private GraphicsContext graphics;
	private Image boardImage;
	private Image diceZero;
	private Image diceOne;
	
	/* Board coordinates and cell size*/
	private static int BOARD_X = 0;
	private static int BOARD_Y = 150;
	private static int BOARD_W = 8;
	private static int BOARD_H = 3;
	private static int BOARD_CELL_W = 100;
	private static int BOARD_CELL_H = 100;
	
	/* Roll button coordinates and size */
	private static int ROLL_ONE_X = 300;
	private static int ROLL_ONE_Y = 50;
	private static int ROLL_TWO_X = 300;
	private static int ROLL_TWO_Y = 450;
	private static int ROLL_W = 100;
	private static int ROLL_H = 100;
	
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
		
		/* Check if mouse clicked anywhere on board */
		if ((BOARD_X <= mouseX) && (mouseX < BOARD_X + BOARD_CELL_W * BOARD_W) 
				&& (BOARD_Y <= mouseY) && (mouseY < BOARD_Y + BOARD_CELL_H * BOARD_H)) {
			
			/* Loop through board cells */
			for (int cellX = 0; cellX < BOARD_W; cellX++) {
				for (int cellY = 0; cellY < BOARD_H; cellY++) {
					
					/* Check which board cell was clicked */
					if (((BOARD_CELL_W * cellX + BOARD_X) <= mouseX) && (mouseX < (BOARD_CELL_W * (cellX + 1) + BOARD_X)) 
							&& ((BOARD_CELL_H * cellY + BOARD_Y) <= mouseY) && (mouseY < (BOARD_CELL_H * (cellY + 1) + BOARD_Y)) ) {
							
						/* At this point: user clicked on board at cell (cellX, cellY) */
						System.out.print("GameBoard - board cell (" + cellX + ", " + cellY + ") clicked.\n"); //sponge
					}
				}
			}
		}
		
		/* Check if user clicked on top roll button */
		if (ROLL_ONE_X <= mouseX && mouseX < ROLL_ONE_X + ROLL_W 
				&& ROLL_ONE_Y <= mouseY && mouseY < ROLL_ONE_Y + ROLL_H) {
			System.out.print("GameBoard - Top roll button clicked.\n"); //sponge
		}
		
		/* Check if user clicked on bottom roll button */
		if (ROLL_TWO_X <= mouseX && mouseX < ROLL_TWO_X + ROLL_W 
				&& ROLL_TWO_Y <= mouseY && mouseY < ROLL_TWO_Y + ROLL_H ) {
			System.out.print("GameBoard - Bottom roll button clicked.\n"); //sponge
		}
		
		/* Check if user clicked on top piece stack */
		if (BOARD_X <= mouseX && mouseX < BOARD_X + BOARD_CELL_W 
				&& BOARD_Y - BOARD_CELL_H <= mouseY && mouseY < BOARD_Y ) {
			System.out.print("GameBoard - Top piece stack clicked.\n"); //sponge
		}
		
		/* Check if user clicked on bottom piece stack */
		if (BOARD_X <= mouseX && mouseX < BOARD_X + BOARD_CELL_W 
				&& BOARD_Y + BOARD_CELL_H * BOARD_H <= mouseY && mouseY < BOARD_Y + BOARD_CELL_H * (BOARD_H + 1) ) {
			System.out.print("GameBoard - Bottom piece stack clicked.\n"); //sponge
		}
	}
	
	@FXML
	private void handleOnKeyPressed(KeyEvent event) {
		KeyCode key = event.getCode();

		/* Open pause menu when ESC pressed */
		if (key.equals(KeyCode.ESCAPE)) {
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
		graphics.drawImage(boardImage, BOARD_X, BOARD_Y);
		
		/* Placeholder text just to see where everything on board goes */
		graphics.setFill(Color.WHITE);
		graphics.setTextAlign(TextAlignment.CENTER);
		graphics.setTextBaseline(VPos.CENTER);
		graphics.fillText("PileOne", 50, 100);
		graphics.fillText("PileTwo", 50, 500);
		graphics.fillText("RollBtnOne", 350, 100);
		graphics.fillText("RollBtnTwo", 350, 500);
		graphics.fillText("DiceOne", 450, 100);
		graphics.fillText("DiceOne", 550, 100);
		graphics.fillText("DiceOne", 650, 100);
		graphics.fillText("DiceOne", 750, 100);
		graphics.fillText("DiceTwo", 450, 500);
		graphics.fillText("DiceTwo", 550, 500);
		graphics.fillText("DiceTwo", 650, 500);
		graphics.fillText("DiceTwo", 750, 500);
		/* sponge */
	}
	
	/**
	 * Draws rolled dice onto the screen
	 * @param numberUp the number of dice with a marked corner facing up
	 */
	private void drawDice(int numberUp) {
		int i;
		numberUp -= 1;
		
		if (Main.gameState == GameState.PLAYER_ONE) {
			/* Draw marked dice */
			for (i = 0; i <= numberUp; i++) {
				graphics.drawImage(diceOne, 400+i*100, 50);
			}
			
			/* Draw unmarked dice */
			for (; i <= 3; i++) {
				graphics.drawImage(diceZero, 400+i*100, 50);
			}
		}
		
		if (Main.gameState == GameState.PLAYER_TWO) {
			/* Draw marked dice */
			for (i = 0; i <= numberUp; i++) {
				graphics.drawImage(diceOne, 400+i*100, 450);
			}
			
			/* Draw unmarked dice */
			for (; i <= 3; i++) {
				graphics.drawImage(diceZero, 400+i*100, 450);
			}
		}
	}
}
