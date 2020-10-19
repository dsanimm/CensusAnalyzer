package com.capgemini.censusloader;
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

public class StateCensusAnalyzer {
	/**
	 * Returns the no. of entries in the file
	 */
	public int processIndiaCensus(String filePath) throws CensusAnalyzerException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			ICSVBuilder csvBuilder=CSVBuilderFactory.getCSVBuilder();
			Iterator<StateCodeCSV> iterator = csvBuilder.getIteratorForCSVFile(reader, StateCodeCSV.class);
			return (int) getCountOfEntries(iterator);
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
			ICSVBuilder csvBuilder= CSVBuilderFactory.getCSVBuilder();
			Iterator<StateCodeCSV> iterator = csvBuilder.getIteratorForCSVFile(reader, StateCodeCSV.class);
			return getCountOfEntries(iterator);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyzerException("No Such File Found", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (IOException e) {
			throw new CensusAnalyzerException("IO Exception", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}

	

	private <E> int getCountOfEntries(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
	}

}