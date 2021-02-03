package de.g3ll3rt.jannuity.model;

public class Credit {
	private int    startYear;                // Startjahr
	private double totalDebt;                // Aufnahmebetrag
	private double payRate;                  // Betrag Rate
	private double ratePercent;              // Zinssatz
	private double unscheduledRepaymentRate; // Betrag jährliche Sondertilgung
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public double getTotalDebt() {
		return totalDebt;
	}
	public void setTotalDebt(double totalDebt) {
		this.totalDebt = totalDebt;
	}
	public double getPayRate() {
		return payRate;
	}
	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}
	public double getRatePercent() {
		return ratePercent;
	}
	public void setRatePercent(double ratePercent) {
		this.ratePercent = ratePercent;
	}
	public double getUnscheduledRepaymentRate() {
		return unscheduledRepaymentRate;
	}
	public void setUnscheduledRepaymentRate(double unscheduledRepaymentRate) {
		this.unscheduledRepaymentRate = unscheduledRepaymentRate;
	}
	
	public double getMinimumPayRate() {
		return ((( totalDebt * ratePercent ) / 100 ) / 12);
	}
	
	public boolean isValid() {
		//1. keine Werte <= Null zulässig
		if ( (totalDebt <= 0) || (payRate <= 0) || (ratePercent <= 0) ){
			return false;
		}
		
		
		//2. Die Zinsen für die erste Rate dürfen nicht die Abzahlrate übersteigen
		if (payRate <= getMinimumPayRate()) {
			return false;
		}
		return true;
	}
}