package com.ilegra.curriculos.service.provider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;

public class DriveServiceProvider {

	private static final String SERVICE_ACCOUNT_EMAIL = "466415098103-43o3ukr3ukh48qe5alkjactacsui4cnt@developer.gserviceaccount.com";
	private static final String SERVICE_ACCOUNT_PKCS12_FILE_PATH = "C:\\Users\\admin\\Documents\\GitHub\\curriculos\\69ea4878d818decfe993895ae9a0e9b09c409fe9-privatekey.p12";

	// password notasecret
	public Drive getDriveService() throws GeneralSecurityException,IOException, URISyntaxException {
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		
		GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(jsonFactory)
				.setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
				.setServiceAccountScopes(Arrays.asList("https://www.googleapis.com/auth/drive.file"))
				.setServiceAccountPrivateKeyFromP12File(new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE_PATH))
				.build();
		Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName("datapub-curriculos").build();
		return service;
	}
}