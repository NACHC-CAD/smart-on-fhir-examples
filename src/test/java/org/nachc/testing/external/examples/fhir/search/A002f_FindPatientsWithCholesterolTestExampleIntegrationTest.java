package org.nachc.testing.external.examples.fhir.search;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A002f_FindPatientsWithCholesterolTestExampleIntegrationTest {

	/**
	 * This search uses the FHIR search API to find a patient for a given ID.
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		// query the server and get the json response string
		String requestedPatient = "2093-3";
		String path = "/Observation/?code=" + requestedPatient;
		String response = FhirQuerySender.get(path);
		String msg = "Response from server: \n\n" + response + "\n";
		log.info(msg);
		// crete a bundle object from the response json
		Bundle bundle = FhirJsonParser.parse(response, Bundle.class);
		BundleParser parser = new BundleParser(bundle);
		List<Observation> obsList = parser.getResourceListForType(Observation.class);
		log.info("Got " + obsList.size() + " observations.");
		assertTrue(obsList.size() == 50);
		log.info("Done.");
	}

}
