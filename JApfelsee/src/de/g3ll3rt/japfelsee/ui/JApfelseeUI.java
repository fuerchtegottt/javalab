package de.g3ll3rt.japfelsee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.g3ll3rt.japfelsee.FractalCore;
import de.g3ll3rt.japfelsee.JApfelseeException;
import de.g3ll3rt.japfelsee.ViewPosition;
import de.g3ll3rt.japfelsee.basic.BasicScreen;
import de.g3ll3rt.japfelsee.ui.canvas.JPaintComponent;
import de.g3ll3rt.japfelsee.ui.canvas.PaintableLine;

public class JApfelseeUI extends JFrame {
	private JPanel panel1;
	private JLabel label1;
	private JPaintComponent paintComponent;
	private BasicScreen basicScreen;
	private JMenuBar menubar = new JMenuBar();
	private JMenu menu1 = new JMenu("Landscape");
	private JMenuItem renderItem = new JMenuItem("render");
	private FractalCore fractalCore;
	private ViewPosition viewPos;
	
	public JApfelseeUI() {
		initControls();
		initListeners();
	    setTitle("JApfelsee V0.1");
	    setSize(345, 270);
	    setLocation(50,50);
	    renderLandscape();
	    setVisible(true);
	}
	
	private void initControls() {

        setJMenuBar(menubar);
        menubar.add(menu1);
        menu1.add(renderItem);
	    paintComponent = new JPaintComponent();
	    basicScreen = new BasicScreen();
		
	    add(paintComponent);
	    add(basicScreen);
	}
	
	private void initListeners() {
	    addWindowListener(new WindowAdapter() {
	  	  public void windowClosing(WindowEvent e) {
	  		 System.exit(0);
	  	  }
	  	});
	    renderItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          renderLandscape();
	        }
	      }); 
	}
	
	private void renderLandscape() {
		  try {
		    viewPos = ViewPosition.getNewViewPosition(0, 0, 1, 90);
		    fractalCore = new FractalCore(basicScreen, viewPos, FractalCore.LIGHTMODE_NIGHT );
	        fractalCore.generateLandscape();
		  } catch(JApfelseeException e) {
			  //Fehler ausgeben
		  }
		  this.getContentPane().repaint();
	}
	
	public static void main(String[] args) {
		new JApfelseeUI();
	}
}
