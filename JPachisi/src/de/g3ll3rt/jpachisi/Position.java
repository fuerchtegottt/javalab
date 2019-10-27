package de.g3ll3rt.jpachisi;

import java.util.ArrayList;

public class Position {
	private boolean isShelter;
	private ArrayList<Token> tokenList;
	
	public Position(){
		tokenList = new ArrayList<>();
	}
	public void setShelter(Boolean isShelter){
		this.isShelter = isShelter;
	}
	
	public void putToken(Token inToken){
		if (!isShelter){
			// remove opponent tokens
			for(Token oldToken : tokenList){
				oldToken.setState(TokenState.OUT);
			}
			tokenList.clear();
			tokenList.add(inToken);
		}
		
		tokenList.add(inToken);
	}
	
	public void removeToken(Token token){
		
	}
	
	public boolean isFree(){
		return (tokenList.size() == 0);
	}
	
	public void init(){
		tokenList.clear();
	}
}
