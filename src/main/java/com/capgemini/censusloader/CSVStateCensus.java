package com.capgemini.censusloader;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {

	@CsvBindByName(column = "State Name", required = true) 
	String state;
	@CsvBindByName(column = "State Code", required = true)
	String code;
	@CsvBindByName(column = "Population", required = true)
	int population;
	@CsvBindByName(column = "TIN", required = true)
	int tinNumber;

	@Override
	public String toString() {
		return "CSVStateCensus [state=" + state + ", code=" + code + ", population=" + population + ", tinNumber="
				+ tinNumber + "]";
	}
}