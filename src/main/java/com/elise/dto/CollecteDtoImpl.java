package com.elise.dto;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.elise.model.Collecte;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

@Repository
public class CollecteDtoImpl implements CollecteDto {

	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/2/sheets.googleapis.com-java-quickstart.json");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY,
			SheetsScopes.SPREADSHEETS, SheetsScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = CollecteDtoImpl.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline")
						.setApprovalPrompt("force").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());

		return credential;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws IOException {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	/**
	 * récuperer la reponse
	 * 
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static ValueRange getResponse() throws IOException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		String spreadsheetId = "1VAaYxZ037sh-O1vYATG-n9WPKg6HvHZS6qnzhDwqHhE";
		String range = "1:26";
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		return response;
	}

	@Override
	public boolean addCollect(Collecte p) {
		ValueRange body = new ValueRange().setValues(
				Arrays.asList(Arrays.asList("id", "Nom Societe", "Type Collecte", "date demande", "date collecte")));

		ValueRange appendBody = new ValueRange().setValues(Arrays.asList(
				Arrays.asList(p.getId(), p.getName(), p.getTypeCollecte(), p.getDateDemande(), p.getDateCollecte())));
		try {
			getSheetsService().spreadsheets().values()
					.update("1VAaYxZ037sh-O1vYATG-n9WPKg6HvHZS6qnzhDwqHhE", "A1", body).setValueInputOption("RAW")
					.execute();

			getSheetsService().spreadsheets().values()
					.append("1VAaYxZ037sh-O1vYATG-n9WPKg6HvHZS6qnzhDwqHhE", "A1", appendBody)
					.setValueInputOption("USER_ENTERED").setInsertDataOption("INSERT_ROWS")
					.setIncludeValuesInResponse(true).execute();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public void validateCollect(int id) {
		int i = id + 1;
		String range1 = "A" + i; // TODO: Update placeholder value.
		String range2 = "E" + i;
		List<String> ranges = Arrays.asList(range1, range2);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// date de collecte du jour

		Date today = Calendar.getInstance().getTime();
		String dateTodayToCollect = df.format(today);
		ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList(dateTodayToCollect)));

		try {

			getSheetsService().spreadsheets().values()
					.update("1VAaYxZ037sh-O1vYATG-n9WPKg6HvHZS6qnzhDwqHhE", range2, body).setValueInputOption("RAW")
					.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Collecte> listCollect() {
		ValueRange response = null;
		List<Object> objectCollecte = new ArrayList<>();
		List<Collecte> listCollecteFinal = new ArrayList<>();

		try {
			response = getResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<List<Object>> values = response.getValues();
		List<List<Object>> listt = new ArrayList<>();
		for (int i = 1; i < values.size(); i++) {
			listt.add(values.get(i));
		}

		ListIterator<List<Object>> it = listt.listIterator();

		while (it.hasNext()) {
			objectCollecte = it.next();
			listCollecteFinal.add(this.findListCollecteForSheetsObject(objectCollecte));
		}

		return listCollecteFinal;
	}

	public Collecte findListCollecteForSheetsObject(List<Object> listResult) {

		List<String> listResultStrings = listResult.stream().map(object -> Objects.toString(object, null))
				.collect(Collectors.toList());

		Collecte colecte = new Collecte();
		colecte.setId(Integer.valueOf(listResultStrings.get(0)));
		colecte.setName(listResultStrings.get(1));
		colecte.setTypeCollecte(listResultStrings.get(2));
		colecte.setDateDemande(listResultStrings.get(3));
		if (listResultStrings.size() > 4) {
			colecte.setDateCollecte(listResultStrings.get(4));
		}

		return colecte;
	}

	@Override
	public Collecte getCollecteId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCollecte(int id) {
		// TODO Auto-generated method stub

	}

}
