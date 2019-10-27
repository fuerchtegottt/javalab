package de.g3ll3rt.StringOperator;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StringOperatorGUI extends Frame{
	private static final long serialVersionUID = 1L;
	TextArea inputConsole;
	TextArea outputConsole;
	StringOperationBase operator;
	Panel midPan;
	Panel lowerPan;
	MenuItem itmExecute;
	MenuItem itmImport;
	MenuItem itmExport;
	MenuItem itmExportClp;
	MenuItem itmConvert;
	boolean autoFillClpbrd = false;  //auto-clipboard-function disabled
	FileDialog fdImport;
	FileDialog fdExport;
	
	public StringOperatorGUI(StringOperationBase operator){
        this.operator = operator;
	    initControls();
	    initMenu();
	    initListeners();
	}
	
	public StringOperatorGUI(StringOperationBase operator, String defaultInput){
      this(operator);
      inputConsole.setText(defaultInput); 
	}
	
	public StringOperatorGUI(StringOperationBase operator, boolean autoFill){
	      this(operator);
	      setClpbrdAutoFill(autoFill);
	}	
	
	public void setClpbrdAutoFill(boolean autoFill){
		this.autoFillClpbrd = autoFill;
	}
	
	private void initControls(){
	    inputConsole = new TextArea(5,128);
	    outputConsole = new TextArea(35,128);
	    outputConsole.setEditable(false);
	    outputConsole.setBackground(Color.black);
	    outputConsole.setForeground(Color.white);
	    midPan   = new Panel();
	    lowerPan = new Panel();
		midPan.setLayout(new FlowLayout());
		lowerPan.setLayout(new FlowLayout());
		midPan.add(inputConsole);
		lowerPan.add(outputConsole);
		
        setLayout(new FlowLayout());
		add(midPan);
		add(lowerPan);
		
		setSize(935, 720);
		setTitle(operator.getTitle());
		setVisible(true);
		fdImport = new FileDialog(this, "Datei importieren", FileDialog.LOAD);
		fdExport = new FileDialog(this, "Datei exportieren", FileDialog.SAVE);

	}
	
	private void initMenu(){
		MenuBar menbar = new MenuBar();
		Menu opmen = new Menu("Operation");
		itmExecute = new MenuItem("execute");
		itmImport = new MenuItem("import from file");
		itmExport = new MenuItem("export to file");
		itmExportClp = new MenuItem("export to clpbrd");
		itmConvert   = new MenuItem("1-click-convert");
		
		opmen.add(itmConvert);
		opmen.add(itmImport);
		opmen.addSeparator();
		opmen.add(itmExport);
		opmen.add(itmExportClp);
		opmen.addSeparator();
		opmen.add(itmExecute);
		itmExecute.setShortcut(new MenuShortcut(KeyEvent.VK_F8));
		menbar.add(opmen);
		setMenuBar(menbar);
	}

	private void initListeners(){
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(1);
			}
		});
		
		itmExecute.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				doOperation();
			}
		});
		
		itmImport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				importFromFile();
			}
		});
		
		itmExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				exportToFile();
			}
		});
		itmExportClp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dropOnClpbrd();
			}
		});
		itmConvert.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				oneClickConvert();
			}
		});
		
		
		
		
	}
	
	private void doOperation(){
		try{
		  outputConsole.setForeground(Color.white);
		  outputConsole.setText( operator.execute(inputConsole.getText()) );
		  if (autoFillClpbrd){
		    dropOnClpbrd();  
		  }
		}catch (Exception e){
			
	        writeErrorMsg(e.toString());
		}
	}
	
	private void importFromFile(){
		fdImport.setVisible(true);
		if (!(fdImport.getFile() == null || (fdImport.getDirectory() == null))){
			try{
	    inputConsole.setText(StringOperationFileSystem.readFile((fdImport.getDirectory() + fdImport.getFile())));
			} catch(Exception e){
				writeErrorMsg(e.toString());
			}
		}
	}
	
	private void exportToFile(){
		fdExport.setVisible(true);
		if (!(fdExport.getFile() == null || (fdExport.getDirectory() == null))){
		  try{
		    StringOperationFileSystem.writeFile(outputConsole.getText(), (fdExport.getDirectory() + fdExport.getFile()));
		  } catch (Exception e){
			  writeErrorMsg(e.toString());
		  }
		}
	}
	
	private void oneClickConvert(){
		fdImport.setVisible(true);
		String outputFile;
		if (!(fdImport.getFile() == null || (fdImport.getDirectory() == null))){
			try{
	    inputConsole.setText(StringOperationFileSystem.readFile((fdImport.getDirectory() + fdImport.getFile())));
			} catch(Exception e){
				writeErrorMsg(e.toString());
			}
		}
		doOperation();
		outputFile = fdImport.getFile() + operator.getDefaultOutputExtension();
		try{
		    StringOperationFileSystem.writeFile(outputConsole.getText(), (fdImport.getDirectory() + outputFile));
		  } catch (Exception e){
			  writeErrorMsg(e.toString());
		  }
	}
	
	private void dropOnClpbrd(){
		    StringSelection stringSelection = new StringSelection(outputConsole.getText());
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        clipboard.setContents(stringSelection, null);
    }
	
	private void writeErrorMsg(String errMsg){
		outputConsole.setForeground(Color.red);
		outputConsole.setText(errMsg);
	}
}