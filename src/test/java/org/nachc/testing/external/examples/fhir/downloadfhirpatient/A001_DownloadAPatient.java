package org.nachc.testing.external.examples.fhir.downloadfhirpatient;

import org.junit.Test;
import org.nachc.smartonfhirexamples.properties.AppProperties;
import org.nachc.tools.fhirtoomop.tools.download.patient.fetcher.FhirPatientEverythingFetcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A001_DownloadAPatient {

	@Test
	public void shouldDownloadPatient() {
		log.info("Starting test...");
		String patientId = AppProperties.get("patientId");
		log.info("Getting patient for ID: " + patientId);
		FhirPatientEverythingFetcher fetcher = new FhirPatientEverythingFetcher();
		String json = fetcher.fetchEverything(patientId);
		log.info("Got Patient resource for: " + patientId + "\n" + json);
		log.info("Done.");
	}
	
}
