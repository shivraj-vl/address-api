package com.org.app.exceptions;

/**
 * @author Shivraj
 * This Exception is thrown when any input field is invalid.
 */
public class InvalidInputException extends Exception {
    /**
	 * default serialization id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * String message to be conveyed to user
	 */
	private String message;

    /**
     * @param message
     * Parameterized constructor
     */
    public InvalidInputException(String message){
        this.message=message;
    }

    /**
     * @return The error message
     */
    public String getMessage(){
        return message;
    }
}
