package com.capgemini.censusloader;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateCensusAnalyzerTest {
	
	@Test
	public void testProcessStateCensus() {
		String filePath = "IndianStateCensusData.csv";
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		try {
			assertEquals(36,stateCensusAnalyzer.processStateCensus(filePath));
		} catch (CensusAnalyzerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException(){
		String filePath = "IndianStateCensusDataA.csv";
		
		try {
			StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
			stateCensusAnalyzer.processStateCensus(filePath);
		} catch (CensusAnalyzerException e) {
			// TODO Auto-generated catch block
			assertEquals(CensusAnalyzerException.ExceptionType.FILE_PROBLEM, e.type);
		}
	}

}
