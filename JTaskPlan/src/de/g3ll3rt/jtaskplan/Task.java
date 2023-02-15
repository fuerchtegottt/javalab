package de.g3ll3rt.jtaskplan;

public class Task {
  private int taskId = 0;
  private String message = "";
  private String date = "";
  private boolean done = false;
  private boolean erased = false;
  
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public boolean isDone() {
	return done;
}
public void setDone(boolean done) {
	this.done = done;
}
public boolean isErased() {
	return erased;
}
public void setErased(boolean erased) {
	this.erased = erased;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

public void setTaskId(int taskId) {
	this.taskId = taskId;
}

public String toString() {
	  StringBuilder str = new StringBuilder();
	  str.append("                                                   ");
	  str.insert(0,  taskId);
	  str.insert(3, "|");
	  // limit console output to 30 chars
	  if (message.length() > 29) {
		  str.insert(4, message.substring(0, 30));
	  } else {
		  str.insert(4, message);  
	  }
	  if (done) {
		  str.insert(40, "| DONE");
	  } else {
		  str.insert(40, "|");
	  }
	  return str.toString();
}
  
}
