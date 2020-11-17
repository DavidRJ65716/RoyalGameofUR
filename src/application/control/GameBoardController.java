/*
 * application/control/GameBoardController.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application.control;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * MVC controller for the Game Board view.
 */
public class GameBoardController{
	private GraphicsContext graphics;
	private Image boardImage;
	
	@FXML
	private Canvas canvas;

	/**
	 * Initialize board assets and draw initial state
	 */
	@FXML
	public void initialize() {
		this.boardImage = new Image("UrBoard.png");
		this.graphics = canvas.getGraphicsContext2D();
		draw();
	}
	
	/**
	 * Method to draw the board view to the screen
	 */
	public void draw() {
		graphics.setFill(Color.ALICEBLUE);
		graphics.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		graphics.setGlobalAlpha( 1.0 );
		graphics.drawImage(boardImage, 0, 300);
	}
}
