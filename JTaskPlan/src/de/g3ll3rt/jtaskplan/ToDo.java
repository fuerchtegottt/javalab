package de.g3ll3rt.jtaskplan;

import java.util.ArrayList;

/**
 * @author chg
 * 
 * JTastPlan Param Dispatcher
 *   options (param 1)
 *     [no params] GUI START
 *     [-l]        print task list
 *     [-a]        add new task with text from following params
 *     [-c]        check / unckeck task
 *     [-g]        call GUI
 *     [-h]        print help screen
 *     [-v]        version     
 *
 */
public class ToDo {
	public ToDo(String[] args) {
		try {
		processParams( new Params(args));
		} catch(ParamMissmatchException e) {
			toConsole(e.toString());
		}
	}
	
	private void processParams(Params params) throws ParamMissmatchException {
		switch(params.getCmd()) {
		case "-h":
			toConsole(getHelpString());
			break;
		case "-g":
			new TPGui();
			break;			
		case "-a":
			addTask( params.getTaskText());
			break;
		case "-l":
			toConsole(getTasksString());
			break;
		case "-v":
			toConsole(getVersion());
			break;
		case "-c":
			checkTask(params.getTaskId(), params.isChecked() );
			break;
		default:
			toConsole(getVersion());
			toConsole(getHelpString());
			break;
		}
	}
	
	private String getVersion() {
		return "JTaskPlan V" + TaskPlan.getVersion();
	}
	
	private String getHelpString() {
		  StringBuilder str = new StringBuilder();
		  str.append("known parameters: \n");
		  str.append(" -h                 : help \n");
		  str.append(" -g                 : GUI \n");
		  str.append(" -l                 : print list \n");
		  str.append(" -a [text]          : add task \n");
		  str.append(" -c [task id] [X| ] : set state \n");
		  str.append(" -v                 : version info \n");		  
		  return str.toString();
	}
	
	private String getTasksString() {
		StringBuilder str = new StringBuilder();
		TaskPlan plan = new TaskPlan();
		plan.readTasks();
		ArrayList<Task> taskList = plan.getTasks();
		str.append("                                     ");
		str.insert(0,  "ID");
		str.insert(3,  "| TEXT");
		str.insert(40, "| STATUS");
		str.append("\n");
		str.append("------------------------------------------------- \n");
		for(int i=0; i <taskList.size();++i) {
			 Task task = (Task)taskList.get(i);
			 if (task.isErased() == false) {
			   str.append( task.toString() + "\n");
			 }
		}
		return str.toString();
	}
	
	private void addTask( String taskTitle) {
		TaskPlan plan = new TaskPlan();
		plan.readTasks();
		plan.insertTask(taskTitle);
		plan.writeTasks();
		toConsole("Task :" + taskTitle + " added.");
	}
	
	
	private void checkTask(int taskId, boolean checked) throws ParamMissmatchException{
		TaskPlan plan = new TaskPlan();
		plan.readTasks();
		ArrayList<Task> taskList = plan.getTasks();
		
		try {
		  //System.out.println("searching for task " + taskId + " to be switched to " + checked);
		  Task task = taskList.get(taskId);
	      //System.out.println("Task found: " + task);
		  task.setDone(checked);
		  plan.updateTask(taskId, task);
		  plan.writeTasks();
		} catch (Exception e) {
			throw new ParamMissmatchException("Message ID not found!");
		}
	}

	private void toConsole(String inString) {
		System.out.println(inString);
	}
	
	public static void main(String [] args) {
		  new ToDo( args );	
	}		
}