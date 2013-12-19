package com.ilegra.curriculos.index;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

public class ExecSearcher {
	public static void main(String[] args) throws IOException, ParseException {
		Searcher searcher = new Searcher();
		searcher.search("Oracle");
	}
}