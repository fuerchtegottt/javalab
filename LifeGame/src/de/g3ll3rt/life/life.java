package de.g3ll3rt.life;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class life extends Frame implements Runnable
{
	static int threadcount;
	int accelMethod = 1; // no delete
	private char fillchar1 = '#';
	private char fillchar2 = '_';
	private boolean arrayfeld[][];
	private String stoneNames[][];
	private int breite;
	private int hoehe;
	private int windowSizeX = 300;
	private int windowSizeY = 400;
	boolean pause = false;
	boolean game_stopped = false;
	private boolean threadValid = true;
	
	
	
	// PREF-VARS :
	Color stoneColor =(Color.green);
	Color backgrColor =(Color.gray);
	shape shapeX = new ovalShape();
	int stoneShape = 1; //FILLRECT,FILLOVAL
	int gameSpeed = 90;
	Image newImage;
	
	
	FileWriter f;
	BufferedWriter b;
	PrintWriter p;
	PopupMenu myPopUp = new PopupMenu();
	
								class closedialog extends Dialog
									{
										public closedialog()
										{
										super(getLocalFrame(),true); 
										addWindowListener(new WindowAdapter() 
    										{ 
      											public void windowClosing(WindowEvent e) 
      												{ 
       												dispose();
     												} 
    										});

										setLayout (new GridLayout(2,1,20,20));
										Panel p1 = new Panel();
										Panel p2 = new Panel();
										p1.setLayout(new GridLayout(1,2));
										setTitle("beenden ?");
										Button yesbutton = new Button("JA");
										yesbutton.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
													{
													if (threadcount < 1) System.exit(0);
													else 
														{
														dispose();
														threadcount = threadcount -1;
														closeThread();
														}
													}	
											});
		
										Button nobutton = new Button("NEIN");
										nobutton.addActionListener(new ActionListener()
											{		
												public void actionPerformed(ActionEvent e)
													{
													dispose();
													}
											});
										p1.add(new Label("Wollen Sie das Spiel beenden ?"));
										p2.add(yesbutton);
										p2.add(nobutton);
										add(p1);
										add(p2);
										pack();
										setResizable(false);
										setVisible(true);
										}
								}
	
								
	
								class preferencedialog extends Dialog implements ItemListener
								{
									CheckboxGroup colorgrp = new CheckboxGroup();
									Checkbox color_red = new Checkbox("rot",colorgrp,false);
									Checkbox color_green = new Checkbox("gr�n", colorgrp, false);
									Checkbox color_blue = new Checkbox("blau",colorgrp, false);
									
									CheckboxGroup backgrgrp = new CheckboxGroup();
									Checkbox backgr_gray = new Checkbox("grau" ,backgrgrp,false);
									Checkbox backgr_black = new Checkbox("schwarwz", backgrgrp,false);
									Checkbox backgr_white = new Checkbox("weiss", backgrgrp,false);
									
									Choice shapeChoice = new Choice();
									
									Scrollbar speedBar = new Scrollbar(Scrollbar.HORIZONTAL,gameSpeed,1,1,100);
									Label gameSpeedLabel = new Label("Game Speed :" + gameSpeed);
									
									public preferencedialog(Frame papaframe)
									{
										super(papaframe, false);
										addWindowListener(new WindowAdapter()
										{
											public void windowClosing(WindowEvent e)
											{
												dispose();
											}
										});
										
										
										
										color_red.addItemListener(this);
										color_green.addItemListener(this);
										color_blue.addItemListener(this);
										backgr_gray.addItemListener(this);
										backgr_black.addItemListener(this);
										backgr_white.addItemListener(this);
										shapeChoice.addItemListener(this);
										speedBar.addAdjustmentListener(new AdjustmentListener()
										{
											public void adjustmentValueChanged(AdjustmentEvent e)
											{
											gameSpeed = (speedBar.getValue());
											gameSpeedLabel.setText("Game Speed :" + gameSpeed);
											}
										});
										
											
										
										if(stoneColor == (Color.red)) color_red.setState(true);
										else if(stoneColor == (Color.green)) color_green.setState(true);
										else color_blue.setState(true);
										
										if(backgrColor == (Color.gray)) backgr_gray.setState(true);
										else if(backgrColor == (Color.black)) backgr_black.setState(true);
										else backgr_white.setState(true);
										
										setLayout(new GridLayout(5,1,20,20));
										Panel p1 = new Panel();
										Panel p2 = new Panel();
										Panel p3 = new Panel();
										Panel p4 = new Panel();
										Panel p5 = new Panel();
										
										Panel colorcheckboxgroup = new Panel();
										Panel backgrcheckboxgroup = new Panel();
										Panel speedbarpanel = new Panel();
										
										p1.setLayout(new GridLayout(1,2,20,20));
										p2.setLayout(new GridLayout(1,2,20,20));
										p3.setLayout(new GridLayout(1,2,20,20));
										p4.setLayout(new GridLayout(1,2,20,20));
										p5.setLayout(new BorderLayout());
										colorcheckboxgroup.setLayout(new GridLayout(3,1,2,2));
										backgrcheckboxgroup.setLayout(new GridLayout(3,1,2,2));
										speedbarpanel.setLayout(new GridLayout(2,1,2,2));
										
										colorcheckboxgroup.add(color_red);
										colorcheckboxgroup.add(color_green);
										colorcheckboxgroup.add(color_blue);
										
										backgrcheckboxgroup.add(backgr_gray);
										backgrcheckboxgroup.add(backgr_black);
										backgrcheckboxgroup.add(backgr_white);
										
										
										speedbarpanel.add(speedBar);
										speedbarpanel.add(gameSpeedLabel);
										
										shapeChoice.add("RECTS");
										shapeChoice.add("OVALS");
										shapeChoice.select(stoneShape);
										
										Button closeButton = new Button("Fenster schlie�en");
										closeButton.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent e)
											{
												dispose();
											}
										});
										
										p1.add(new Label("Farbe der Steine :"));
										p1.add(colorcheckboxgroup);
										p2.add(new Label("Hintergrundfarbe :"));
										p2.add(backgrcheckboxgroup);
										p3.add(new Label("Form der Steine :"));
										p3.add(shapeChoice);
										p4.add(new Label("Spielgeschwindigkeit :"));
										p4.add(speedbarpanel);
										p5.add("East", closeButton);
										add(p1);
										add(p2);
										add(p3);
										add(p4);
										add(p5);
										
										pack();
										
										setVisible(true);
									}
									
									
									public void itemStateChanged(ItemEvent e)
									{
											// Steinfarben
											if(color_red.getState()) stoneColor = (Color.red);
											if(color_green.getState()) stoneColor = (Color.green);
											if(color_blue.getState()) stoneColor = (Color.blue);
							
											// Hintergrundfarbe
											if(backgr_gray.getState())  backgrColor =(Color.gray);
											if(backgr_black.getState()) backgrColor =(Color.black);
											if(backgr_white.getState()) backgrColor =(Color.white);
											
											// ShapeChoice
											stoneShape = (shapeChoice.getSelectedIndex());
											if (stoneShape == 0) shapeX = new rectShape();
											if (stoneShape == 1) shapeX = new ovalShape(); 
									}
								
								}
								
	
								class fieldsizedialog extends Dialog
								{
									TextField tfx = new TextField("",2);
									TextField tfy = new TextField("",2);
									Panel p1 = new Panel();
									public fieldsizedialog()
										{	
										super(getLocalFrame(), true);
										setTitle("Spielfeldgr��e");
										setLayout(new GridLayout(3,1,20,20));
										addWindowListener(new WindowAdapter()
										{
											public void windowClosing(WindowEvent e)
											{
												dispose();
											}
										});
										
										
										Button okbutton = new Button("�BERNEHMEN");
										okbutton.addActionListener(new ActionListener()
										{
										public void actionPerformed(ActionEvent e)
											{
												try
												{
												int xval = (int)(Integer.parseInt(tfx.getText()));
												int yval = (int)(Integer.parseInt(tfy.getText()));
												if((xval < 5) && (yval < 5)) chFieldsize(5,5);
												else chFieldsize(xval,yval);
												dispose();
												}
												catch (java.lang.NumberFormatException nfe)
												{
													ErrorDialog ed = new ErrorDialog();
												}
											}
										});
										p1.setLayout(new GridLayout(2,2,20,20));
										p1.add(new Label("X - Achse :"));
										p1.add(tfx);
										p1.add(new Label("Y - Achse :"));
										p1.add(tfy);
		
										add(new Label("Bitte geben Sie die neue Spielfeldgr��e an :"));
										add(p1);
										add(okbutton);
										setResizable(false);
										pack();
										setVisible(true);
		
										addWindowListener (new WindowAdapter()
										{
										public void windowClosing(WindowEvent e)
											{
											dispose();
											}
										});
								}
							}

							class ErrorDialog extends Dialog
							{
								public ErrorDialog()
								{
									super(getLocalFrame(),true);
									//super("FATAL ERROR");
									addWindowListener(new WindowAdapter()
									{
										public void windowClosing(WindowEvent we)
										{
											dispose();
										}
									});
									
									setResizable(false);
									GridLayout grid = new GridLayout(2,1);
									FlowLayout flow = new FlowLayout();
									Panel p1 = new Panel();
									p1.setLayout(flow);
									setLayout(grid);
									Button closeButton = new Button("CLOSE");
									closeButton.addActionListener(new ActionListener ()
									{
										public void actionPerformed(ActionEvent aev)
										{
											dispose();
										}
									});
									
									p1.add(closeButton);
									add(new Label("UNG�LTIGE EINGABE !!!"));
									add(p1);
									pack();
									setVisible(true);
									
								
								}
							}
							
							class accelDialog extends Dialog
							{
								Panel centerPanel = new Panel();
								Choice accelChoice = new Choice();
								public accelDialog()
								{
									super(getLocalFrame());
									addWindowListener(new WindowAdapter()
									{
										public void windowClosing(WindowEvent we)
										{
											dispose();
										}
									});
									accelChoice.addItemListener(new ItemListener()
									{
										public void itemStateChanged(ItemEvent ie)
										{
											accelMethod = (accelChoice.getSelectedIndex());
										}
									});
									
									setLayout(new GridLayout(3,1));
									setSize(100,100);
									setTitle("ACCELERATION");
									centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
									Button OKBtn = new Button("OK");
									OKBtn.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent ae)
										{
											dispose();
										}
									});
									centerPanel.add(OKBtn);
									
									accelChoice.add("normal");
									accelChoice.add("NO DELETE");
									accelChoice.add("DOUBBLE BUFFERING");
									accelChoice.select(accelMethod);
									add(new Label("Bitte w�hlen sie eine Darstellungsart"));
									add(accelChoice);
									add(centerPanel);
									pack();
									setVisible(true);
								}
							}
							

	

