package de.g3ll3rt.slife;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class slife extends JFrame implements Runnable
{
	boolean arrayfeld[][];
	int breite;
	int hoehe;
	int windowSizeX = 250;
	int windowSizeY = 250;
	int gamespeed = 90; // in %
	boolean pause = false;
	boolean game_stopped = false;
	boolean threadValid = true;
	static int threadcount =0;
	int accelMethod = 2;
	
	// PREF-VARS :
	int stoneColVal = 0; // red,green
	Color stoneColor;
	Color backgrColor;
	int shapeVal = 0; // oval, rect
	shape shapeX = new ovalShape();
																				class intro extends JWindow
																					{
																						Font bigFont = new Font("SansSerif",Font.BOLD,50);
																						Font midFont = new Font("SansSerif",Font.BOLD + Font.ITALIC, 20);
																						Font smalFont = new Font("SansSerif",Font.BOLD, 10);
																						Color backgrCol = (Color.cyan);
																						Color FontColor = (Color.black);
																						int breite = 200;
																						int hoehe = 150;
																						int posX;
																						int posY;
																						
																						public intro()
																								{
																									center();
																									setBounds(posX,posY,breite,hoehe);
																									setVisible(true);
																								}	
		
																						public void paint(Graphics g)
																							{
																								g.setColor(FontColor);
																								g.fillRect(0,0,getWidth(),getHeight());
																								g.setColor(backgrCol);
																								g.fillRect(2,2,(getWidth()-4), (getHeight() -4));
																								g.setFont(bigFont);
																								g.setColor(FontColor);
																								g.drawString("S-Life",30,60);
																								g.setFont(midFont);
																								g.drawString("LITE V1.0",30,75);
																								g.setFont(smalFont);
																								g.drawString("(C) Christian Gellert 2003",30,120);
				
																							}
																						public void center()
																							{
																								Toolkit tk = getToolkit();
																								Dimension dim = tk.getScreenSize();
																								posX = ((int)(dim.getWidth() /2)) - (breite /2);
																								posY = ((int)(dim.getHeight() / 2)) - (hoehe / 2);
																							}
																					}	
	

																				class optionsDialog extends JDialog
																				{
																					JSlider mySlider = new JSlider(JSlider.HORIZONTAL,0,100,gamespeed);
																					JComboBox colorBox = new JComboBox();
																					JComboBox shapeBox = new JComboBox();
																					
																					public optionsDialog()
																					{
																					//	super(getLocalJFrame(),false);
																						setTitle("PREFS MENU");
																	
																						mySlider.setMajorTickSpacing(25);
																						mySlider.setPaintLabels(true);
																						mySlider.setPaintTicks(true);
																						mySlider.addChangeListener(new ChangeListener()
																						{
																							public void stateChanged(ChangeEvent e)
																							{
																								gamespeed = mySlider.getValue();
																							}
																						});
																						
																						colorBox.addItem("red");
																						colorBox.addItem("green");
																						colorBox.setSelectedIndex(stoneColVal);
																						colorBox.addItemListener(new ItemListener()
																						{
																							public void itemStateChanged(ItemEvent e)
																							{
																								stoneColVal = colorBox.getSelectedIndex();
																								if (stoneColVal == 0) stoneColor = (Color.red);
																								if (stoneColVal == 1) stoneColor = (Color.green);
																							}
																						});
																						
																						shapeBox.addItem("ovals");
																						shapeBox.addItem("rects");
																						shapeBox.setSelectedIndex(shapeVal);
																						shapeBox.addItemListener(new ItemListener()
																						{
																							public void itemStateChanged(ItemEvent e)
																							{
																								shapeVal = shapeBox.getSelectedIndex();
																								if (shapeVal == 0) shapeX = new ovalShape();
																								if (shapeVal == 1) shapeX = new rectShape();
																							}
																						});
																						init_menu();
																						pack();
																						setLocation(300,50);
																						setVisible(true);
																						
																					}
																					
																					void init_menu()
																					{
																						getContentPane().setLayout(new GridLayout(4,1));
																						
																						JPanel gameSpeedPanel = new JPanel();
																						JPanel colorPanel = new JPanel();
																						JPanel stoneShapePanel = new JPanel();
																						JPanel buttonPanel = new JPanel();
																						
																						gameSpeedPanel.setLayout(new GridLayout(2,1));
																						gameSpeedPanel.add(new JLabel("Spielgeschwindigkeit :"));
																						gameSpeedPanel.add(mySlider);
																						
																						colorPanel.setLayout(new GridLayout(2,1));
																						colorPanel.add(new JLabel("Farbe der Steine"));
																						colorPanel.add(colorBox);
																						
																						stoneShapePanel.setLayout(new GridLayout(2,1));
																						buttonPanel.setLayout(new FlowLayout());
																						stoneShapePanel.add(new JLabel("Form der Steine :"));
																						stoneShapePanel.add(shapeBox);
																					
																						buttonPanel.setLayout(new FlowLayout());
																						JButton pauseButton = new JButton("Pause");
																						pauseButton.addActionListener(new ActionListener()
																						{
																							public void actionPerformed(ActionEvent e)
																							{
																								pause = !pause;
																							}
																						});
																						
																						JButton fSizeButton = new JButton("Feldgr��e");
																						fSizeButton.addActionListener(new ActionListener()
																						{
																							public void actionPerformed(ActionEvent e)
																							{
																								new fieldSizeDialog();
																							}
																						});
																						
																						JButton closeButton = new JButton("schliessen");
																						closeButton.addActionListener(new ActionListener()
																						{
																							public void actionPerformed(ActionEvent e)
																							{
																								dispose();
																							}
																						});
																						
																						buttonPanel.add(pauseButton);
																						buttonPanel.add(fSizeButton);
																						buttonPanel.add(closeButton);
																						
																						getContentPane().add(gameSpeedPanel);
																						getContentPane().add(colorPanel);
																						getContentPane().add(stoneShapePanel);
																						getContentPane().add(buttonPanel);
																					
																					}
																					
																				}
	

																				class fieldSizeDialog extends JDialog
																				{
																					public fieldSizeDialog()
																						{
																						super(getLocalJFrame(),false);
																						setTitle("Feldgr��e ...");
			
																						getContentPane().setLayout(new GridLayout(3,1));
																						final JTextField textX = new JTextField(4);
																						final JTextField textY = new JTextField(4);
																						
																						JPanel inputPanel = new JPanel();
																						inputPanel.add(new JLabel("Breite : "));
																						inputPanel.add(textX);
																						inputPanel.add(new JLabel("H�he : "));
																						inputPanel.add(textY);
																						inputPanel.setLayout(new GridLayout(2,2));
																						
																						JPanel buttonPanel = new JPanel();
																						buttonPanel.setLayout(new FlowLayout());
																						JButton confirmButton = new JButton("CONFIRM");
																						confirmButton.addActionListener(new ActionListener()
																						{
																							public void actionPerformed(ActionEvent e)
																							{
																								try
																															{
																															int xval = (int)(Integer.parseInt(textX.getText()));
																															int yval = (int)(Integer.parseInt(textY.getText()));
																															if((xval < 5) && (yval < 5)) chFieldsize(5,5);
																															else chFieldsize(xval,yval);
																															dispose();
																															}
																															catch (java.lang.NumberFormatException nfe)
																															{
																																JOptionPane.showMessageDialog(getLocalJFrame(),"Bitte nur Zahlen eingeben",
																																"FEHLER !!!", JOptionPane.ERROR_MESSAGE);
																															}
																							}
																						});
																						
																						JButton cancelButton = new JButton("CANCEL");
																						cancelButton.addActionListener(new ActionListener()
																						{
																							public void actionPerformed(ActionEvent e)
																							{
																								dispose();
																							}
																						});
																						buttonPanel.add(confirmButton);
																						buttonPanel.add(cancelButton);
																						
																						
																						
																						getContentPane().add(new JLabel("neue Bildschirmgr��e :"));
																						getContentPane().add(inputPanel);
																						getContentPane().add(buttonPanel);
																						
																						pack();
																						setVisible(true);
																					}
																				}





	public slife() 
		{
			super("SLife v1.0");
			if (stoneColVal == 0) stoneColor = (Color.red);
			if (stoneColVal == 1) stoneColor = (Color.green);
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					int myChoice =(JOptionPane.showConfirmDialog(getLocalJFrame(),
					"Wollen Sie das Programm beenden ?","CLOSE PROGRAM",JOptionPane.OK_CANCEL_OPTION));
						
					if (myChoice == 0) 
						{
							if (threadcount == 1)
							{
								System.exit(1);
							}
							
							else
							{
							threadcount = threadcount -1;
							threadValid = false;
							dispose();
							}
						} 
				}
			});
			hoehe = 30;
			breite = 30;
			setBounds(30,30,windowSizeX,windowSizeY);
			arrayfeld = new boolean[hoehe][breite];
			getContentPane().add(new paintboard(this));
			init_menu();
			
			//setVisible(true);
		}
	
	void init_menu()
	{
		JMenuBar menbar = new JMenuBar();
		
		JMenu filemen = new JMenu("FILE");
		JMenu gamemen = new JMenu("GAME");
		JMenu helpmen = new JMenu("HELP");
		
		JMenuItem quitItem = new JMenuItem("QUIT");
		quitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(1);
			}
		});
		
		JMenuItem newItem = new JMenuItem("NEW");
		newItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				init_it();
			}
		});
		
		JMenuItem loadItem = new JMenuItem("LOAD");
		loadItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FileDialog load = new FileDialog(new Frame(), "RECOVER SET",FileDialog.LOAD);
				load.setVisible(true);
				String path = (load.getDirectory() + load.getFile());
				try
				{
					load_it(path);
				}
			catch (IOException i)
				{
				System.out.println("FATAL LOAD ERROR");
				}
		
			}	
		});
		
		JMenuItem saveItem = new JMenuItem("SAVE");
		saveItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FileDialog save = new FileDialog(new Frame(), "DUMP SET",FileDialog.SAVE);
					save.setVisible(true);
					String path =(save.getDirectory() + save.getFile());
						try
							{
							dump_it(path);
							}
						catch(IOException i){}
			}
		});
		
		JMenuItem pauseItem = new JMenuItem("PAUSE");
		pauseItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pause = !pause;
			}
		});
		
		JMenuItem prefItem = new JMenuItem("OPTIONS");
		prefItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new optionsDialog();
			}
		});
		
		JMenuItem threadItem = new JMenuItem("new Thread");
		threadItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openThread();
			}
		});
		
		JMenuItem infoItem = new JMenuItem("INFO");
		infoItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(getLocalJFrame(),"Autor : Christian Gellert 2003",
				"S-Life LITE 1.0", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		setJMenuBar(menbar);
		
		menbar.add(filemen);
		filemen.add(newItem);
		filemen.add(loadItem);
		filemen.add(saveItem);
		filemen.add(quitItem);
		
		menbar.add(gamemen);
		gamemen.add(pauseItem);
		gamemen.add(prefItem);
		gamemen.add(threadItem);
		
		menbar.add(helpmen);
		helpmen.add(infoItem);
	}
	
	void init_it()
		{
			for (int i = 0; i < arrayfeld.length; i++)
			{
				for (int j = 0; j < arrayfeld[0].length; j++)
				{
					arrayfeld[i][j] = (java.lang.Math.random() < 0.5);
				}
			}
		
		}
	
	int count_nachbarn(int h, int b)
		{
		int nachbarcount = 0;
			
			if (arrayfeld[h][b] == true)
				nachbarcount = nachbarcount -1;
			
		
			for (int i = (h-1); i < (h+2); i++)
			{
				for (int j = (b-1); j < (b+2); j++)
					{
					int i_temp = i;
					int j_temp = j;
			
					if (i_temp < 0)
					i_temp = (arrayfeld.length -1);		
				
					if (i_temp > (arrayfeld.length-1))
					i_temp = 0;
		
					if (j_temp < 0)
					j_temp = (arrayfeld[i_temp].length-1);
					
					if (j_temp > (arrayfeld[i_temp].length-1))
					j_temp = 0;
			
					if (arrayfeld[i_temp][j_temp])
					nachbarcount = nachbarcount +1;
					}
			}

		return nachbarcount;
		}
	
	
	
	void recreate_it()
		{
			boolean temparray[][] = new boolean[hoehe][breite];
			for (int i = 0;i < temparray.length; i++)
			{
				for (int j = 0; j < temparray[i].length; j++)
				{
					 if (arrayfeld[i][j] == true)  //--------------SPIELREGELN--------------
					{
						int cn = count_nachbarn(i,j);
						if ((cn == 2) || (cn == 3))
						{
							temparray[i][j] = true;			// �berleben
						}	
						else
							{
							temparray[i][j] = false;   // tot wg. �berbev�lkerung od. Einsamkeit
							}
					}
						
						else
						{ 
						if (count_nachbarn(i,j) == 3)
							{
							temparray[i][j] = true;			// Geburt eines Neuen
							}
						else 
							temparray[i][j] = false;	// keine Geburt			
						}			    //-----------ENDE DER SPIELREGELN--------
					}
				}
			arrayfeld = temparray;	
		}
	
	void load_it(String path) throws IOException
		{
			try
			{
				FileInputStream filein = new FileInputStream(path);
				DataInputStream in = new DataInputStream(filein);
				chFieldsize(in.readInt(),in.readInt());
				for(int i = 0; i < arrayfeld.length; i++)
				{
					for(int j = 0; j < arrayfeld[i].length; j++)
					{
						arrayfeld[i][j] = in.readBoolean();
					}
				}
				in.close();
			}
			catch (IOException ioe)
				{
					System.out.println("FATAL LOAD ERROR");
				}
		
		}
	
	void dump_it(String path) throws IOException
		{
		
			try
			{
				FileOutputStream fileout = new FileOutputStream(path);
				DataOutputStream out = new DataOutputStream(fileout);
				out.writeInt(hoehe);
				out.writeInt(breite);
				for (int i = 0; i < arrayfeld.length; i++)
				{
					for (int j = 0; j < arrayfeld[i].length; j++)
					{
						out.writeBoolean(arrayfeld[i][j]);
					}
				}
				out.close();
			}
		
			catch (IOException x)
			{
				System.out.println("FATAL DUMPING ERROR...");
			}
		
		}
	
	void chFieldsize(int x, int y)
		{
			pause = true;
			arrayfeld = new boolean[y][x];
			repaint();
			hoehe = y;
			breite = x;
			init_it();
			pause = false;
		}
	
	JFrame getLocalJFrame()
		{
			return this;
		}
	
	void openThread()
	{
		(new Thread(new slife())).start();
	}

	void fit_it()
	{
		final Dimension DIM = getSize();
		final Insets INS = getInsets();
		final int HOR = INS.left + INS.right;
		final int VER = INS.top + INS.bottom;
		final int booleanlength = arrayfeld.length;
		final int booleanlength_i = arrayfeld[0].length;
		setSize((DIM.width - HOR)/booleanlength_i*booleanlength_i+HOR, ((DIM.height-VER)/booleanlength*booleanlength+VER));
	}
	
	
		public void run()
		{
		threadcount = threadcount + 1;
		if (threadcount == 1)
			{
			intro x = new intro();
			cgtoolbox.delay(3000);
			x.dispose();
			}
		init_it();
		setVisible(true);
		while(threadValid)
			{
			if (!pause)
				{	
					repaint();
					cgtoolbox.delay(10 * (100 -gamespeed));
					recreate_it();
				}
			}
		}
	
	public static void main(String[] args)
		{
		(new slife()).run();
		}
}







