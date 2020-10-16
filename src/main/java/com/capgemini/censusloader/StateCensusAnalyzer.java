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
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			Iterator<StateCodeCSV> csvIterable = getIteratorForCSVFile(reader,StateCodeCSV.class);
			return (int) StreamSupport.stream(((Iterable) csvIterable).spliterator(), false).count();
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
			Iterator<StateCodeCSV> csvIterable = getIteratorForCSVFile(reader,StateCodeCSV.class);
			return (int) StreamSupport.stream(((Iterable) csvIterable).spliterator(), false).count();
		} catch (NoSuchFileException e) {
			throw new CensusAnalyzerException("No Such File Found", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (IOException e) {
			throw new CensusAnalyzerException("IO Exception", CensusAnalyzerException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}
	private <E> Iterator<E> getIteratorForCSVFile(Reader reader, Class<E> csvClass) throws CensusAnalyzerException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withSeparator(',');
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.PARSE_EXCEPTION);
		}

	}
}