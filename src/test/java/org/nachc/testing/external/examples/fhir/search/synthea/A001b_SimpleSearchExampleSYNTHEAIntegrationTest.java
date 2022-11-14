package org.nachc.testing.external.examples.fhir.search.synthea;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A001b_SimpleSearchExampleSYNTHEAIntegrationTest {

	/**
	 * This search uses the FHIR search API to find a patient for a given ID.
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		// query the server and get the json response string
		String requestedPatient = "0823143f-91dc-4dd3-b4bb-f4d9165773ea";
		String path = "/Patient?_id=" + requestedPatient;
		String response = FhirQuerySender.get(path);
		String msg = "Response from server: \n\n" + response + "\n";
		log.info(msg);
		// crete a bundle object from the response json
		Bundle bundle = FhirJsonParser.parse(response, Bundle.class);
		BundleParser parser = new BundleParser(bundle);
		List<Patient> patientList = parser.getResourceListForType(Patient.class);
		log.info("Got " + patientList.size() + " patient.");
		if(patientList.size() > 0) {
			Patient patient = patientList.get(0);
			PatientParser patientParser = new PatientParser(patient);
			String patientId = patientParser.getId();
			log.info("Requested: " + requestedPatient);
			log.info("Response:  " + patientId);
			// assert that the response has the same id as the request
			assertTrue(requestedPatient.equals(patientId));
		}
		log.info("Done.");
	}

}
