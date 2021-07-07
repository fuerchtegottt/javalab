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
import java.util.concurrent.TimeUnit;

public class DeviceController {
	private final String propertiesFilename = "denonRC.properties";
	private String ip;
	private String protocol;
	private boolean debug = false;
	public static void main(String[] args) {
		DeviceController dc = new DeviceController();
		
		try {
			/*
		  dc.setDebugMode(true);
		  dc.setZone2(true);
		  dc.setZone2Volume(5);
		  dc.setZone2Favorite(2);
		  TimeUnit.SECONDS.sleep(1);
		  dc.setZone2Volume(10);
		  TimeUnit.SECONDS.sleep(1);
		  dc.setZone2Volume(15);
		  TimeUnit.SECONDS.sleep(1);
		  dc.setZone2Volume(20);
		  TimeUnit.SECONDS.sleep(1);
		  dc.setZone2Volume(25);
		  TimeUnit.SECONDS.sleep(1);
		  dc.setZone2Volume(30);
		  */
		  dc.setZone2Favorite(2);
		} catch(Exception e){
			e.printStackTrace();
		}
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
		return this.protocol + "://" + this.ip + APICommand.apiCallPath + command;
	}
	
	public Properties writeDefaultProps() {
		Properties prop = null;
		
		try (OutputStream output = new FileOutputStream(this.propertiesFilename)) {
			prop = new Properties();
			prop.setProperty("rc.ip"        , "192.168.2.25");
			prop.setProperty("rc.protocol"  , "http");
			prop.save(output, "denon remote connection settings");
		} catch (IOException io) {
            io.printStackTrace();
        }
		return prop;
	}
	
	public void setZone2(boolean switchOn) {
		if (switchOn) {
		executeCommand( APICommand.Z2ON);
		} else {
			executeCommand( APICommand.Z2ON);
		}
	}
	
	public void setZone2Volume(int volume) {
		String volCommand;
		int newVol = volume;
		if ( newVol < 0 || newVol > 100 ) {
			newVol = 15;
		}
		
		if ( newVol < 10 ) {
			volCommand = APICommand.Z2VOL + "0" + newVol;
		} else {
			volCommand = APICommand.Z2VOL + newVol;
		}
		
		executeCommand( volCommand);
	}
	
	public void setZone2Favorite(int fav) {
		switch(fav) {
		case 1:
			executeCommand( APICommand.Z2FAVORITE1);
			break;
		case 2:
			executeCommand( APICommand.Z2FAVORITE2);
			break;
		case 3:
			executeCommand( APICommand.Z2FAVORITE3);
			break;
		default:
			executeCommand( APICommand.Z2FAVORITE1);
			break;	
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
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
	}
}
