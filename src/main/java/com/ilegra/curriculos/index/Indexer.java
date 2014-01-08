package com.ilegra.curriculos.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;

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

import com.ilegra.curriculos.domain.LuceneDocument;
import com.ilegra.curriculos.service.provider.DocumentsProvider;

public class Indexer {
	
	private IndexWriter indexWriter;
	
	public void index() throws InvalidFormatException, IOException, GeneralSecurityException, URISyntaxException {
		createIndex();
		System.out.println("index created");
		DocumentsProvider documentsProvider = new DocumentsProvider();
		
		List<LuceneDocument> documents = documentsProvider.provideDocuments();
		for(LuceneDocument luceneDocument : documents) {
			System.out.println(luceneDocument.getUrl());
			System.out.println(luceneDocument.getContent().length());
		}
		
		for(LuceneDocument luceneDocument : documents) {
			Document doc = new Document();
			doc.add(new TextField("content", luceneDocument.getContent(), Store.YES));
			doc.add(new TextField("url", luceneDocument.getUrl(), Store.YES));
			System.out.println("document populated");
			indexWriter.addDocument(doc);
			System.out.println("document added to the index");
		}

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