package de.g3ll3rt.jpachisi;

import java.util.ArrayList;

public class GameStats {
	private int roundCnt;
	private ArrayList<String> actionLog;
	private Fraction winner;
	
	public GameStats(){
		roundCnt = 0;
		actionLog = new ArrayList<>();
	}
	
	public void addAction(String action){
		actionLog.add(action);
	}
	
	public void addRound(){
		roundCnt++;
	}
	
	public void setWinningFraction(Fraction winner){
		this.winner = winner;
	}
	
	public int getRoundCnt(){
		return roundCnt;
	}
	
	public Fraction getWinner(){
		return this.winner;
	}
	
	public String toString(){
		StringBuffer output = new StringBuffer();
		for (String entry: actionLog){
			output.append(entry);
			output.append("\n");
		}
		return output.toString();
	}

}
