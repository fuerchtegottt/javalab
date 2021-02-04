package de.g3ll3rt.jannuity.test;

import java.awt.Frame;

import de.g3ll3rt.jannuity.exception.CalculationException;
import de.g3ll3rt.jannuity.gui.ParamDialog;
import de.g3ll3rt.jannuity.model.Credit;
import de.g3ll3rt.jannuity.model.RepaymentPlan;

public class ConsoleTest {
	public static void main(String[] args) {
		
		
		// Abzahlplan berechnen (mit Beispielkredit)
		try {
		  RepaymentPlan plan1 = new RepaymentPlan(Credit.getSampleCredit());
		  System.out.println(plan1);
		} catch(CalculationException e) {
			System.out.println(e);
		}
	}
}
