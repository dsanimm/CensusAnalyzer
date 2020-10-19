package com.capgemini.censusloader;

import com.capgemini.opencsvbuilder.*;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.capgemini.censusloader.CensusAnalyzerException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import com.capgemini.opencsvbuilder.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.capgemini.censusloader.CensusAnalyzerException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import com.capgemini.opencsvbuilder.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.capgemini.censusloader.CensusAnalyzerException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.capgemini.factory.*;
import com.capgemini.interfaces.ICSVBuilder;
import com.capgemini.censusloader.*;

public class StateCensusAnalyzer {
	private String SAMPLE_JSON_StateCensus = "StateJsonList.json";
	private String SAMPLE_JSON_IndiaCensus = "IndiaJsonList.json";

	/**
	 * Returns the no. of entries in the file
	 */
	public int processIndiaCensus(String filePath) throws CensusAnalyzerException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.getCSVBuilder();
			List<StateCodeCSV> list = csvBuilder.getListForCSVFile(reader, StateCodeCSV.class);
			List<StateCodeCSV> csvToList = list.stream().sorted((l1, l2) -> l1.stateName.compareTo(l2.stateName))
					.collect(Collectors.toList());
			Gson gson = new Gson();
			String jsonList = gson.toJson(csvToList);
			FileWriter filewriter = new FileWriter(SAMPLE_JSON_IndiaCensus);
			filewriter.write(jsonList);
			filewriter.close();
			return (int) getCountOfEntries(list);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyzerException("No Such File Found", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (IOException e) {
			throw new CensusAnalyzerException("IO Exception", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}

	public int processStateCensus(String csvFilePath) throws CensusAnalyzerException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.getCSVBuilder();
			List<CSVStateCensus> list = csvBuilder.getListForCSVFile(reader, StateCodeCSV.class);
			List<CSVStateCensus> csvToList = list.stream().sorted((l1, l2) -> l1.state.compareTo(l2.state))
					.collect(Collectors.toList());
			Gson gson = new Gson();
			String jsonList = gson.toJson(csvToList);
			FileWriter filewriter = new FileWriter(SAMPLE_JSON_StateCensus);
			filewriter.write(jsonList);
			filewriter.close();
			return getCountOfEntries(list);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyzerException("No Such File Found", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (IOException e) {
			throw new CensusAnalyzerException("IO Exception", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}

	private <E> int getCountOfEntries(List<E> list) {
		return (int) list.stream().count();
	}

}