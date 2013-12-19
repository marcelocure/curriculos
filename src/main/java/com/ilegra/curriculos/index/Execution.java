package com.ilegra.curriculos.index;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Execution {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		Indexer indexer = new Indexer();
		
		indexer.index();

	}

}
