package de.g3ll3rt.jannuity.test;

import de.g3ll3rt.jannuity.exception.CalculationException;
import de.g3ll3rt.jannuity.model.Credit;
import de.g3ll3rt.jannuity.model.RepaymentPlan;

public class ConsoleTest {
	public static void main(String[] args) {
		Credit c1 = new Credit();
		c1.setPayRate(650);
		c1.setRatePercent(1.05);
		c1.setStartYear(2007);
		c1.setTotalDebt(120000);
		c1.setUnscheduledRepaymentRate(3200);
		
		c1.setPayRate(750);
		c1.setRatePercent(1.05);
		c1.setStartYear(2007);
		c1.setTotalDebt(120000);
		c1.setUnscheduledRepaymentRate(3200);
		try {
		  RepaymentPlan plan1 = new RepaymentPlan(c1);
		  System.out.println(plan1);
		} catch(CalculationException e) {
			System.out.println(e);
		}
		
	}

}
