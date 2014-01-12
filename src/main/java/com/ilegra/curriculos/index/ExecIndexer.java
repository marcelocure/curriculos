package com.ilegra.curriculos.index;

public class ExecIndexer {
	public static void main(String[] args) {
		Indexer indexer = new Indexer();
		try {
			indexer.index();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
