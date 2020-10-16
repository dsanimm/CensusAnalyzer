package com.capgemini.censusloader;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateCensusAnalyzerTest {
	
	@Test
	public void testProcessStateCensus() {
		String filePath = "IndianStateCensusData.csv";
		StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
		assertEquals(36,stateCensusAnalyzer.processStateCensus(filePath));
	}

}
