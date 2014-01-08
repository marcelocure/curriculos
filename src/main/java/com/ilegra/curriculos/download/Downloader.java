package com.ilegra.curriculos.download;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.io.InputStream;

public class Downloader {
	public InputStream downloadFile(Drive service, File file) {
		if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
			try {
				HttpResponse resp = service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl())).execute();
				return resp.getContent();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

}
