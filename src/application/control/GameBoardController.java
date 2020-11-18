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
		this.graphics = canvas.getGraphicsContext2D();
		
		canvas.getParent().setOnKeyPressed(this::handleOnKeyPressed);
		
		draw();
	}
	
	@FXML
	private void handleOnMouseClicked(MouseEvent event) {
		int mouseX = ((int) event.getX());
		int mouseY = ((int) event.getY());
		System.out.print(mouseX + ", " + mouseY + "\n\n"); //sponge
		
		/* Check if mouse clicked anywhere on board */
		if( BOARD_X <= mouseX && mouseX < BOARD_X + BOARD_CELL_W*8 &&
			BOARD_Y <= mouseY && mouseY < BOARD_Y + BOARD_CELL_H*3) {
			
			/* Loop through board cells */
			for( int x = 0; x < 8; x++) {
				for( int y = 0; y < 3; y++) {
					
					/* Check which board cell was clicked */
					if( ((100 * x + BOARD_X) <= mouseX) && (mouseX < (100 * (x+1) + BOARD_X)) &&
						((100 * y + BOARD_Y) <= mouseY) && (mouseY < (100 * (y+1) + BOARD_Y)) ) {
						/* At this point: user clicked on board at cell (x, y) */
					}
				}
			}
		}
	}
	
	@FXML
	private void handleOnKeyPressed(KeyEvent event) {
		KeyCode key = event.getCode();

		/* Open pause menu when ESC pressed */
		if( key.equals(KeyCode.ESCAPE)) {
			Main.changeView(GameState.PAUSE);
		}
	}
	
	/**
	 * Method to draw the board view to the screen
	 */
	public void draw() {
		graphics.setFill(Color.ALICEBLUE);
		graphics.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		graphics.setGlobalAlpha( 1.0 );
		graphics.drawImage(boardImage, BOARD_X, BOARD_Y);
	}
}
