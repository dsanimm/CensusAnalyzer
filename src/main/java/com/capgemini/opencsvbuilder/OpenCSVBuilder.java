package com.capgemini.opencsvbuilder;

import java.io.Reader;
import java.util.Iterator;

import com.capgemini.censusloader.CensusAnalyzerException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder {
	public <E> Iterator<E> getIteratorForCSVFile(Reader reader, Class<E> csvClass) throws CensusAnalyzerException {
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