class paintboard extends JComponent
{
	slife lifeobj;
	Image newImage;
	
	public paintboard(slife inobj)
	{
		lifeobj = inobj;
	}
	
	public Dimension getPreferrfedSize()
	{
		return new Dimension((getWidth()),(getHeight()));
	}
	
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}
	
	public void paintComponent(Graphics g)
	{	
			g.setColor(lifeobj.stoneColor);
			
			int ibreite = (getWidth());
			int ihoehe = (getHeight());
			int steinbreite;
			int steinhoehe;
		
			int posY = 0;
			int posX = 0;
		
			steinbreite = (ibreite / lifeobj.arrayfeld.length);
			steinhoehe = (ihoehe / lifeobj.arrayfeld[0].length); 
		
		
			{
				for (int i = 0; i < lifeobj.arrayfeld.length; i ++)
				{
				
					for (int j = 0; j < lifeobj.arrayfeld[i].length; j++)
					{
						if (lifeobj.arrayfeld[i][j]) 
							{ 
							lifeobj.shapeX.drawShape(g, posX, posY, steinbreite,steinhoehe);
							//g.fillOval(posX,posY,steinbreite,steinhoehe);
							}
					
					/*	else
							{
							g.setColor(lifeobj.backgrColor);
							g.fillOval(posX,posY,steinbreite,steinhoehe);
							g.setColor(lifeobj.stoneColor);
							}
					*/
					
						posX = posX + steinbreite;
					}
					posX = 0;
					posY = posY + steinhoehe;
				}
			}	
			
			
	}
	public void update(Graphics g)
	{
		
	if(lifeobj.accelMethod == 0) // normal
		{
			g.clearRect(0,0,getWidth(), getHeight());
			paint(g);
		}
	
	if(lifeobj.accelMethod == 1) // non - delete
		{
		paint(g);
		}
		
	if (lifeobj.accelMethod == 2) // double - buffering
			{
			System.out.println("Ich verwende jetzt Update-Methode 2");
			if (newImage == null)
				{
				newImage = createImage(getWidth(),getHeight());
		 		}
		 	Graphics h = newImage.getGraphics();
		 	paintComponent(h);
		 	g.drawImage(newImage, 0,0,getWidth(),getHeight(),this);
		 	
		}
	}
}

abstract class shape
{
		abstract public void drawShape(Graphics g, int posX, int posY, int steinbreite,int steinhoehe);
}

class ovalShape extends shape
{
		public void drawShape(Graphics g, int posX, int posY, int steinbreite,int steinhoehe)
		{
			g.fillOval(posX,posY,steinbreite,steinhoehe);
		}
}	

class rectShape extends shape
{
		public void drawShape(Graphics g, int posX, int posY, int steinbreite, int steinhoehe)
		{
			g.fillRect(posX,posY,steinbreite,steinhoehe);
		}
}

