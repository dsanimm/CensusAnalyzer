package com.capgemini.censusloader;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {

	@CsvBindByName(column = "State Name", required = true)
	public String stateName;

	@CsvBindByName
	public String stateCode;
}