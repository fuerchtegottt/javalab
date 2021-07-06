package de.g3ll3rt.japfelsee;

import java.awt.Color;

import de.g3ll3rt.japfelsee.basic.BasicScreen;
import de.g3ll3rt.japfelsee.basic.CMath;

public class FractalCore {
	public static int LIGHTMODE_DAY = 0;
	public static int LIGHTMODE_NIGHT = 1;
	BasicScreen screen = null;
	ViewPosition pos = null;
	int lightMode;
  public FractalCore(BasicScreen screen, ViewPosition pos, int lightMode ) {
	  this.screen = screen;
	  this.pos = pos;
	  this.lightMode = lightMode;
  }
  
  public void generateLandscape() {
	  if (lightMode == LIGHTMODE_DAY) {
	    drawDayHorizon();  
	  } else {
		drawNightHorizon();
	  }
	  screen.drawPixel(50, 50);
  }
  
  private void drawDayHorizon() {
	  screen.setBackground(BasicScreen.LIGHTBLUE);
	  screen.setColor(Color.DARK_GRAY);
	  
	  screen.drawLine(0, 97, 320, 97);
	  
  }
  
  private void drawNightHorizon() {
	  screen.setBackground(Color.black);
	  int a = 0;
	  int b1 = 0;
	  screen.setColor(Color.WHITE);
	  for (int i = 3; i <= 97; i++) {
		  a = CMath.rnd(1, 320 );
		  b1 = CMath.rnd(1, 97 );
		  
		  screen.drawPixel(a, b1);
		  screen.drawLine(a, b1, a + 1, b1);
	  }
	  screen.drawLine(0, 97, 320, 97);
	  /*
	  5200 for i = 3 to 97:
	       j = i * j / 97:
	       a  = rnd(rnd(0)) * 159:
	       b1 = rnd(rnd(0)) * 96:
	       draw 1, a, b1:
	       draw 0,97 - j, 159 to 97 - j, 3:
	     next:
	     color 2,1:
	     draw 2 , 0 , 97 to 159 , 97:
	     return
	     */
  }
}