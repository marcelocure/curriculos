package com.ilegra.curriculos.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Indexer {
	
	private IndexWriter indexWriter;
	
	public void index() throws InvalidFormatException, IOException {
		createIndex();
		System.out.println("index created");
		
		InputStream fs = new FileInputStream("C:\\Users\\admin\\Documents\\Marcelo Cure_english.docx");
		XWPFWordExtractor extractor = new XWPFWordExtractor(new XWPFDocument(OPCPackage.open(fs)));
		System.out.println("file read");
		
		Document doc = new Document();
		doc.add(new TextField("content", extractor.getText(), Store.YES));
		doc.add(new TextField("url", "C:\\Users\\admin\\Documents\\Marcelo Cure_english.docx", Store.YES));
		System.out.println("document populated");
		indexWriter.addDocument(doc);
		System.out.println("document added to the index");

		indexWriter.close();
		System.out.println("index closed");
	}

	private void createIndex() throws IOException {
		indexWriter = new IndexWriter(FSDirectory.open(getPath()),getIndexWriterConfig());
	}
	
	private IndexWriterConfig getIndexWriterConfig() {
		return new IndexWriterConfig(Version.LUCENE_45, new StandardAnalyzer(Version.LUCENE_45));
	}

	private File getPath() {
		return new File("c:/luceneindexes");
	}
}