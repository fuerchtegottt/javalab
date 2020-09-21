package mrpParser;

/*------------------------------------------------------------------------------
  class:       MprParser
  version:     0.2
  author:      Christian Gellert 
  update:      22.10.2005
  platform:    JDK verion 1.5.0 
  description: application for converting CSV-formed Mpr-Output into CSV-formed
               5-rows-per-row file format.
------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MprParser extends JFrame
{
  private ArrayList<String[]> data;
  String fileIn = "";
  String fileOut = "";
  JLabel label1 = new JLabel("     Input File: /");
  JLabel label2 = new JLabel("     Output File: /");
  JTextArea textArea = new JTextArea();
  JTextArea jt = new JTextArea(10,15);
  
  public MprParser()
  {
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent e)
        {
          System.exit(0);
        }
      });
    setTitle("Mr. MprParser   (-;");
    setResizable(false);
    setBounds(200,200,600,400);
    getContentPane().setLayout(new BorderLayout());
    add(label1,BorderLayout.NORTH);
    add(label2,BorderLayout.SOUTH);
    jt.setEditable(false);
    add(new JScrollPane(jt), BorderLayout.CENTER);
    
    JMenuBar menbar = new JMenuBar();
    JMenu menu1 = new JMenu("Program");
    JMenuItem item1 = new JMenuItem("Specify INPUT file");
    JMenuItem item2 = new JMenuItem("Specify OUTPUT file");
    JMenuItem item3 = new JMenuItem("Start conversion");
    JMenuItem item4 = new JMenuItem("Quit");
    menu1.add(item1);
    menu1.add(item2);
    menu1.add(item3);
    menu1.add(item4);
    menbar.add(menu1);
    
    item1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        getInputFile();
      }
    });
    
    item2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        getOutputFile();
      }
    });
    
    item3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        convert();
      }
    });
    
    item4.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
    });
    this.setJMenuBar(menbar);
    toConsole("--- Mr. MRP V0.1 ---");
    setVisible(true);
  }
  
  public static void main(String[] args)
  {
    new MprParser(); 
  }
  
  public void readFile(String filename)
  {
    try
    {
      toConsole("Reading records of " +fileIn + " ...");
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      String s_line;
      while((s_line = br.readLine()) != null)
      {
        String next;
        String chain = s_line + ";";
        if ((next = br.readLine()) != null) chain = chain + next + ";";
        if ((next = br.readLine()) != null) chain = chain + next + ";";
        if ((next = br.readLine()) != null) chain = chain + next + ";";
        if ((next = br.readLine()) != null) chain = chain + next + "; \r\n";
       data.add(chain.split(";"));
      }
    }
    catch(FileNotFoundException x) { toConsole("FILE NOT FOUND");}
    catch(IOException x) { toConsole("I/O Exception");}
    
    toConsole("Number of output records : " + data.size());
    
  }
  
  public void getInputFile()
  {
    FileDialog load = new FileDialog(new Frame(), "Select Input File",FileDialog.LOAD);
    load.setVisible(true);
      if(load.getFile() != null)
      {
        fileIn = (load.getDirectory() + load.getFile());
        label1.setText("     Input File : "+ fileIn);
      }
  }
  
  public void getOutputFile()
  {
    FileDialog save = new FileDialog(new Frame(), "Specify Output File",FileDialog.SAVE);
    save.setVisible(true);
    if(save.getFile() != null)
    {
      fileOut = (save.getDirectory() + save.getFile());
      label2.setText("     Output File : "+ fileOut);
    }
  }
  
  public void toConsole(String text)
  {
    jt.append("    " + text + "\n");
  }
  
  public void convert()
  {
    data = null;
    data = new ArrayList<String[]>();
    if ((fileIn.equals("")) || (fileOut.equals("")))
        {
          toConsole("Error!: input/output file has to be specified first !!!");
        }
    else
    {
      readFile(fileIn);
      writeFile(fileOut);
    }
  }
  
  public void writeFile(String filename)
  {
    try
    {
      if (data != null)
      {
        FileWriter f = new FileWriter(filename, true);
        BufferedWriter b = new BufferedWriter(f);
        PrintWriter p = new PrintWriter(b);
        toConsole("Writig Records into " +fileOut + " ...");
        p.print(";");
        for(int i = 0; i < data.size(); i++)
        {
          String[] out = data.get(i);
          String line = "";
          for (int j = 0; j < out.length; j++)
          {
            line = line  + out[j] + ";";
          }
          p.print(line);
        }
        b.flush();
        f.close();
        toConsole("... done!");
        toConsole(" ");
       }
       else
       {
         toConsole("Fehler : Daten konnten nicht geschrieben werden!!!");
       }
     }
     catch (Exception e){toConsole(e.toString());}
  }
}