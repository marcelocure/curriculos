package com.ilegra.curriculos.service.provider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;

public class DriveServiceProvider {

	public Drive getDriveService() throws GeneralSecurityException,IOException, URISyntaxException {
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		GoogleCredentialProvider googleCredentialProvider = new GoogleCredentialProvider(httpTransport, jsonFactory);
		return new Drive.Builder(httpTransport, jsonFactory, googleCredentialProvider.getCredential()).build();
	}
}