package de.g3ll3rt.jtaskplan;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class TPGui extends JFrame {
  TaskPlan taskplan = null;
 
  private JComboBox combobox1 = new JComboBox();
  private JLabel label1 = new JLabel("V" + TaskPlan.getVersion());
  private JMenuBar menubar = new JMenuBar();
  private JMenu menu1 = new JMenu("JTaskPlan");
  private JMenu menu2 = new JMenu("Info");
  private JMenuItem menuItem3 = new JMenuItem("about");
  private JMenuItem menuItem1 = new JMenuItem("add Task");
  private JMenuItem menuItem2 = new JMenuItem("Quit");
  
  private JPanel panel1 = new JPanel(new BorderLayout());
  private JTable table1 = new JTable();
  
  public TPGui() {
    taskplan = new TaskPlan();
    taskplan.readTasks();
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    
    combobox1.addItem("current tasks");
    combobox1.addItem("archive");
    
    
    setJMenuBar(menubar);
    menubar.add(menu1);
    menubar.add(menu2);
    menu1.add(menuItem1);
    menu1.add(menuItem2);
    menu2.add(menuItem3);
    
    JScrollPane scrollPane = new JScrollPane(table1);
    panel1.add(combobox1, BorderLayout.NORTH);
    panel1.add(label1, BorderLayout.SOUTH);
    panel1.add(scrollPane, BorderLayout.CENTER);
    getContentPane().add(panel1);

//   Image img = getToolkit().getImage("ico.gif");
//	setIconImage(img);
	
 //   URL url = ClassLoader.getSystemClassLoader().getResource("img/ico.gif");
 //   Icon icon = new ImageIcon(url);   
 //   setIconImage(icon);
    
    setTitle("JTaskPlan");
    setListeners();
    setSize(600,400);
    setLocation(50,50);
    refreshTable();
    setVisible(true);
  }
  
  private void setListeners() {
    addWindowListener(new WindowAdapter() {
	  public void windowClosing(WindowEvent e) {
	    doClose();
	  }
	});
    
    combobox1.addItemListener(new ItemListener() {
	  public void itemStateChanged(ItemEvent e) {
		refreshTable();
	  }
	});
    table1.addMouseListener(new MouseAdapter()
    	    {
    	      public int getClickedRow( MouseEvent e )
    	      {
                Point pt = e.getPoint() ;
                return table1.rowAtPoint(pt);
              }
            
    	    public void mouseClicked(MouseEvent e)
    	    {
    	      if (e.getClickCount() == 2)
    	      { 
    	    	int selectedIndex = Integer.parseInt(table1.getValueAt(getClickedRow(e),0).toString());
    	    	doEditTask(selectedIndex);
    	      }
    	    }
    	    });
    menuItem1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          doAddTask();
        }
      }); 
    menuItem2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doClose();
      }
    });
    menuItem3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	Object[] inputs = new Object[2];
		  	inputs[0] = new JLabel("JTaskPlan V" + TaskPlan.getVersion());
            inputs[1] = new JLabel("Christian Gellert 2006 - 2023");
            JOptionPane.showOptionDialog(
            new JFrame(), inputs, "Version", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, null,null);
        }
      });
  }
  private void doAddTask() {
    Object[] inputs = new Object[2];
	inputs[0] = new JLabel("Task");
	inputs[1] = new JTextField("new task");
	int result = JOptionPane.showOptionDialog(
	  this, inputs, "add task", 
	  JOptionPane.OK_CANCEL_OPTION, 
	  JOptionPane.INFORMATION_MESSAGE, 
	  null, null,null
    );
	if (result == JOptionPane.OK_OPTION) {
	  taskplan.insertTask(((JTextField)inputs[1]).getText());
	  taskplan.writeTasks();
      refreshTable();
	}
  }
  private void doEditTask(int idx) {
    Task task = (Task)taskplan.getTasks().get(idx);
    String msg = task.getMessage();
    boolean done = task.isDone();
    boolean erased = task.isErased();
    
    Object[] inputs = new Object[4];
    inputs[0] = new JLabel("Task");
    inputs[1] = new JTextField(msg);
    inputs[2] = new JCheckBox("done",done);
    inputs[3] = new JCheckBox("erased", erased);
    int result = JOptionPane.showOptionDialog(
      this, inputs, "edit task", 
      JOptionPane.OK_CANCEL_OPTION, 
      JOptionPane.INFORMATION_MESSAGE, 
      null, null,null
    );
    if (result == JOptionPane.OK_OPTION) {
      msg = ((JTextField)inputs[1]).getText();
      done = ((JCheckBox)inputs[2]).isSelected();
      erased = ((JCheckBox)inputs[3]).isSelected();
      task.setMessage(msg);
      task.setDone(done);
      task.setErased(erased);
      taskplan.updateTask(idx,task);
      taskplan.writeTasks();
      refreshTable();
    }
    
  }
  private void doClose() {
    System.exit(0);
  }
  private void refreshTable(){
	int viewMode = combobox1.getSelectedIndex(); // 0=currentTasks,1=archive
    ArrayList<Task> taskList = taskplan.getTasks();
    MyTableModel model = new MyTableModel();
    model.addColumn("");
    model.addColumn("Task");
    model.addColumn("Date");
    model.addColumn("Done");
    
    for(int i=0; i <taskList.size();++i) {
      Task task = (Task)taskList.get(i);
      String msg = "  "+ task.getMessage();
      String date = task.getDate();
      String done = " ";
      if (task.isDone()) done ="X";
      if ((task.isErased()==false && viewMode == 0)||(task.isErased()==true && viewMode ==1)) {
        Vector<String> vec = new Vector<String>();
        vec.add(""+i);
        vec.add(msg);
        vec.add(date);
        vec.add(done);
        model.addRow(vec);
      }
      table1.setModel(model);
      table1.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
      table1.getColumnModel().getColumn(1).setPreferredWidth(460);
      TableColumnModel colMod = table1.getColumnModel();
	  DefaultTableCellRenderer center = new DefaultTableCellRenderer();
	  DefaultTableCellRenderer right = new DefaultTableCellRenderer();
	  center.setHorizontalAlignment(SwingConstants.CENTER);
	  right.setHorizontalAlignment(SwingConstants.RIGHT);
	  colMod.getColumn(3).setCellRenderer(center);
	  colMod.getColumn(0).setCellRenderer(right);
    }
  }
  
  /*
  public static void main(String[] args) {
	System.out.println("Hallo Welt");
    new TPGui();
  }
  */
}