public life(int b, int h)
{	
	super("LIFE Spiel V1.1");
	
	if (threadcount == 0)
		{
			try
				{
					intro myIntro = new intro("Life Spiel wird gestartet", this);
				}
			catch (Exception x) {}
		}
		
	shapeX = new ovalShape();
	breite = b;
	hoehe = h;
	arrayfeld = new boolean[hoehe][breite];
	stoneNames = new String[hoehe][breite];
	
	setSize(windowSizeX, windowSizeY);
	fit_it();
	
	Image img = getToolkit().getImage("ico.gif");
	setIconImage(img);
	
	initMenu();
		
	initPopUp();
	
	initListeners();

	fit_it();
	setVisible(true);
}


public void run()
{
	play_it_window();
}


public life(int h)
{
	this(10,h);
}


public life()
{
	this(40,40);	
}

	protected void processMouseEvent(MouseEvent e)
	{
		if(e.isPopupTrigger())
		{
			myPopUp.show(e.getComponent(),e.getX(),e.getY());
		}
		super.processMouseEvent(e);
	}
	
	void initPopUp()
	{
	enableEvents(AWTEvent.MOUSE_EVENT_MASK); // Maus - Abfrage auf Systemebene
	
	MenuItem size1Item = new MenuItem("50 x 50");
	size1Item.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				chFieldsize(50,50);
			}
		});
	
	MenuItem size2Item = new MenuItem("100 x 100");
	size2Item.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				chFieldsize(100,100);
			}
		});
	
	MenuItem size3Item = new MenuItem("250 x 300");
	size3Item.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Toolkit tk = getToolkit();
	  			Dimension dim = tk.getScreenSize();
	  			setBounds(0,0,dim.width, dim.height);
				chFieldsize(250,300);
			}
		});
	
	
	MenuItem userdefinedsizeItem = new MenuItem("undefined");
	userdefinedsizeItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
				fieldsizedialog fsd = new fieldsizedialog();
				}
		});
	
	add(myPopUp);
	myPopUp.add(size1Item);
	myPopUp.add(size2Item);
	myPopUp.add(size3Item);
	myPopUp.add(userdefinedsizeItem);
	
		
	}
	
	void initListeners()
	{
		addWindowListener(new WindowAdapter() 
    		{ 
      			public void windowClosing(WindowEvent e) 
      				{ 
       				closedialog y = new closedialog();
      				} 
    		});
    
    	addComponentListener(new ComponentAdapter() 
			{  
 	 			public void componentResized(ComponentEvent e) 
 	 				{  
  					fit_it();
  					repaint();
  					}  
			}); 
	
	}
	
	
	
	void initMenu()
	{
		MenuBar menbar = new MenuBar();
	
		Menu filemen = new Menu("DATEI");
		Menu gamemen = new Menu("GAMEPLAY");
		Menu viewmen = new Menu("VIEW");
		Menu helpmen = new Menu("HILFE");
	
		MenuItem loadItem = new MenuItem("LOAD");
		loadItem.setShortcut(new MenuShortcut(KeyEvent.VK_L));
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
			catch (IOException i){}
		
			}
		});
			
		MenuItem saveItem = new MenuItem("SAVE");
		saveItem.setShortcut(new MenuShortcut(KeyEvent.VK_S));
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
	
	MenuItem exportItem = new MenuItem("Export to ...DOC");
	exportItem.setShortcut(new MenuShortcut(KeyEvent.VK_E));
	exportItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
		FileDialog export = new FileDialog(new Frame(), "EXPORT SET",FileDialog.SAVE);
		export.setVisible(true);
		String path = (export.getDirectory() + export.getFile());
		try
			{
			export_it(path);
			}			
		catch(IOException i){}
		
		}
	});
	
	MenuItem threadItem = new MenuItem("new Thread");
	threadItem.setShortcut(new MenuShortcut(KeyEvent.VK_T));
	threadItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			life.threadcount = life.threadcount ++;
			openThread();
		}
	});
	
	
	
	MenuItem quitItem = new MenuItem("Beenden");
	quitItem.setShortcut(new MenuShortcut(KeyEvent.VK_Q));
	quitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				closedialog y = new closedialog();
			}
		});
	
	MenuItem infoItem = new MenuItem("INFO");
	infoItem.setShortcut(new MenuShortcut(KeyEvent.VK_I));
	infoItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("AUTOR : Christian Gellert");
				System.out.println("(C) 2003");
			}
		});
	
	MenuItem killItem = new MenuItem("kill all");
	killItem.setShortcut(new MenuShortcut(KeyEvent.VK_K));
	killItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
		boolean temparray[][] = new boolean[hoehe][breite];
		arrayfeld = temparray;
		repaint();
		}
	});
			
	MenuItem respawnItem = new MenuItem("RESPAWN");
	respawnItem.setShortcut(new MenuShortcut(KeyEvent.VK_R));
	respawnItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			init_it();
			repaint();
		}
	});
	
	MenuItem fastdownItem = new MenuItem("FAST SHUTDOWN");
	fastdownItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(1);
		}
	});
	
	MenuItem accelItem = new MenuItem("ACCELERATION");
	accelItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			accelDialog adx = new accelDialog();
		}
	});
	
	CheckboxMenuItem pauseItem = new CheckboxMenuItem("PAUSE");
	pauseItem.setShortcut(new MenuShortcut(KeyEvent.VK_P));
	pauseItem.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			pause = !pause;
		}
	});
	
	MenuItem newItem = new MenuItem("Neu");
	newItem.setShortcut(new MenuShortcut(KeyEvent.VK_N));
	newItem.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{ 
			init_it(); 
			repaint(); 
		}
		
	});
	MenuItem minimizeItem = new MenuItem("minimize");
	minimizeItem.setShortcut(new MenuShortcut(KeyEvent.VK_M));
	minimizeItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			setState(Frame.ICONIFIED);
		}
	});
	
	MenuItem maximizeItem = new MenuItem("maximize");
	maximizeItem.setShortcut(new MenuShortcut(KeyEvent.VK_X));
	maximizeItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Toolkit tk = getToolkit();
	    	Dimension dim = tk.getScreenSize();
	    	setBounds(0,0,dim.width, dim.height);
		}
	});
	
	MenuItem defaultsizeItem = new MenuItem("default size");
	defaultsizeItem.setShortcut(new MenuShortcut(KeyEvent.VK_D));
	defaultsizeItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			setSize(windowSizeX,windowSizeY);
		}
	});
	
	MenuItem centerItem = new MenuItem("center");
	centerItem.setShortcut(new MenuShortcut(KeyEvent.VK_C));
	centerItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Toolkit tk = getToolkit();
			Dimension dim1 = tk.getScreenSize();
			Dimension dim2 = getSize();
			int midX = ((dim1.width /2) - (dim2.width / 2)) ;
			int midY = ((dim1.height /2) - (dim2.height / 2));
			setBounds (midX,midY,dim2.width,dim2.height);
		}
	});
	
	MenuItem prefItem = new MenuItem("PREFS");
	prefItem.setShortcut(new MenuShortcut(KeyEvent.VK_F));
	prefItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			preferencedialog prefdlg = new preferencedialog(new Frame());
		}
	});
	
	MenuItem namelistItem = new MenuItem("WHO IS WHO ?");
	namelistItem.setShortcut(new MenuShortcut(KeyEvent.VK_W));
	namelistItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			arrayview aview = new arrayview("they are hard as stone ... :",stoneNames);
		}
	});
	
	menbar.add(filemen);	
	
	filemen.add(newItem);
	filemen.add(loadItem);
	filemen.add(saveItem);
	filemen.add(exportItem);
	filemen.add(threadItem);
	filemen.addSeparator();
	filemen.add(quitItem);
	
	menbar.add(gamemen);
	gamemen.add(killItem);
	gamemen.add(respawnItem);
	gamemen.add(fastdownItem);
	gamemen.add(accelItem);
	gamemen.add(pauseItem);
	
	menbar.add(viewmen);
	viewmen.add(maximizeItem);
	viewmen.add(minimizeItem);
	viewmen.add(defaultsizeItem);
	viewmen.add(centerItem);
	viewmen.addSeparator();
	viewmen.add(prefItem);
	viewmen.add(namelistItem);
	
	menbar.setHelpMenu(helpmen);
	helpmen.add(infoItem);
	
	
	setMenuBar(menbar);
	}
	
	
	void play_it_file(int runden) throws IOException
	{
		init_it();
		
			for (int i = 0; i < runden ;i++)
					{
						write_it(); 
						recreate_it();
					}
	}
	
	void invert_it()
	{
		boolean inv_feld[][] = new boolean[hoehe][breite];
		for (int i = 0; i < arrayfeld.length; i++)
		{
			for (int j = 0; j < arrayfeld[i].length; j++)
			{
				inv_feld[i][j] = !(arrayfeld[i][j]);
			}
		}
		arrayfeld = inv_feld;
	}
	
	Frame getLocalFrame()
	{
		return this;
	}
	
	void chFieldsize(int x, int y)
		{
			pause = true;
			while(!game_stopped);
			arrayfeld = new boolean[y][x];
			repaint();
			hoehe = y;
			breite = x;
			init_it();
			pause = false;
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
	
	public void paint(Graphics g)
	{	
		
			setBackground(backgrColor);
			g.setColor(stoneColor);
			Insets ins = getInsets();
			g.setClip(0,0,getWidth(), getHeight());
		
			int ibreite = (getWidth()-(ins.left + ins.right));
			int ihoehe = (getHeight()-(ins.bottom + ins.top));
			int steinbreite;
			int steinhoehe;
		
			int posY = ins.top;
			int posX = ins.left;
		
			steinbreite = (ibreite / arrayfeld.length);
			steinhoehe = (ihoehe / arrayfeld[0].length); 
		
		
			{
				for (int i = 0; i < arrayfeld.length; i ++)
				{
				
					for (int j = 0; j < arrayfeld[i].length; j++)
					{
						if (arrayfeld[i][j]) shapeX.drawShape(g, posX, posY, steinbreite,steinhoehe);
						else
							{
							g.setColor(backgrColor);
							shapeX.drawShape(g, posX, posY, steinbreite,steinhoehe);
							g.setColor(stoneColor);
							}
					
						posX = posX + steinbreite;
					}
					posX = getInsets().left;
					posY = posY + steinhoehe;
				}
			}	
	
	}	
	
	public void update(Graphics g)
	{
		
	if(accelMethod == 0) // normal
		{
			g.clearRect(0,0,getWidth(), getHeight());
			paint(g);
		}
	
	if(accelMethod == 1) // non - delete
		{
		paint(g);
		}
		
	if (accelMethod == 2) // double - buffering
			{
			if (newImage == null)
				{
				newImage = createImage(getWidth(),getHeight());
		 		}
		 	Graphics h = newImage.getGraphics();
		 	paint(h);
		 	g.drawImage(newImage, 0,0,getWidth(),getHeight(),this);
		 	
		}
	}
	
	void play_it_console(int runden)
	{
		init_it();
		
		for (int i = 0; i < runden ;i++)
		{
			print_it();
			recreate_it();
			System.out.println();
		}
	}
	
	void play_it_window()
	{
		
		init_it();
		cgtoolbox.delay(100);
		while (threadValid)
		{
		
			while(!pause)
			{
				game_stopped = false;
				//if (pause == false) 
				//{
				recreate_it();
				cgtoolbox.delay((100 - gameSpeed)*10);
				repaint();
				//}	
			}
			game_stopped = true;
			
		}
	}
	
	void play_it_window(int runden)
	{
		init_it();
		
		for (int i =0; i < runden; i++)
		{
		recreate_it();
		cgtoolbox.delay(100);
		repaint();
		}
	}
	
	void init_it()
	{
		for (int i = 0; i < arrayfeld.length; i++)
		{
			for (int j = 0; j < arrayfeld[0].length; j++)
			{
				arrayfeld[i][j] = (java.lang.Math.random() < 0.5);
				if (arrayfeld[i][j]) stoneNames[i][j] = (cgtoolbox.getName());
				else stoneNames[i][j] = ("######");
			}
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
	

	void export_it(String path) throws IOException
	{
		f = new FileWriter(path, true);
		b = new BufferedWriter(f);
		p = new PrintWriter(b);
		for (int i = 0; i < arrayfeld.length; i++)
		{
			for (int j = 0; j < arrayfeld[i].length; j++)
			{
				if (arrayfeld[i][j])
				p.print(fillchar1);
				else
				p.print(fillchar2);
			}
			p.print("\n");
		}
	
	b.flush();
	f.close();
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
	
	void easterEgg()
	{
		System.out.println("KYLES MOM IS A BITCH");
	}
	
	void write_it() throws IOException
	{
		f = new FileWriter("Life_sim.txt", true);
		b = new BufferedWriter(f);
		p = new PrintWriter(b);
		p.print("\n");
		p.print("-----------------------------------");
		p.print("\n");
		for (int i = 0; i < arrayfeld.length; i++)
		{
			for (int j = 0; j < arrayfeld[i].length; j++)
			{
				if (arrayfeld[i][j])
				p.print(fillchar1);
				else
				p.print(fillchar2);
			}
			p.print("\n");
		}
	
	b.flush();
	f.close();
	}
	
	void print_it()
	{
		for (int i = 0; i < arrayfeld.length; i++)
		{
			for (int j = 0; j < arrayfeld[i].length; j++)
			{
				if (arrayfeld[i][j])
				System.out.print(fillchar1);
				else
				System.out.print(fillchar2);
			}
			System.out.println();
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
						stoneNames[i][j] = ("######");
						}
				}
				
				
				else
					{ 
					if (count_nachbarn(i,j) == 3)
						{
						temparray[i][j] = true;			// Geburt eines Neuen
						stoneNames[i][j] = (cgtoolbox.getName());
						}
					else 
						temparray[i][j] = false;	// keine Geburt			
					}			    //-----------ENDE DER SPIELREGELN--------
				}
			}
		arrayfeld = temparray;
		
	}
	
	void openThread()
		{
			threadcount = threadcount +1;
			new Thread(new life()).start();
			
		}
		
	public static void main (String[] args) throws IOException
		{	
		life x = new life();
   		x.play_it_window();
		}
		
	 void closeThread()
		{
		dispose();
		threadValid = false;
		}
								
}

