package com.capgemini.censusloader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {
	/**
	 * Returns the no. of entries in the file
	 */
	public int processStateCensus(String filePath) {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			CsvToBean<CSVStateCensus> csvToBean =  new CsvToBeanBuilder<CSVStateCensus>(reader)
					                                  .withType(CSVStateCensus.class).build();
			Iterator<CSVStateCensus> iterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvIterable = () -> iterator;
			return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}