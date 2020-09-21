package mrLineMerge;

import java.awt.*;


class splash extends Window
{
	String introstring;
	Font myFont = new Font("Serif",(Font.BOLD + Font.ITALIC),20);
	Font myFont2 = new Font("Serif",0,20);
	Image splashPic;
	int screenWidth;
	int screenHeight;
	public splash(String inString, int duration)
	{
		super(new Frame());
		setBackground(Color.green);
		introstring = new String(inString); 
		Toolkit tool = getToolkit();
		splashPic = tool.getImage("mrLineMerge\\splash.gif");
		MediaTracker mt = new MediaTracker(this);
		try{
		  mt.addImage(splashPic,1);
		  mt.waitForAll();
		} catch (Exception e){}
		
		
		Dimension dim2 = tool.getScreenSize();
		screenWidth = (int)dim2.getWidth();
		screenHeight = (int)dim2.getHeight();
	    setLocation(screenWidth ,screenHeight );
		
	
		setVisible(true);
		setFont(myFont2);
		FontMetrics am = getGraphics().getFontMetrics();
		setSize(am.stringWidth(inString)+20,200);
		setLocation(screenWidth / 2 - (getWidth() /2),(screenHeight /2)- (getHeight()/2));

		delay(duration);
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
		
		g.drawImage(splashPic,(ins.left+3),(ins.top + 3),null);
		g.drawString(introstring,fontmitteX,fontmitteY);
				
	}
	
	public static void delay(int duration) // in ms
	{
	  try
	    {
		  Thread.sleep(duration);
		}
	  catch(InterruptedException i){}
	}
	
	public static void main(String[] args){
	  new splash("     Mr. LineMerge V0.3", 2000);
	}
}