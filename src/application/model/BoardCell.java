package application.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class BoardCell {
	private Rectangle rectangle;
	private int cellX;
	private int cellY;
	
	public BoardCell(int cellX, int cellY, Rectangle rectangle) {
		this.cellX = cellX;
		this.cellY = cellY;
		this.rectangle = rectangle;
	}
	
	public int getCellX() {
		return this.cellX;
	}
	
	public int getCellY() {
		return this.cellY;
	}
	
	public double getX() {
		return rectangle.getX();
	}

	public double getY() {
		return rectangle.getY();
	}
	
	public boolean contains(Point2D point2D) {
		return rectangle.contains( point2D);
	}
}
