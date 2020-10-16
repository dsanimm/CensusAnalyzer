package com.capgemini.censusloader;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateCensusAnalyzerTest {

	@Test
	public void testProcessStateCensus() {
		String filePath = "IndianStateCensusData.csv";
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		try {
			assertEquals(36, stateCensusAnalyzer.processStateCensus(filePath));
		} catch (CensusAnalyzerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		String filePath = "IndianStateCensusDataA.csv";
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		try {
			stateCensusAnalyzer.processStateCensus(filePath);
		} catch (CensusAnalyzerException e) {
			// TODO Auto-generated catch block
			assertEquals(CensusAnalyzerException.ExceptionType.FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenWrongFileTypeWhenProcessedShouldThrowAnException() {
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		String filePath = "IndianStateCensusData.pdf";
		try {
			stateCensusAnalyzer.processStateCensus(filePath);
		} catch (CensusAnalyzerException e) {
			assertEquals(CensusAnalyzerException.ExceptionType.FILE_PROBLEM, e.type);
		}

	}

	@Test
	public void givenWrongDelimiterTypeWhenProcessedShouldThrowAnException() {
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		String filePath = "IndianStateCensusDataWrongDelimiter.csv";
		try {
			stateCensusAnalyzer.processStateCensus(filePath);
		} catch (CensusAnalyzerException e) {
			assertEquals(CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION, e.type);

		}

	}

	@Test
	public void givenIndianCensusCSVFile_WhenWrongHeaderProvided_ShouldThrowException() {
		try {
			StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
			stateCensusAnalyzer.processStateCensus("IndianStateCensusDataWrongHeader.csv");
		} catch (CensusAnalyzerException e) {
			System.out.println(e.getMessage());
			assertEquals("Error capturing CSV header!", e.getMessage());
		}
	}

}
