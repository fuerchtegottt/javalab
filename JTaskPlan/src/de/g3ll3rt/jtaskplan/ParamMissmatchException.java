package de.g3ll3rt.jtaskplan;

  public class ParamMissmatchException extends Exception {
	public ParamMissmatchException(String text, Exception origin) {
		super(text, origin);
	}
		
	public ParamMissmatchException(String text) {
		super(text);
	}
}


