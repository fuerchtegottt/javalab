package de.g3ll3rt.japfelsee.basic;

import java.awt.Color;
import java.awt.Dimension;

import de.g3ll3rt.japfelsee.ui.canvas.JPaintComponent;
import de.g3ll3rt.japfelsee.ui.canvas.PaintableLine;
import de.g3ll3rt.japfelsee.ui.canvas.PaintableRect;

public class BasicScreen extends JPaintComponent {
	public static Color LIGHTBLUE = new Color(112,89,198);
	private Color col = Color.BLACK;
	public BasicScreen() {
		super();
		this.setPreferredSize(new Dimension(320,200));
	}
	
	public void drawTestScreen() {
	      paintObj(new PaintableLine(Color.RED, 1,1, 320, 200));
		  paintObj(new PaintableLine(Color.BLUE, 1, 200, 320, 1));
	}
	
	public void drawPixel(int x, int y) {
		paintObj(new PaintableLine(col, x,y,x,y));
	}
	
	public void drawLine(int x1, int y1, int x2, int y2) {
		paintObj(new PaintableLine(col, x1,y1,x2,y2));
	}
	
	public void setColor(Color col) {
		this.col = col;
	}
	
	public void setBackground(Color col) {
		paintObj(new PaintableRect(col, 0, 0, 320, 200));
	}
	
}
