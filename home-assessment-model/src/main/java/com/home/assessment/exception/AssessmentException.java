package com.home.assessment.exception;

@SuppressWarnings("serial")
public class AssessmentException extends RuntimeException {

	public AssessmentException(String message) {
		super(message);
	}

	public AssessmentException(String message, Throwable t) {
		super(message, t);
	}

}