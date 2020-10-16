package com.capgemini.censusloader;

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

public class StateCensusAnalyzer {
	/**
	 * Returns the no. of entries in the file
	 */
	public int processIndiaCensus(String filePath) throws CensusAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
					.withType(CSVStateCensus.class).build();
			Iterator<CSVStateCensus> iterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvIterable = () -> iterator;
			return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
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
			CsvToBeanBuilder<StateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(StateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withSeparator(',');
			CsvToBean<StateCodeCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCodeCSV> censusCSVIterator = csvToBean.iterator();
			Iterable<StateCodeCSV> csvIterable = () -> censusCSVIterator;
			int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEateries;
		} catch (NoSuchFileException e) {
			throw new CensusAnalyzerException("No Such File Found", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (IOException e) {
			throw new CensusAnalyzerException("IO Exception", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}
}