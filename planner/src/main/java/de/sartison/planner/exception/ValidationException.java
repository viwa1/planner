package de.sartison.planner.exception;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 6356269108245229072L;
	
	private String title;

	public ValidationException(String title, String message) {
		super(message);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
