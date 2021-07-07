package de.g3ll3rt.jdenon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JDenonShell {
	public DeviceController cntl;
	boolean leaveShell = false;
	public JDenonShell() {
		cntl = new DeviceController();
		
	}
	public static void main(String[] args) {
		JDenonShell shell = new JDenonShell( );
		
		if (args.length < 1 ) {
			shell.callShell();
		} else {
			shell.parseArgs(args);
		}
		
	}
	
	public void parseArgs(String[] args) {
		
	}
	
	public String parseString(String command) {
		String message = "";
		switch(command) {
		case "exit":
			message = "bye";
			leaveShell = true;
			break;
		case "help":
		    message = getCommandList();
		    break;
		case "ver":
			message = getVersionInfo();
		    break;
	    default:
	    	message = "command '" + command + "' not found. \n \r" + getCommandList();
		}
	    	
		return message;
	}
	
	public void callShell() {
		String commandString;
		String output;
		Scanner in = new Scanner(System.in);
		System.out.println("");
		System.out.println("Welcome to JDenon CLI");
        do{
        	System.out.print("JDenon:\\> ");
            commandString = in.nextLine();
            output = parseString(commandString);
            System.out.println(output);
        }while(!leaveShell); 

	}
	
	public String getVersionInfo() {
		  StringBuilder str = new StringBuilder();
		  str.append("JDenon V1.0 \n ");
		  return str.toString();
	}
	
	public String getCommandList() {
	  StringBuilder str = new StringBuilder();
	  str.append("Command List: \n ");
	  str.append("help - command list \n ");
	  str.append("ver  - version info \n ");
	  return str.toString();
	}
}
