package com.ticketresolution.controller;

public class InvalidInputException extends Exception {
	String inputMessage;

	InvalidInputException(String message) {
		inputMessage = message;
	}
	
	public String getMessage() {
		return inputMessage;
	}
	
	public String toString() {
		return ("Invalid input type : " + inputMessage);
	}
}
