package de.g3ll3rt.jpachisi;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
	private String           name;
	private Fraction         fraction;
	private ArrayList<Token> tokens;
	
	public Player(String name, Fraction fraction){
       this(name, fraction, 4);
	}
	
	private int rollDice() {
		return ThreadLocalRandom.current().nextInt(1, 6 + 1);
	}
	
	public Player(String name, Fraction fraction, int tokenCnt) {
		this.name = name;
		this.fraction = fraction;
		tokens = new ArrayList<>();
		for( int i = 0; i < tokenCnt; i++){
			tokens.add(new Token("Player " + i, this));
		}
	}
	
	public void doTurn(PlayingField field){
		if (movesPossible()){
			doTurn(field, rollDice());
		} else {
			// 3 chances to enter game
			int retry = 3;
			do{
				if (rollDice() == 6){
					retry = 0;
					
					doTurn(field, 6);
				}
				
			} while (retry > 0);
		}
		
	}
	
	private void doTurn(PlayingField field, int diceCnt){
		if (diceCnt == 6 && tokensOut()){
			field.insertTokenForPlayer(this);
		} else {
			//get possible tokens
			//rate token situations
			//choose token (ask ai)
			//move token
		}
		
	}
	
	private ArrayList<Token> getMovableTokens(int diceCnt){
		ArrayList<Token> outTokens = new ArrayList<>();
		
		for (Token token : tokens){
			// get current field
			// get target field
			
		}
		
		return outTokens;
	}
	
	private boolean tokensOut(){
		for (Token token : tokens){
			if (token.getState() == TokenState.OUT){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * move possible if at least 1 token on board
	 * move possible if gaps in players house
	 * 
	 * @return
	 */
	public boolean movesPossible(){
		
		// still players on board?
		for (Token token : tokens){
			if (token.getState() == TokenState.BOARD){
			  return true;
			}
		}
		
		// still gaps in players house?
		if (gapsAtHome()){
			return true;
		}
		
		return false;
	}
	
	public String getName(){
		return name;
	}
	
	private boolean gapsAtHome(){
		return false;
	}
	
	public ArrayList<Token> getTokens(){
		return tokens;
	}
	
	public boolean hasWon(){
		for (Token token : tokens){
			if (( token.getState() == TokenState.BOARD ) || (token.getState() == TokenState.OUT)){
				return false;
			}
		}
		return true;
	}
	
	public void initPlayer(){
		for (Token token : tokens){
			token.setState(TokenState.OUT);
		}
	}
	
	public Fraction getFraction(){
		return fraction;
	}
	
	/**
	 * Testmethode: sollte nicht verwendet werden
	 */
	public void declarePokeVictory(){
		for (Token token : tokens){
			token.setState(TokenState.HOME);
		}
	}
}
