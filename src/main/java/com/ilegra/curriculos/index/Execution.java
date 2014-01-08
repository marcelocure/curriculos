package com.ilegra.curriculos.index;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Execution {

	public static void main(String[] args) throws InvalidFormatException, IOException, GeneralSecurityException, URISyntaxException {
		Indexer indexer = new Indexer();
		indexer.index();
	}
}
