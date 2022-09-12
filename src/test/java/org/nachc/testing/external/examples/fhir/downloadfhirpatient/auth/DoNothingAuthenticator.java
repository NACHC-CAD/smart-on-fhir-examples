package org.nachc.testing.external.examples.fhir.downloadfhirpatient.auth;

import org.nachc.tools.fhirtoomop.fhir.util.server.auth.HttpClientAuthenticator;

import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DoNothingAuthenticator implements HttpClientAuthenticator {

	@Override
	public void init() {
		log.info("Done with init()");
	}

	@Override
	public void refresh() {
		log.info("Done with refresh()");
	}

	@Override
	public void addAuth(HttpRequestClient client) {
		log.info("Done with addAuth()");
	}

}
