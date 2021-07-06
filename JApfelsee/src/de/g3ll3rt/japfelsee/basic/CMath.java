package de.g3ll3rt.japfelsee.basic;

import java.util.Random;

public class CMath {
	private static Random rand = new Random();
	public static int rnd(int from, int to) {
		return from + rand.nextInt(to);
	}

}
