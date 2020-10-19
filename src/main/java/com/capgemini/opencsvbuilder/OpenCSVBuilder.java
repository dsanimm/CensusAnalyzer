package com.capgemini.opencsvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.capgemini.censusloader.CensusAnalyzerException;
import com.capgemini.interfaces.ICSVBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder {

	@Override
	public List getListForCSVFile(Reader reader, Class csvClass) throws CensusAnalyzerException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withSeparator(',');
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			csvToBean.iterator();
			List myList = null;
			while (csvToBean.iterator().hasNext())
				myList.add(csvToBean.iterator().next());
			return myList;
		} catch (IllegalStateException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.PARSE_EXCEPTION);
		}
	}

}
