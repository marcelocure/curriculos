package com.ilegra.curriculos.converter;

import java.io.InputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class FileConverter {
	public String convert(InputStream fileInputStream) {
		XWPFWordExtractor extractor = getExtractor(fileInputStream);
		return extractor.getText();
	}

	private XWPFWordExtractor getExtractor(InputStream fileInputStream) {
		XWPFWordExtractor extractor = null;
		try {
			extractor = new XWPFWordExtractor(new XWPFDocument(OPCPackage.open(fileInputStream)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extractor;
	}
}
