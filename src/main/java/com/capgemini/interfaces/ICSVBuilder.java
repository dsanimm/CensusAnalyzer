package com.capgemini.interfaces;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.capgemini.censusloader.CensusAnalyzerException;

public interface ICSVBuilder<E>{
	public List<E> getListForCSVFile(Reader reader, Class<E> csvClass) throws CensusAnalyzerException;
}