package org.nachc.testing.external.examples.fhir.search;

import static org.junit.Assert.assertTrue;

import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;
import org.nachc.tools.fhirtoomop.fhir.parser.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A001a_NotASearchExampleIntegrationTest {
	
	/**
	 * This example shows how to get a patient from a FHIR server.  
	 * Note that this query is technically not a FHIR "search" but a request for a resource.  
	 * The response from this request is a Patient FHIR resource.  
	 * This is unlike a search result.  
	 * A search result returns a Bundle that includes a Patient FHIR resource as part of the content of that Bundle. 
	 * The next example demonstrates how to use the FHIR search API to get a patient.  
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		// query the server and get the json response string
		String requestedPatient = "87a339d0-8cae-418e-89c7-8651e6aab3c6";
		String path = "/Patient/" + requestedPatient;
		String response = FhirQuerySender.get(path);
		String msg = "Response from server: \n\n" + response + "\n";
		log.info(msg);
		// create a FHIR object from the response
		Patient patient = FhirJsonParser.parse(response, Patient.class);
		PatientParser parser = new PatientParser(patient);
		String patientId = parser.getId();
		log.info("Requested: " + requestedPatient);
		log.info("Response:  " + patientId);
		// assert that the response has the same id as the request
		assertTrue(requestedPatient.equals(patientId));
		log.info("Done.");
	}

}
