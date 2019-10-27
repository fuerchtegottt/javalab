package de.g3ll3rt.slife;
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