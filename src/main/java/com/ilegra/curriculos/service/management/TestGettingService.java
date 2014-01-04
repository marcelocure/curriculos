package com.ilegra.curriculos.service.management;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.ilegra.curriculos.service.provider.DriveServiceProvider;

public class TestGettingService {
	public static void main(String[] args) {
		DriveServiceProvider driveServiceProvider = new DriveServiceProvider();
		try {
			Drive service = driveServiceProvider.getDriveService();
			System.out.println(service);
			List<File> result = new ArrayList<File>();
		    Files.List request = service.files().list();

//		    do {
//		      try {
//		        FileList files = request.execute();
//
//		        result.addAll(files.getItems());
//		        request.setPageToken(files.getNextPageToken());
//		      } catch (IOException e) {
//		        System.out.println("An error occurred: " + e);
//		        request.setPageToken(null);
//		      }
//		    } while (request.getPageToken() != null &&
//		             request.getPageToken().length() > 0);
//
//		    System.out.println(result);

		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}

