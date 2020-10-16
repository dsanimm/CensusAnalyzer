package com.capgemini.censusloader;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateCensusAnalyzerTest {

	@Test
	public void testProcessIndiaCensusDataCensus() {
		String filePath = "IndianStateCensusData.csv";
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		try {
			assertEquals(36, stateCensusAnalyzer.processIndiaCensus(filePath));
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
			stateCensusAnalyzer.processIndiaCensus(filePath);
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
			stateCensusAnalyzer.processIndiaCensus(filePath);
		} catch (CensusAnalyzerException e) {
			assertEquals(CensusAnalyzerException.ExceptionType.FILE_PROBLEM, e.type);
		}

	}

	@Test
	public void givenWrongDelimiterTypeWhenProcessedShouldThrowAnException() {
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		String filePath = "IndianStateCensusDataWrongDelimiter.csv";
		try {
			stateCensusAnalyzer.processIndiaCensus(filePath);
		} catch (CensusAnalyzerException e) {
			assertEquals(CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION, e.type);

		}

	}

	@Test
	public void givenIndianCensusCSVFile_WhenWrongHeaderProvided_ShouldThrowException() {
		try {
			StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
			stateCensusAnalyzer.processIndiaCensus("IndianStateCensusDataWrongHeader.csv");
		} catch (CensusAnalyzerException e) {
			System.out.println(e.getMessage());
			assertEquals("Error capturing CSV header!", e.getMessage());
		}
	}
	@Test
	public void testProcessStateCensus() {
		String filePath = "StateCSV.csv";
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		try {
			assertEquals(36, stateCensusAnalyzer.processStateCensus(filePath));
		} catch (CensusAnalyzerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void givenStateCensus_WithWrongFile_ShouldThrowException() {
		String filePath = "StateCSVA.csv";
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		try {
			stateCensusAnalyzer.processStateCensus(filePath);
		} catch (CensusAnalyzerException e) {
			// TODO Auto-generated catch block
			assertEquals(CensusAnalyzerException.ExceptionType.FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenWrongFileTypeWhenProcessedShouldThrowAnException_givenStateCensus() {
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		String filePath = "StateCSV.pdf";
		try {
			stateCensusAnalyzer.processStateCensus(filePath);
		} catch (CensusAnalyzerException e) {
			assertEquals(CensusAnalyzerException.ExceptionType.FILE_PROBLEM, e.type);
		}

	}

	@Test
	public void givenWrongDelimiterTypeWhenProcessedShouldThrowAnException_givenStateCensus() {
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		String filePath = "StateCSVDataWrongDelimiter.csv";
		try {
			stateCensusAnalyzer.processStateCensus(filePath);
		} catch (CensusAnalyzerException e) {
			assertEquals(CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION, e.type);

		}

	}

	@Test
	public void givenStateCensusCSVFile_WhenWrongHeaderProvided_ShouldThrowException() {
		try {
			StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
			stateCensusAnalyzer.processStateCensus("StateCSVDataWrongHeader.csv");
		} catch (CensusAnalyzerException e) {
			System.out.println(e.getMessage());
			assertEquals("Error capturing CSV header!", e.getMessage());
		}
	}

}
