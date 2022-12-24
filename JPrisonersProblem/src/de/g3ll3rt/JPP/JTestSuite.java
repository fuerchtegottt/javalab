package de.g3ll3rt.JPP;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JTestSuite {
	/**
	 * assert that random values are between 1 and 100
	 */
	@Test
	void testRandomBoundaries() {
		Simulation sim = new Simulation(100, 50);
		sim.runSimulation();
		for (int i = 0; i < 50; i++) {
			int nextRandomNumber = sim.getNextBoxByRandomOrder(i);
			assertTrue( nextRandomNumber > 0 && nextRandomNumber < 101);
		}
		assertTrue(true);
	}
}