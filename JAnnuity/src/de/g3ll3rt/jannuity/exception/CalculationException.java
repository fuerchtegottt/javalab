package de.g3ll3rt.jannuity.exception;

public class CalculationException extends Exception {
	public CalculationException(String text, Exception origin) {
		super(text, origin);
	}
	
	public CalculationException(String text) {
		super(text);
	}
}
