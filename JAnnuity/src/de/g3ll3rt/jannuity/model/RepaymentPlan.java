package de.g3ll3rt.jannuity.model;

import java.util.ArrayList;
import java.util.List;

import de.g3ll3rt.jannuity.exception.CalculationException;

public class RepaymentPlan {
  public RepaymentPlan(Credit credit) throws CalculationException{
	  this.credit = credit;
	  // check credit (z.B. Raten müssen unter monatl. Zinsen liegen)
	  if ( credit == null || credit.isValid() == false ) {
		  throw new CalculationException("invalid credit base");
	  }
	  this.annuityList = generatePlan(credit);
  }
  private Credit credit;
  private List<Annuity> annuityList;
  public List<Annuity> getAnnuityList(){
	  return this.annuityList;
  }
  public List<Annuity> generatePlan(Credit credit) {
	  annuityList = new ArrayList<Annuity>();
	  
	  // initiate first year
	  double restDebt = credit.getTotalDebt();
	  int nextYear    = credit.getStartYear();
	  
	  while(restDebt > 0) {
		  Annuity annuity = this.calculateAnnuity(restDebt, nextYear, credit.getRatePercent(), credit.getPayRate(), credit.getUnscheduledRepaymentRate());
		  this.annuityList.add(annuity);
	      restDebt = annuity.getDebtEnd();
	      nextYear = annuity.getYear() + 1;
	  }
	  return this.annuityList;	  
  }

  public Annuity calculateAnnuity(double restDebt, int year, double ratePercent, double payRate, double unscheduledRepayment ) {
	  Annuity annuity = new Annuity(restDebt, ratePercent, payRate, unscheduledRepayment );
	  annuity.setYear(year);
	  
	  return annuity;	  
  }
  
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
	for(int i = 0; i < this.annuityList.size(); i++) {
		strBuilder.append(this.annuityList.get(i));
		strBuilder.append("\n");
	}
    
	return strBuilder.toString();
  }
}