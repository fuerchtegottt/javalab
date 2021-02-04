package de.g3ll3rt.jannuity.gui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import de.g3ll3rt.jannuity.exception.CalculationException;
import de.g3ll3rt.jannuity.model.Credit;

public class ParamDialog extends Dialog{
	private Credit credit;
	TextField totalDebt;                  //Darlehenssumme
	TextField payRate;                    //Abtrag
	TextField startYear;                  //Startjahr
	TextField ratePercent;                //Zinssatz
	TextField unscheduledRepaymentRate;   //Sondertilgung
	Button btnConfirm;                    //Button zum Bestätigen
	Button btnCancel;                     //Button Abbruch
	JAnnuityUI ui = null;
	public ParamDialog(Frame father, Credit credit, JAnnuityUI ui) {
		super(father, false);
		this.credit = credit;
		this.ui = ui;
		setLocation(650,50);
		setTitle("Kreditdaten:");
		initTextFields();
		initLayout();
		initListeners();
		setVisible(true);
	}
	
	private void initTextFields() {
		totalDebt = new TextField();
		totalDebt.setColumns(20);
		totalDebt.setText(credit.getTotalDebt() + "");
		
		payRate = new TextField();
		payRate.setColumns(20);
		payRate.setText(credit.getPayRate() + "");
		
		startYear = new TextField();
		startYear.setColumns(20);
		startYear.setText(credit.getStartYear() + "");
     
		ratePercent = new TextField();
		ratePercent.setColumns(20);
		ratePercent.setText(credit.getRatePercent() + "");
		
		unscheduledRepaymentRate = new TextField();
		unscheduledRepaymentRate.setColumns(20);
		unscheduledRepaymentRate.setText(credit.getUnscheduledRepaymentRate() + "");
		
		btnConfirm = new Button("berechnen");
		btnCancel = new Button("abbrechen");
	}
	
	private void initLayout() {
		setLayout(new GridLayout(6,1,10,10));
		Panel p1 = new Panel();              //TotalDebt
		Panel p2 = new Panel();              //username
		Panel p3 = new Panel();              
		Panel p4 = new Panel();              
		Panel p5 = new Panel();
		Panel btnPanel = new Panel();        //confirm button / cancel button
		
		p1.setLayout(new GridLayout(1,2,10,10));
		p2.setLayout(new GridLayout(1,2,10,10));
		p3.setLayout(new GridLayout(1,2,10,10));
		p4.setLayout(new GridLayout(1,2,10,10));
		p5.setLayout(new GridLayout(1,2,10,10));
		
		btnPanel.setLayout(new GridLayout(1,2,10,10));
		
		p1.add(new Label("Kreditsumme"));
		p1.add(totalDebt);
		
		p2.add(new Label("Abtrag"));
		p2.add(payRate);
		
		p3.add(new Label("Startjahr"));
		p3.add(startYear);
		
		p4.add(new Label("Zinssatz in %"));
		p4.add(ratePercent);
		
		p5.add(new Label("Sondertilgung (Betrag)"));
		p5.add(unscheduledRepaymentRate);
		
		btnPanel.add(btnConfirm);
		btnPanel.add(btnCancel);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(btnPanel);
		pack();
	}
	
	private void flushParameters() throws CalculationException {
		credit.setPayRate(Double.parseDouble(payRate.getText()));
		credit.setTotalDebt(Double.parseDouble(totalDebt.getText()));
		credit.setStartYear(Integer.parseInt(startYear.getText()));
		credit.setRatePercent(Double.parseDouble(ratePercent.getText()));
		credit.setUnscheduledRepaymentRate(Double.parseDouble(unscheduledRepaymentRate.getText()));
		if ( credit.isValid() == false ) {
			throw new CalculationException("invalid params");
		}
	}
	
	private void initListeners() {
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		btnConfirm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					flushParameters();
					ui.calculatePlan();
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "ungültige Parameter", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
	}
}
