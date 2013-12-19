package com.ilegra.curriculos.index;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {
	private int maxResults = 10;

	public void search(String queryText) throws IOException, ParseException {
		 IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("C:/luceneindexes")));
		 IndexSearcher searcher = new IndexSearcher(reader);
		 Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_45);
		 QueryParser parser = new QueryParser(Version.LUCENE_45, "content", analyzer);
		 Query query = parser.parse(queryText);
		 TopDocs results = searcher.search(query, 10);
		 ScoreDoc[] hits = results.scoreDocs;
			
			for(ScoreDoc hit : hits) {
				Document doc = searcher.doc(hit.doc);
				System.out.println(doc.get("url"));
				System.out.println(doc.get("content"));
			}


//		IndexSearcher searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File("C:/luceneindexes"))));
//		QueryParser parser = new QueryParser(Version.LUCENE_45, "content",
//				new StandardAnalyzer(Version.LUCENE_45));
//		Query query = parser.parse("ora");
//		TopScoreDocCollector collector = TopScoreDocCollector.create(256, true);
//		searcher.search(query, collector);
//		ScoreDoc[] hits = collector.topDocs().scoreDocs;
//		for (int i2 = 0, n2 = hits.length; i2 < n2; i2++) {
//			Document doc = searcher.doc(hits[i2].doc);
//			System.out.println(doc.get("url"));
//		}
	}
}