package de.g3ll3rt.japfelsee;

public class JApfelseeException extends Exception {
	public JApfelseeException(String text, Exception origin) {
		super(text, origin);
	}
	
	public JApfelseeException(String text) {
		super(text);
	}
}
