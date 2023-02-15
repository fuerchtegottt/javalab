package de.g3ll3rt.jtaskplan;

public class Params {
	
	private String cmd = " ";
	private String taskText = " ";
	private int taskId = 0 ;
	private boolean checked = false;
	public Params(String[] args) throws ParamMissmatchException{
		
		try {
		  if (args.length > 0) {
			cmd = args[0];
		  } else {
			cmd = "-g";
		  }
		
		  if (args.length > 1) {
			  switch(cmd) {
			    case "-a":
				  taskText = getTextFromArgs(args);
				  break;
			    case "-c":
			      taskId = Integer.parseInt(args[1]);
				  checked = false;
				  break;
		      }  
		  }	
		
		  if (args.length > 2) {
			  switch(cmd) {
			  case "-c":
				  if ( args[2].charAt(0) == 'X' ) {
				  	checked = true;
				  } else {
					checked = false;
				  }
				  break;
			  }
		  }
		} catch(Exception e) {
			throw new ParamMissmatchException("parameter missmatch");
		}
	}
	
	private String getTextFromArgs(String[] args) {
		  StringBuilder str = new StringBuilder();
		  for (int i = 0; i < args.length; i++) {
			  if (i > 0) {
				  str.append( args[i] + " ");
			  }
		  }
		  return str.toString();
	}
	
	public String getCmd() {
		return cmd;
	}
	public String getTaskText() {
		return taskText;
	}
	public int getTaskId() {
		return taskId;
	}
	public boolean isChecked() {
		return checked;
	}
	
	public String toString() {
		  StringBuilder str = new StringBuilder();
		  str.append("PARAMS: /n");
		  str.append("CMD + Text + ID + CHECKED" + "\n");
		  str.append( cmd + " " + taskText + " " + taskId + " " + checked);
		  str.append("\n");		  
		  return str.toString();
	}
}