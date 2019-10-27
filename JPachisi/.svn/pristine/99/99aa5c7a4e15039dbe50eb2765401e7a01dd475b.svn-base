package de.g3ll3rt.jpachisi;

import java.util.ArrayList;

public class PlayingField {
	private FieldMetrics metrics;
	private Position[] field;

	public PlayingField(FieldMetrics metrics) {
		this.metrics = metrics;
		field = new Position[metrics.getFieldLength()];
		for (int i = 0; i < field.length; i++) {
			field[i] = new Position();
		}
	}

	public void moveToken(Token token, int from, int to) {
		if (from >= 0){
			// remove Token from old field
			Position oldPos = field[from];
			oldPos.removeToken(token);
		}
		
		// place Token in new Field
		Position newPos = field[to];
		newPos.putToken(token);
	}

	public void insertTokenForPlayer(Player player){
		// choose token
		ArrayList<Token> tokens = player.getTokens();
		if (!tokens.isEmpty()){
			Token token = tokens.get(0);
		    // get entry point for fraction
		    int entryPos = metrics.getEntryPos(player.getFraction());
		    moveToken(token, -1, entryPos);
		}
	}
	
	public void init() {
		// free fraction houses

		// free playing field
		for (int i = 0; i < field.length; i++) {
			field[i].init();
		}
	}
}
