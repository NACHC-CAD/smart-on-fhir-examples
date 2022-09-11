package org.nachc.testing.external.examples.fhir.search;

import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A002a_HasDiagnoticReportSearchExampleIntegrationTest {
	
	/**
	 * This example uses the FHIR search API to find patients that have a DiagnosticReport resource. 
	 * This example finds those patients by asking for patients that have a DiagnosticReport.  
	 * This query can also be made by getting all of the DiagnosticReport resources and then parsing out the Patients from the responses 
	 * (this approach is taken in the next example).  
	 * The method used here seems to be more straight forward and the request seems to more closely match the semantics of the question.  
	 */	
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		String path = "/Patient?_type=DiagnosticReport&_summary=true";
		String response = FhirQuerySender.get(path);
		String msg = "Response from server: \n\n" + response + "\n";
		log.info(msg);
		log.info("Done.");
	}

}
