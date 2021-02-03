package de.g3ll3rt.jannuity.model;

import java.util.ArrayList;
import java.util.List;

public class Annuity {
	private int    year;                 // Jahr
	private double debtStart;            // Restbetrag der Darlehenssumme am 01. Januar
	private double debtEnd;              // Restbetrag der Darlehenssumme am 31. Dezember
	private double repayment;            // Betrag Abtrag
	private double interest;             // Betrag Zinsen
	private double unscheduledRepayment; // Sondertilgung
	private double ratePercent;          // Zinssatz
	private double payRate;              // Ratenbetrag
	private List<Month> monthList;       // Monatsabzahlung
	
	public Annuity(double restDebt, double ratePercent, double payRate) {
		this.debtStart = restDebt;
		this.payRate = payRate;
		this.ratePercent = ratePercent;
		calculateYear();
	}
	
	private void calculateYear() {
		monthList = new ArrayList<Month>();
		int monthCnt = 0;
		double restDebt = this.debtStart;
		Month month;
		
		//Monate generieren
		while ( ( monthCnt < 12 ) && (restDebt > 0) ) {
		  monthCnt = monthCnt + 1;
		  month = new Month();
		  month.month = monthCnt;
		  month.interest = ( ( restDebt / 100 ) * ratePercent )  / 12;
		  month.repayment = payRate - month.interest;
		  monthList.add(month);
		}
		
		double repaymentSum = 0;
		double interestSum = 0;
        for (int i = 0; i < monthList.size(); i++) {
        	Month actMonth = monthList.get(i);
        	repaymentSum = repaymentSum + actMonth.repayment;
        	interestSum  = interestSum + actMonth.interest;
        }
        
        // JAhreszinsen + neuen Schuldenstand ermitteln
        this.repayment = repaymentSum;
        this.debtEnd  = this.debtStart - repaymentSum;
        this.interest = interestSum;
        
        // Sondertilgung abziehen
        if ( this.debtEnd > this.unscheduledRepayment ) {
          this.debtEnd = this.debtEnd - this.unscheduledRepayment;
        } else {
        	// Sondertilgung = Restschuld
          this.unscheduledRepayment = this.debtEnd;
          this.debtEnd = 0;
        }
	}
	
	   private double round(double value, int decimalPoints) {
		      double d = Math.pow(10, decimalPoints);
		      return Math.round(value * d) / d;
		   }
	
	public String toString() {
      StringBuilder strBuilder = new StringBuilder();
      strBuilder.append("Jahr: "       + this.year );
      strBuilder.append("Startschuld:" + round(this.debtStart,2) );
      strBuilder.append("Abtrag:"      + round(this.repayment,2) );
      strBuilder.append("Zinsen:"      + round(this.interest,2) );
      strBuilder.append("Restschuld:"  + round(this.debtEnd,2) );
      //strBuilder.append("\n");
      
      return strBuilder.toString();
		
	}
             
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getDebtStart() {
		return debtStart;
	}
	public void setDebtStart(double debtStart) {
		this.debtStart = debtStart;
	}
	public double getDebtEnd() {
		return debtEnd;
	}
	public void setDebtEnd(double debtEnd) {
		this.debtEnd = debtEnd;
	}
	public double getRepayment() {
		return repayment;
	}
	public void setRepayment(double repayment) {
		this.repayment = repayment;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getUnscheduledRepayment() {
		return unscheduledRepayment;
	}
	public void setUnscheduledRepayment(double unscheduledRepayment) {
		this.unscheduledRepayment = unscheduledRepayment;
	}
}
