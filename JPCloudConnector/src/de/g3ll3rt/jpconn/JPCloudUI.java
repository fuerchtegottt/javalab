package de.g3ll3rt.jpconn;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * visit oauth page go get code:
 * https://my.pcloud.com/oauth2/authorize?client_id=###CLIENTID###&response_type=code
 */

public class JPCloudUI extends Frame {
	
	private static final long serialVersionUID = 1L;
	final String choiceUserInfo = "UserInfo";
	final String choiceWriteArchive = "writeArchive";
	TextArea outputConsole;
	Panel inputPanel;
	Panel consolePanel;
	Button btnConnect = new Button("connect");
	Button btnExecute = new Button("execute");
	Button btnLink    = new Button("oauth2");
	TextField clientIdField;
	TextField clientSecretField;
	TextField clientCodeField;
	TextField connStateField;
	MenuItem itmLogin;
	MenuItem itmLogout;
	MenuItem itmExecuteCall;
	Choice operationChoice;
	JPCloudConnection conn = new JPCloudConnection();
	
    private void open(URI uri) {
	    if (Desktop.isDesktopSupported()) {
	      try {
	        Desktop.getDesktop().browse(uri);
	      } catch (IOException e) { 
            writeMessage(e.toString(), true);
	      }
	    } else { 
	    	writeMessage("desktop not supported", true);
	    }
	  }	
	
	public JPCloudUI() {
		initControls();
		initMenu();
		initListeners();
		
		setDefaultCredentials();
		setGuiConnected(false);
	}	
	
	private void initControls() {
		clientIdField = new TextField();
		clientSecretField = new TextField();
		clientSecretField.setEchoChar('*');
		clientCodeField = new TextField();
		clientCodeField.setEchoChar('*');
		connStateField  = new TextField();
		connStateField.setEditable(false);
		
	    outputConsole = new TextArea(35,128);
	    outputConsole.setEditable(false);
	    outputConsole.setBackground(Color.black);
	    outputConsole.setForeground(Color.white);
	    
	    operationChoice = new Choice();
	    operationChoice.add(choiceUserInfo);
	    operationChoice.add(choiceWriteArchive);
	    
		inputPanel = new Panel();
		inputPanel.setLayout(new GridLayout(6,2));
		inputPanel.add(new Label("Client ID"));
		inputPanel.add(clientIdField);
		inputPanel.add(new Label("Client Secret"));
		inputPanel.add(clientSecretField);
		inputPanel.add(inputPanel.add(btnLink));  //client code field
		inputPanel.add(clientCodeField);
		inputPanel.add(new Label("Method"));
		inputPanel.add(operationChoice);
		inputPanel.add(new Label("connection state"));
		inputPanel.add(connStateField);
		inputPanel.add(btnConnect);
		inputPanel.add(btnExecute);
		
		consolePanel = new Panel();
		consolePanel.setLayout(new FlowLayout());
		consolePanel.add(outputConsole);
		
        setLayout(new FlowLayout());
		add(inputPanel);
		add(consolePanel);
		
		setSize(935, 760);
		setTitle("PCloud UI");
		setVisible(true);
	}
	
	private void initMenu() {
		MenuBar menbar = new MenuBar();
		Menu opmen = new Menu("Operation");
		itmLogin = new MenuItem("login");
		itmLogout = new MenuItem("logout");
		itmExecuteCall = new MenuItem("execute");
		
		opmen.add(itmLogin);
		opmen.add(itmLogout);
		opmen.add(itmExecuteCall);
		itmExecuteCall.setShortcut(new MenuShortcut(KeyEvent.VK_F8));
		menbar.add(opmen);
		setMenuBar(menbar);
		setGuiConnected(conn.isConnected());
	}
	
	private URI getOauthURI() {
		URI retUri;
		String urlString = "https://my.pcloud.com/oauth2/authorize?client_id="
				           + clientIdField.getText()
				           + "&response_type=code";
				        
		try {
			retUri = new URI(urlString);
		} catch (URISyntaxException e) {
           retUri = null;
		}
		return retUri;
	}
	
	private void initListeners() {
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(1);
			}
		});
		
		itmLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				login();
			}
		});
		
		itmLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				logout();
			}
		});		
		
		itmExecuteCall.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				execute();
			}
		});	
		
		btnConnect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				login();
			}
		});	
		btnExecute.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				execute();
			}
		});	
		
		btnLink.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					open(getOauthURI());
			}
		});	
	}
	
	private void login() {
		JPCloudResponse resp = conn.connect(clientIdField.getText(), clientSecretField.getText(), clientCodeField.getText());
		writeMessage(resp.toString());
		setGuiConnected(conn.isConnected());
	}
	
	private void logout() {
		JPCloudResponse resp = conn.logout();
		writeMessage(resp.toString());
		setGuiConnected(conn.isConnected());
	}	
	
	private void execute() {
	   String operation = operationChoice.getSelectedItem();
	   
	   JPCloudResponse resp = new JPCloudResponse("method not implemented");
	   switch(operation) {
	   case choiceUserInfo:
	     resp = conn.getUserInfo();
	     break;
	   case choiceWriteArchive:
		 resp = conn.getDirectory();
		 break; 
	   }
	   
		 writeMessage(resp.toString());
	}
	
	public static void main(String [] args) {
		new JPCloudUI(); 
	}
	
	private void writeMessage(String message) {
		writeMessage(message, false);
	}
	
	private void writeMessage(String message, boolean isError) {
      if (isError) {
    	  outputConsole.setForeground(Color.red);
    	  outputConsole.setText(message);
      } else {
    	  outputConsole.setForeground(Color.white);
          outputConsole.append(message);
      }
      outputConsole.append("\r\n");
	}
	
	public void setGuiConnected(boolean connState){
		if (connState == true) {
			connStateField.setText("connected");
			connStateField.setBackground(Color.green);
			operationChoice.setEnabled(true);
			clientIdField.setEditable(false);
			clientSecretField.setEditable(false);
			clientCodeField.setEditable(false);
			itmLogin.setEnabled(false);
			itmExecuteCall.setEnabled(true);
			btnConnect.setEnabled(false);
			btnExecute.setEnabled(true);
			itmLogout.setEnabled(true);
		} else {
			connStateField.setText("disconnected");
			connStateField.setBackground(Color.red);
		    operationChoice.setEnabled(false);
			clientIdField.setEditable(true);
			clientSecretField.setEditable(true);
			clientCodeField.setEditable(true);
			itmLogin.setEnabled(true);
			itmExecuteCall.setEnabled(false);
			btnConnect.setEnabled(true);
			btnExecute.setEnabled(false);
			itmLogout.setEnabled(false);
		}
	}
	
	private void setDefaultCredentials() {
		clientIdField.setText("###yourClientID###");
		clientSecretField.setText("###yourClientSecret###");
	}
}