package model;

public class ClubNotFoundException extends Exception {

	public ClubNotFoundException() {
		super();
	}
	
	public ClubNotFoundException(String message) {
		super(message);
	}
	
	public ClubNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ClubNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