class intro extends Window
{
	String introstring;
	Font myFont = new Font("Serif",(Font.BOLD + Font.ITALIC),20);
	Font myFont2 = new Font("Serif",0,20);
	Image chepic;
	int screenWidth;
	int screenHeight;
	public intro(String instring, Frame PapaFrame) throws Exception
	{
		super(PapaFrame);
		setBackground(Color.green);
		introstring = new String(instring); 
		Toolkit tool = getToolkit();
		chepic = tool.getImage("che.gif");
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(chepic,1);
		mt.waitForAll();
		
		
		Dimension dim2 = tool.getScreenSize();
		screenWidth = (int)dim2.getWidth();
		screenHeight = (int)dim2.getHeight();
	    setLocation(screenWidth ,screenHeight );
		
	
		setVisible(true);
		setFont(myFont2);
		FontMetrics am = getGraphics().getFontMetrics();
		setSize(am.stringWidth(instring)+20,200);
		setLocation(screenWidth / 2 - (getWidth() /2),(screenHeight /2)- (getHeight()/2));

		cgtoolbox.delay(2000);
		dispose();
	}
	
	
	public void paint(Graphics g)
	{
		
		Dimension dim = getSize();
		
		Insets ins = getInsets();
		
		int mitteX = ((dim.width -(ins.left + ins.right)) /2);
		int mitteY = ((dim.height - (ins.top + ins.bottom)) /2);
		
		FontMetrics am = g.getFontMetrics();
		
		int fontmitteX = mitteX - (am.stringWidth(introstring) /2);
		int fontmitteY = mitteY + ((am.getHeight() /2) - (am.getDescent() + am.getLeading()));
		
		for (int i = 0; i < 10; i++)
		{
			g.drawRect((ins.left+(i*3)),(ins.top+(i*3)),(dim.width-(i*3)),(dim.height-(i*3)));
		}
		
		g.drawImage(chepic,(ins.left+3),(ins.top + 3),null);
		g.drawString(introstring,fontmitteX,fontmitteY);
				
	}
}

		

	




