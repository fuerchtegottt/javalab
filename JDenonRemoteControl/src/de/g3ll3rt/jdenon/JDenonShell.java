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
	    StringBuilder str = new StringBuilder();
		for(int i = 0; i< args.length; i++) {
			str.append(args[i] + " ");
		}
		toConsole(parseString(str.toString()));
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
	    	try {
	    	if (command.substring(0,4).equals("call")){
	    		message = parseCall(command);
	    	} else {
		    	message = "command '" + command + "' not found. \n \r" + getCommandList();	
	    	}
	    	}catch(StringIndexOutOfBoundsException e) {
	    		message = "command not found";
	    	}
	    	break;
	    	
		}
	    	
		return message;
	}
	
	public String parseCall(String callString) {
		String output;
		String cmd;
		try {
		  cmd = callString.substring(5);	
		  output = "  ..sending command " + cmd;
		  cntl.executeCommand(cmd);
		} catch(StringIndexOutOfBoundsException e) {
			output = "call needs to be followed by a command \n \r" + getCommandList();
		}
		return output;
	}
	
	public void callShell() {
		String commandString;
		String output;
		Scanner in = new Scanner(System.in);
		toConsole("");
		toConsole("Welcome to JDenon CLI");
        do{
        	System.out.print("JDenon:\\> ");
            commandString = in.nextLine();
            output = parseString(commandString);
            toConsole(output);
        }while(!leaveShell); 
        in.close();
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
	  str.append("call - call command \n");
	  str.append("\n");
	  str.append("commands: \n");
	  str.append("Z2ON / OFF   (switch on / off receiver) \n");
	  str.append("Z2CD         (switch to source CD) \n" );
	  str.append("Z2IRP        (switch to source Internet Radio Player) \n");
	  str.append("Z2##         (set volume in percentage 00 - 99) \n");
	  str.append("Z2MUON / OFF (mute on / off) \n");
	  str.append("Z2FAVORITE#  (switch to favorite 1 - 3) \n");
	  return str.toString();
	}
	
	public void toConsole(String inString) {
		//placeholder for better output option
		System.out.println(inString);
	}
}
