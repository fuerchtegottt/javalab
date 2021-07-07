package de.g3ll3rt.jdenon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

public class DeviceController {
	private final String propertiesFilename = "denonRC.properties";
	private String ip;
	private String protocol;
	private String commandZone2On;
	private String commandZone2Off;
	private boolean debug = false;
	public static void main(String[] args) {
		DeviceController dc = new DeviceController();
		dc.setDebugMode(true);
		dc.setZone2(true);
	}
	
	public DeviceController() {
		this.readProps();
	}
	
	public void setDebugMode(boolean debug) {
		this.debug = debug;
	}
	private void executeCommand(String command) {
		String urlString = getUrlString(command);
		InputStream result;
		if (debug) {
		  System.out.println("dc calling: " + urlString);
		}
		try {
		result = new URL(urlString).openStream();
		
		if(debug) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(result));
		    System.out.println("receiver answering: ");
			String line = null;
			while ((line = reader.readLine()) != null) {
			    System.out.println(line);
			}
			reader.close();
		}
		
		} catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	private String getUrlString(String command) {
		return this.protocol + "://" + this.ip + command;
	}
	
	public Properties writeDefaultProps() {
		Properties prop = null;
		
		try (OutputStream output = new FileOutputStream(this.propertiesFilename)) {
			prop = new Properties();
			prop.setProperty("rc.ip"        , "192.168.2.25");
			prop.setProperty("rc.protocol"  , "http");
			prop.setProperty("rc.commandZone2On" , "/goform/formiPhoneAppDirect.xml?Z2ON");
			prop.setProperty("rc.commandZone2Off", "/goform/formiPhoneAppDirect.xml?Z2OFF");
			prop.save(output, "denon remote connection settings");
		} catch (IOException io) {
            io.printStackTrace();
        }
		return prop;
	}
	
	public void setZone2(boolean switchOn) {
		if (switchOn) {
		executeCommand( this.commandZone2On);
		} else {
			executeCommand( this.commandZone2Off);
		}
	}
	
	public void readProps() {
		Properties prop;
        try (InputStream input = new FileInputStream(this.propertiesFilename)) {

            prop = new Properties();

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            prop = writeDefaultProps();
        }
           
        if (prop != null) {
        	try {
        	  this.ip = prop.getProperty("rc.ip");
        	  this.protocol   = prop.getProperty("rc.protocol");
        	  this.commandZone2On  = prop.getProperty("rc.commandZone2On");
        	  this.commandZone2Off = prop.getProperty("rc.commandZone2Off");
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
	}
}