class cgtoolbox
{
	public static void delay(int duration) // in ms
	{
	try
		{
		Thread.sleep(duration);
		}
	catch(InterruptedException i){}
	}
	
	public static String getName()
	{
		String[] namearray = 
		{"TEX","TAN","DAR","BEL","BUR","ELA","ANO","RIC","HOL","BAF","GEL","RAF",
		 "BUL","BAK","REN","TAB","GAL","KRI","DUM","BIF","ALG","DRO","MIG","MEL",
		 "BLA","HUU","KEN","JUN","JIN","HAN","IKO","EDO","KOU","LON","BAN","ION"};
		int laenge = (namearray.length -1);
	
		int choice1 = ((int)(java.lang.Math.random() * laenge));
		int choice2 = ((int)(java.lang.Math.random() * laenge));
		String outstring = new String(namearray[choice1] + namearray[choice2]);
	
		return outstring;
	}
}

class arrayview extends Frame
{
	TextArea arrayArea = new TextArea(10,30);
	boolean inarray[][];
	
	public arrayview(String instring, String inarray[][])
	{
		super("ARRAY - VIEW");
		setLayout(new BorderLayout());
		setResizable(false);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		
		
		arrayArea.setEditable(false);
		add("North",new Label(instring));
		add("South",arrayArea);
		
		
		
		for(int i = 0; i < inarray.length; i++)
		{
			for(int j = 0; j < inarray[i].length; j++)
			{
				arrayArea.append(inarray[i][j] + "\t");
			}
			arrayArea.append("\n");
		}
		
		pack();
		setVisible(true);
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