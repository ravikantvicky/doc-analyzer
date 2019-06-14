package com.infosys.stg.doc.exception;

public class DocAnalyzeException extends RuntimeException {
	private static final long serialVersionUID = -4731694319483605121L;

	public DocAnalyzeException(String message) {
		super(message);
	}

	public DocAnalyzeException(String message, Throwable cause) {
		super(message, cause);
	}
}
