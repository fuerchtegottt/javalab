package de.g3ll3rt.jannuity.gui;

import javax.swing.table.*;

public class MyTableModel extends DefaultTableModel {
  public boolean isCellEditable(int row, int column){return false;}
}
