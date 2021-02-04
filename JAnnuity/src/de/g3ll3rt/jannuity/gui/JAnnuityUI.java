package de.g3ll3rt.jannuity.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import de.g3ll3rt.jannuity.exception.CalculationException;
import de.g3ll3rt.jannuity.model.Annuity;
import de.g3ll3rt.jannuity.model.Credit;
import de.g3ll3rt.jannuity.model.RepaymentPlan;

public class JAnnuityUI extends JFrame{
	private JMenuBar menubar = new JMenuBar();
	private JMenu creditMenu = new JMenu("JAnnuity");
	private JMenu infoMenu = new JMenu("Info");
	private JMenuItem itemCredit = new JMenuItem("Kredit..");
	private JMenuItem itemAbout  = new JMenuItem("über..");
	private JMenuItem itemQuit   = new JMenuItem("Beenden");
	private JPanel panel1 = new JPanel(new BorderLayout());
	private JTable table1 = new JTable();	
	private JLabel statusLabel = new JLabel("   Statuszeile");
	private Credit credit = Credit.getSampleCredit();
	DecimalFormat f = new DecimalFormat("#0.00");
	
	public JAnnuityUI() {
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    initMenuBar();
	    initPanels();
	    setTitle("JAnnuity");
	    setSize(600,400);
	    setLocation(50,50);
	    setListeners();
	    refreshTable(null);
	    setVisible(true);
	    
	    proceedCredit();
	}
	
	
	private void setListeners() {
      itemQuit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		  System.exit(0);
		}
      });
      
      itemCredit.addActionListener(new ActionListener() {
  	    public void actionPerformed(ActionEvent e) {
  		  proceedCredit();
  		}
      });
      
      
      itemAbout.addActionListener(new ActionListener() {
  	    public void actionPerformed(ActionEvent e) {
  	      JOptionPane.showMessageDialog(null, "(c) Christian Gellert 2020", "JAnnuity V1.0", JOptionPane.INFORMATION_MESSAGE);
  		}
      });
      
      
	}
    
    private void proceedCredit() {
    	new ParamDialog(new Frame(), credit, this);
    }
    
	private void refreshTable(RepaymentPlan plan) {
		MyTableModel model = new MyTableModel();
	    model.addColumn("Jahr");
	    model.addColumn("Anfangsschuld");
	    model.addColumn("Zinsen");
	    model.addColumn("Tilgung");
	    model.addColumn("Sondertilgung");
	    model.addColumn("Endschuld");
		if (plan != null) {
			for (int i = 0; i < plan.getAnnuityList().size(); i++) {
				Annuity annuity = plan.getAnnuityList().get(i);
				Vector<String> vec = new Vector<String>();
				vec.add(annuity.getYear() + "");
				vec.add(f.format(annuity.getDebtStart()));
				vec.add(f.format(annuity.getInterest()));
				vec.add(f.format(annuity.getRepayment()));
				vec.add(f.format(annuity.getUnscheduledRepayment()));
				vec.add(f.format(annuity.getDebtEnd()));
				
			    model.addRow(vec);
			}
		}
	      table1.setModel(model);
	      table1.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
	      //table1.getColumnModel().getColumn(1).setPreferredWidth(460);
	      TableColumnModel colMod = table1.getColumnModel();
		  DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		  DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		  DefaultTableCellRenderer left = new DefaultTableCellRenderer();
		  center.setHorizontalAlignment(SwingConstants.CENTER);
		  right.setHorizontalAlignment(SwingConstants.RIGHT);
		  left.setHorizontalAlignment(SwingConstants.LEFT);
		  colMod.getColumn(0).setCellRenderer(center);
		  colMod.getColumn(1).setCellRenderer(right);
		  colMod.getColumn(2).setCellRenderer(right);
		  colMod.getColumn(3).setCellRenderer(right);
		  colMod.getColumn(4).setCellRenderer(right);
		  colMod.getColumn(5).setCellRenderer(right);
	}
	
	private void initMenuBar() {
      setJMenuBar(menubar);
      menubar.add(creditMenu);
      menubar.add(infoMenu);
      creditMenu.add(itemCredit);
      creditMenu.add(itemQuit);
      infoMenu.add(itemAbout);
	}
	
	private void initPanels() {
		JScrollPane scrollPane = new JScrollPane(table1);
	    panel1.add(scrollPane, BorderLayout.CENTER);
	    panel1.add(statusLabel, BorderLayout.SOUTH);
		getContentPane().add(panel1);
	}
	
	public void calculatePlan() {
		try {
		  refreshTable(new RepaymentPlan(this.credit));
			
		} catch(CalculationException e) {
		  JOptionPane.showMessageDialog(null, "ungültige Kreditparameter", "Fehler: ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}