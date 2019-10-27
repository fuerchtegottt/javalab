package de.g3ll3rt.jpachisi;

public class Token {
	private String name;
	private Player player;
	private TokenState state;
	
	public Token(String name, Player player){
		this.name = name;
		this.player = player;
		this.state  = TokenState.OUT;
	}
	
	public void setState(TokenState state){
		this.state = state;
	}
	
	public TokenState getState(){
		return this.state;
	}

}
