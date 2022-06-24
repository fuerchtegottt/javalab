package de.g3ll3rt.jpachisi.strategy;

public enum TokenSituation {
	MOVE,      // simple move.
	ATTACK,    // opponent token on target field
	HUNTING,   // opponent nearby (in front)
	INSHELTER, // token is in shelter
	TOSHELTER, // shelter on target field
	TOHOME,    // home oh target field
	HUNTED,    // opponent nearby (behind)
}
