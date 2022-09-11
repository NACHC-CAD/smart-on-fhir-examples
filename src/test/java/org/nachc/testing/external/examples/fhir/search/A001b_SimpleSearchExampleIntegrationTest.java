package org.nachc.testing.external.examples.fhir.search;

import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A001b_SimpleSearchExampleIntegrationTest {
	
	/**
	 * This search uses the FHIR search API to find a patient for a given ID.  
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		String path = "/Patient?_id=87a339d0-8cae-418e-89c7-8651e6aab3c6";
		String response = FhirQuerySender.get(path);
		String msg = "Response from server: \n\n" + response + "\n";
		log.info(msg);
		log.info("Done.");
	}

}
