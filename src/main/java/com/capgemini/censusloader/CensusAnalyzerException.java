package com.capgemini.censusloader;

public class CensusAnalyzerException extends Exception {
	ExceptionType type;

	enum ExceptionType {
		FILE_PROBLEM
	}

	public CensusAnalyzerException(String message, ExceptionType exceptionType) {
		super(message);
		this.type = exceptionType;
	}
}