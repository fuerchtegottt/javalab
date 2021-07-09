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


/**
 * Denon 2113 AVR web api wrapper
 * 
 *  supported commands:
 *  Z2ON / OFF   (switch on / off receiver)
 *  Z2CD         (switch to source CD)
 *  Z2IRP        (switch to source Internet Radio Player)
 *  Z2##         (set volume in percentage 00 - 99)
 *  Z2MUON / OFF (mute on / off)
 *  Z2FAVORITE#  (switch to favorite 1 - 3)
 * @author chg
 *
 */
public class DeviceController {
	public static final String apiCallPath = "/goform/formiPhoneAppDirect.xml?";
	public static final String Z2ON  = "Z2ON";
	public static final String Z2OFF = "Z2OFF";
	public static final String Z2VOL = "Z2";     //Z2##
	public static final String Z2FAVORITE1 = "Z2FAVORITE1";
	public static final String Z2FAVORITE2 = "Z2FAVORITE2";
	public static final String Z2FAVORITE3 = "Z2FAVORITE3";
	
	private static final String propertiesFilename = "denonRC.properties";
	private String ip;
	private String protocol;
	private boolean debug = false;
	public static void main(String[] args) {
		DeviceController dc = new DeviceController();

		  dc.setDebugMode(true);
		  dc.setZone2(true);
		  dc.setZone2Favorite(2);
	}
	
	public DeviceController() {
		this.readProps();
	}
	
	public void setDebugMode(boolean debug) {
		this.debug = debug;
	}
	public void executeCommand(String command) {
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
		return this.protocol + "://" + this.ip + apiCallPath + command;
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
	
	public void sleep(int sec) {
		try {
		  TimeUnit.SECONDS.sleep(sec);
		} catch(Exception e) {
			//do nothing
		}
	}
	
	public void setZone2(boolean switchOn) {
		if (switchOn) {
		executeCommand( Z2ON);
		} else {
			executeCommand( Z2ON);
		}
	}
	
	public void setZone2Volume(int volume) {
		String volCommand;
		int newVol = volume;
		if ( newVol < 0 || newVol > 100 ) {
			newVol = 15;
		}
		
		if ( newVol < 10 ) {
			volCommand = Z2VOL + "0" + newVol;
		} else {
			volCommand = Z2VOL + newVol;
		}
		
		executeCommand( volCommand);
	}
	
	public void setZone2Favorite(int fav) {
		switch(fav) {
		case 1:
			executeCommand( Z2FAVORITE1);
			break;
		case 2:
			executeCommand( Z2FAVORITE2);
			break;
		case 3:
			executeCommand( Z2FAVORITE3);
			break;
		default:
			executeCommand( Z2FAVORITE1);
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
