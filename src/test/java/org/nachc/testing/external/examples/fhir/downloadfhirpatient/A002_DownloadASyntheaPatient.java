package org.nachc.testing.external.examples.fhir.downloadfhirpatient;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.tools.download.patient.fetcher.FhirPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.tools.download.patient.writer.WriteFhirPatientToFile;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A002_DownloadASyntheaPatient {

	private static final String PATIENT_ID = "0823143f-91dc-4dd3-b4bb-f4d9165773ea";
	
	@Test
	public void shouldDownloadPatient() {
		log.info("Starting test...");
		FhirPatientEverythingFetcher fetcher = new FhirPatientEverythingFetcher();
		String json = fetcher.fetchEverything(PATIENT_ID);
		log.info("Got Patient resource for: " + PATIENT_ID + "\n" + json);
		log.info("Done.");
	}
	
}
