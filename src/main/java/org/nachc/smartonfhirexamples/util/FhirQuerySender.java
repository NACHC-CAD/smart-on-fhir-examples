package org.nachc.smartonfhirexamples.util;

import org.nachc.smartonfhirexamples.properties.AppProperties;
import org.nachc.tools.fhirtoomop.tools.download.authenticate.FhirServerAuthenticator;

import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirQuerySender {

	public static String get(String path) {
		String baseUrl = AppProperties.getFhirServerUrl();
		String url = baseUrl + path;
		log.info("FHIR Server: " + url);
		log.info("URL: \n" + url);
		HttpRequestClient client = new HttpRequestClient(url);
		FhirServerAuthenticator.auth(client);
		client.doGet();
		String response = client.getResponse();
		return response;
	}

	public static String getForUrl(String url) {
		HttpRequestClient client = new HttpRequestClient(url);
		FhirServerAuthenticator.auth(client);
		client.doGet();
		String response = client.getResponse();
		return response;
	}
	
}
