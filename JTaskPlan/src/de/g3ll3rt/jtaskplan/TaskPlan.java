package de.g3ll3rt.jtaskplan;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TaskPlan {
  final String filename = "tasks.dat";
  static private String version = "1.0";
  private ArrayList<Task> taskList = null;
  public static String getVersion() {
	  return version;
  }
  public void readTasks() {
    try {
      if(new File(filename).exists()== false) { //Anlegen einer neuen Taskdatenbank wenn keine vorhanden
        taskList = new ArrayList<Task>();
        Task firstTask = new Task();
        firstTask.setDate(getDate());
        firstTask.setMessage("Welcome to JTaskPlan");
        firstTask.setDone(true);
        firstTask.setErased(false);
        taskList.add(firstTask);
        writeTasks();
      }
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      String tmp;
      String[] splitted;
      taskList = new ArrayList<Task>();
      while((tmp = br.readLine()) != null) {
        splitted = tmp.split(";");
        Task tmpTask = new Task();
        tmpTask.setMessage(splitted[0]);
        tmpTask.setDate(splitted[1]);
        tmpTask.setDone(splitted[2].equals("1"));
        tmpTask.setErased(splitted[3].equals("1"));
        tmpTask.setTaskId(taskList.size());
        taskList.add(tmpTask);
      }
      if (taskList.size() < 1) taskList = null;
    } catch(Exception e){System.out.println(e);}
  }
  public void writeTasks() {
	  if (taskList != null){
	    try {
	      BufferedWriter out = new BufferedWriter (new FileWriter (filename,false));
	      for(int i=0; i<taskList.size();i++) {
	    	Task actualTask = (Task)taskList.get(i);
	    	String message = actualTask.getMessage();
	    	String date = actualTask.getDate();
	    	String done = "0";
	    	String erased = "0";
	    	if(actualTask.isDone()) done ="1";
	    	if(actualTask.isErased()) erased ="1";
	    	out.write(message + ";" + date + ";" + done + ";" + erased);
	    	out.newLine();
	      }
	      out.flush();
	      out.close();
	    } catch(Exception e){System.out.println(e);}
	  }
  }
  public void updateTask(int taskId, String msg, boolean done, boolean erased) {
    if(taskList != null) {
      if(taskId < taskList.size()) {
        Task oldTask = (Task)taskList.get(taskId);
        oldTask.setMessage(msg);
        oldTask.setDone(done);
        oldTask.setErased(erased);
        taskList.set(taskId,oldTask);
      }
    }
  }
  public void updateTask(int taskId, Task inTask) {
	updateTask(taskId,inTask.getMessage(),inTask.isDone(),inTask.isErased());
  } 
  public void insertTask(String msg) {
	if(taskList != null) {
	  Task newTask = new Task();
	  newTask.setDate(getDate());
	  newTask.setDone(false);
	  newTask.setErased(false);
	  newTask.setMessage(msg);
	  newTask.setTaskId( taskList.size());
	  taskList.add(newTask);
	}
  }
  public ArrayList<Task> getTasks() {return taskList;}
  private String getDate() {
	Date x = new Date();
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	return df.format(x);
  }
}
