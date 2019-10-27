package de.g3ll3rt.StringOperator;

public class HelloWorldOperator implements StringOperationBase {

	@Override
	public String execute(String output) throws Exception {
		// TODO Auto-generated method stub
		return "Hello, " + output;
	}

	@Override
	public String getTitle() {
      return "HalloOperator";
	}

	@Override
	public String getDefaultOutputExtension() {
		// TODO Auto-generated method stub
		return "TXT";
	}
	
	public static void main(String[] args){
		new StringOperatorGUI(new HelloWorldOperator());
	}

}
