package com.capgemini.interfaces;

import java.io.Reader;
import java.util.Iterator;

import com.capgemini.censusloader.CensusAnalyzerException;

public interface ICSVBuilder<E>{
	public Iterator<E> getIteratorForCSVFile(Reader reader, Class<E> csvClass) throws CensusAnalyzerException;
}