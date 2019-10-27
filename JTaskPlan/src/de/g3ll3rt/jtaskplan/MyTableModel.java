package de.g3ll3rt.jtaskplan;

import javax.swing.table.*;

public class MyTableModel extends DefaultTableModel {
  public boolean isCellEditable(int row, int column){return false;}
}
