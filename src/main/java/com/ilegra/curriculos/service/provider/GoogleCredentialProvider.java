package com.ilegra.curriculos.service.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.drive.DriveScopes;

public class GoogleCredentialProvider {
	private String CLIENT_ID = "466415098103-ji1aed7gr7mlmtbabo1lpvpha2o70ooe.apps.googleusercontent.com";
	private String CLIENT_SECRET = "CyYgpWkMdF6UrS801QDrx6eM";
	private String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	private HttpTransport httpTransport;
	private JsonFactory jsonFactory;
	
	public GoogleCredentialProvider (HttpTransport httpTransport, JsonFactory jsonFactory) {
		this.httpTransport = httpTransport;
		this.jsonFactory = jsonFactory;
	}

	public GoogleCredential getCredential() throws IOException {
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				this.httpTransport, this.jsonFactory, CLIENT_ID, CLIENT_SECRET,
				Arrays.asList(DriveScopes.DRIVE)).setAccessType("online")
				.setApprovalPrompt("auto").build();
	
		String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		System.out.println("Please open the following URL in your browser then type the authorization code:");
		System.out.println("  " + url);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
	
		GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
		return new GoogleCredential().setFromTokenResponse(response);
	}
}
