/*
 * application/control/GameBoardController.java
 * 
 * Group 5
 * Royal Game of Ur
 */
package application.control;

import java.util.ArrayList;

import application.Main;
import application.model.BoardCell;
import application.model.GameState;
import application.model.GameEngine;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.VPos; //sponge
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment; //sponge

/**
 * MVC controller for the Game Board view.
 */
public class GameBoardController {
	private static GraphicsContext graphics;
	private static Image boardImage;
	private static Image diceZero;
	private static Image diceOne;
	
	/* Board coordinates and cell size*/
	private static int BOARD_X = 0;
	private static int BOARD_Y = 150;
	private static int BOARD_W = 8;
	private static int BOARD_H = 3;
	private static int BOARD_CELL_W = 100;
	private static int BOARD_CELL_H = 100;
	
	/* Positions of different objects on the canvas */
	private static ArrayList<BoardCell> board = new ArrayList<>();
	private static Rectangle playerOneRoll = new Rectangle(300, 50, 100, 100);
	private static Rectangle playerTwoRoll = new Rectangle(300, 450, 100, 100);
	private static Rectangle playerOneStack = new Rectangle(0, 50, 100, 100);
	private static Rectangle playerTwoStack = new Rectangle(0, 450, 100, 100);
	
	@FXML
	private Canvas canvas;

	/**
	 * Initialize board assets and draw initial state
	 */
	@FXML
	public void initialize() {
		boardImage = new Image("UrBoard.png");
		diceZero = new Image("diceZero.png");
		diceOne = new Image("diceOne.png");
		graphics = canvas.getGraphicsContext2D();
		
		/*
		 * Loop through each cell of the board and create
		 * a BoardCell object with a Rectangle for position
		 * on the canvas, and coordinates for position on the
		 * board
		 */
		for (int i = 0; i < BOARD_W; i++) {
			for (int j = 0; j < BOARD_H; j++) {
				int xPos = BOARD_X + BOARD_CELL_W * i;
				int yPos = BOARD_Y + BOARD_CELL_H * j;
				
				Rectangle rect = new Rectangle(xPos, yPos, BOARD_CELL_W, BOARD_CELL_H);
				board.add(new BoardCell(i, j, rect));
			}
		}
		
		draw();
	}
	
	@FXML
	private void handleOnMouseClicked(MouseEvent event) {
		Point2D mouse = new Point2D(event.getX(), event.getY());
		
		/* loop through all cells to see if one was clicked */
		for (BoardCell cell : board) {
			if (cell.contains(mouse)) {
				System.out.print("GameBoard - board cell (" + cell.getCellX() + ", " + cell.getCellY() + ") clicked.\n"); //sponge
				GameEngine.move(cell.getCellX(), cell.getCellY());
			}
		}
		
		/* check player one roll */
		if (playerOneRoll.contains(mouse)) {
			System.out.print("GameBoard - Player One roll button clicked.\n"); //sponge
			GameEngine.RollDice();
		}
		
		/* check player two roll */
		if (playerTwoRoll.contains(mouse)) {
			System.out.print("GameBoard - Player Two roll button clicked.\n"); //sponge
			GameEngine.RollDice();
		}
		
		/* check player one stack*/
		if (playerOneStack.contains(mouse)) {
			System.out.print("GameBoard - Player One piece stack clicked.\n"); //sponge
			GameEngine.NewPiece(1);
		}
		
		/* check player two stack */
		if (playerTwoStack.contains(mouse)) {
			System.out.print("GameBoard - Player Two piece stack clicked.\n"); //sponge
			GameEngine.NewPiece(2);
		}
		
		update();
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
	
	private void update() {
		graphics.clearRect(0,  0, canvas.getWidth(), canvas.getHeight());
		draw();
		drawPieces();
	}
	
	
	
	/**
	 * Method to draw the board view to the screen
	 */
	private void draw() {
		graphics.setFill(Color.DARKCYAN);
		graphics.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		graphics.drawImage(boardImage, BOARD_X, BOARD_Y);
		
		/* Placeholder text just to see where everything on the board goes */
		graphics.setFill(Color.WHITE);
		graphics.setTextAlign(TextAlignment.CENTER);
		graphics.setTextBaseline(VPos.CENTER);
		graphics.fillText("P1 Pile", 50, 100);
		graphics.fillText("P2 Pile", 50, 500);
		graphics.fillText("P1 Roll", 350, 100);
		graphics.fillText("P2 Roll", 350, 500);
		graphics.fillText("P1 Dice", 450, 100);
		graphics.fillText("P1 Dice", 550, 100);
		graphics.fillText("P1 Dice", 650, 100);
		graphics.fillText("P1 Dice", 750, 100);
		graphics.fillText("P2 Dice", 450, 500);
		graphics.fillText("P2 Dice", 550, 500);
		graphics.fillText("P2 Dice", 650, 500);
		graphics.fillText("P2 Dice", 750, 500);
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
	
	private void drawPieces() {
		for (BoardCell cell : board) {
			int cellY = cell.getCellY();
			int cellX = cell.getCellX();
			int loc;

			/* Middle row */
			if (cellY == 1) {
				loc = cellX + 4;
			} 

			/* First safe zone */
			else if (cellX < 4) {
				loc = 3 - cellX;
			} 

			/* Second safe zone*/
			else if (cellX > 5){
				loc = 19 - cellX;
			}

			/* End square */
			else if (cellX == 5) {
				continue; //TODO
			}

			/* Pile */
			else {
				continue; //TODO
			}

			if (cellY <= 1 && GameEngine.player1.getPlayerB(loc) > 0) {
				graphics.setFill(Color.GRAY);
				graphics.fillOval(cell.getX()+25, cell.getY()+25, 50, 50);
			}

			if (cellY >= 1 && GameEngine.player2.getPlayerB(loc) > 0) {
				graphics.setFill(Color.BLACK);
				graphics.fillOval(cell.getX()+25, cell.getY()+25, 50, 50);
			}
		}
	}
}
