package com.ilegra.curriculos.service.management;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.ilegra.curriculos.converter.FileConverter;
import com.ilegra.curriculos.domain.LuceneDocument;
import com.ilegra.curriculos.download.Downloader;
import com.ilegra.curriculos.service.provider.DriveServiceProvider;

public class DocumentsProvider {
	public List<LuceneDocument> provideDocuments() throws IOException, GeneralSecurityException, URISyntaxException, InvalidFormatException {
		DriveServiceProvider driveServiceProvider = new DriveServiceProvider();
		Drive service = driveServiceProvider.getDriveService();
		FileList response = service.files().list().setQ("mimeType = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' and not title contains '~$ojure'").execute();
	    List<LuceneDocument> documents = getLuceneDocuments(service, response);
	    return documents;
	}

	private List<LuceneDocument> getLuceneDocuments(Drive service, FileList response) {
		List<LuceneDocument> documents = new ArrayList<LuceneDocument>();
		for (File f : response.getItems())
			documents.add(processDocument(service, f));
		return documents;
	}

	private LuceneDocument processDocument(Drive service, File f) {
		Downloader downloader = new Downloader();
		FileConverter fileConverter = new FileConverter();
		String content = fileConverter.convert(downloader.downloadFile(service, f));
		return new LuceneDocument(f.getAlternateLink(),content);
	}
}