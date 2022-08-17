package de.g3ll3rt.JPP;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author chg
 *
 * basic implementation of the 100-Prisoners-Problem
 *  
 */
public class Simulation {
	private static final int STRATEGY_FOLLOW = 0;
	private int[] boxArray;
	private int[] randomOrder;
	private int prisonerCnt = 100;
	private int tryCnt      = 50;
	private int lastTag     = -1;
	public static int STRATEGY_RANDOM = 0;
	public static int STRATEGY_FOLOW = 1;
	private int strategy = STRATEGY_RANDOM;
	private int lostCnt = 0;
	private int wonCnt = 0;
	
	public Simulation(int inPrisonerCnt, int inTryCnt) {
		if (inPrisonerCnt > 0) {
			prisonerCnt = inPrisonerCnt;
		}
		
		//ammout of tries must not be bigger than prisoners count
		if (inTryCnt > prisonerCnt) {
			inTryCnt = prisonerCnt;
		}
		
		if (inTryCnt > 0) {
			tryCnt = inTryCnt;
		}
		resetSimulation();
	}
	
	public void resetSimulation() {
		lastTag = -1;
		boxArray = createBoxArray(prisonerCnt);
		shuffleBoxes(boxArray);
	}
	
	public void setStrategy(int inStrategy) {
		strategy = inStrategy;
	}
	
	private boolean pickBox(int inPrisonerNo, int inBoxNo) {
		lastTag = boxArray[inBoxNo];
		return (inPrisonerNo == lastTag);
	}
	
	public boolean runSimulation() {
		int nextBox = 0;
		boolean found = false;
		for (int prisonerNo = 0; prisonerNo < prisonerCnt; prisonerNo++) {
		  randomOrder = createRandomPickOrder(tryCnt);
		  found = false;
		  lastTag = - 1;
		  for (int prisonerTry = 0; prisonerTry < tryCnt; prisonerTry++ ) {
			if (strategy == STRATEGY_FOLOW) {
	          nextBox = getNextBoxByFlow(prisonerNo);
			} else {
			  nextBox = getNextBoxByRandomOrder(prisonerTry);	
			}
			if (pickBox( prisonerNo, nextBox) == true) {
				found = true;
				prisonerTry = ( tryCnt + 1);  //stop inner loop and goto next prisoner
			}
		  }
		  if (found == false) {
			  lostCnt = lostCnt + 1;
			  return false;      //game over if one prisoner fails
		  }
		}
		wonCnt = wonCnt + 1;
		return true;             //all prisoners found their tag
	}
	
	public int getNextBoxByFlow(int prisonerNo) {
		if (lastTag < 0) {
			return prisonerNo;	
		} else {
			return lastTag;
		}
	}
	
	public int getNextBoxByRandomOrder(int tryNo) {
		return randomOrder[tryNo];
	}
	
	public String writeBoxes() {
      return writeArray(boxArray);
	}
	
	public String writeRandomOrder() {
		return writeArray(randomOrder);
	}
	
	private String writeArray(int[] inArray) {
		  StringBuilder str = new StringBuilder();
		  for (int i = 0; i < inArray.length; i++) {
			  str.append(i + " - " + inArray[i]);
			  str.append(" \r\n");
		  }
		  return str.toString();
	}
	
	private static void shuffleBoxes(int[] boxArray)
	{
	    int index, temp;
	    Random random = new Random();
	    for (int i = boxArray.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = boxArray[index];
	        boxArray[index] = boxArray[i];
	        boxArray[i] = temp;
	    }
	}
	
	private int[] createBoxArray(int arrayLength) {
		int[] array = new int[arrayLength];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		return array;
	}
	
	private int[] createRandomPickOrder(int arrayLength) {
		Random random = new Random();
	    ArrayList<Integer> arrayList = new ArrayList<Integer>();

	    while (arrayList.size() < tryCnt) { // how many numbers u need
	        int a = random.nextInt(prisonerCnt); // this will give numbers between 1 and 50.

	        if (!arrayList.contains(a)) {
	            arrayList.add(a);
	        }
	    }
		
		
		//TODO: implement random pick order
		int[] array = new int[arrayLength];
		for (int i = 0; i < array.length; i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
	
	public String writeStats() {
		int wonPercentage;
		  StringBuilder str = new StringBuilder();
		      str.append(" prisoners: " + prisonerCnt);
			  str.append(" //");
		      str.append(" attempts: " + tryCnt);
			  str.append(" //");
			  str.append(" won: " + wonCnt);
			  str.append(" //");
			  str.append(" lost: " + lostCnt);
			  str.append(" //");
			  if ((lostCnt + wonCnt) > 0) {
				  wonPercentage = ( wonCnt * 100 / (lostCnt + wonCnt));
			  } else {
				  wonPercentage = 0;
			  }
			  str.append(" succ perc: " +  wonPercentage );			  
		  return str.toString();
	}
	
	public void doMassTest(int inStrategy) {
		for (int j = 0; j < 50; j++) {
			
		resetSimulation();
		setStrategy(inStrategy);
		for (int i = 0; i < 10000; i++) {
	      resetSimulation();
	      runSimulation();
		}
		System.out.println(writeStats());
	  }
	}
	
	public static void main(String[] args) {
		Simulation sim = new Simulation(100, 50);

        //sim.doMassTest(Simulation.STRATEGY_FOLOW);
        
        Simulation sim2 = new Simulation(10, 5);
        sim2.setStrategy(STRATEGY_FOLLOW);
        System.out.println(sim2.writeBoxes());
        sim2.runSimulation();
        System.out.println(sim2.writeStats());
	}	
}