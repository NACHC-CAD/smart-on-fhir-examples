package org.nachc.testing.external.examples.fhir.search;

import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A002b_HasDiagnoticReportSearchExampleIntegrationTest {
	
	/**
	 * This example uses the FHIR search API to find patients that have a DiagnosticReport resource. 
	 * This example finds those patients by asking for all of the DiagnosticReport resources and then getting the Patient resource from there.  
	 * This question could also be asked by asking for patients that have a DiagnosticReport 
	 * (this method is used in the previous example).  
	 * The method presented here is less efficient as each patient can have multiple DiagnosticReport resources.  
	 */	
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		String path = "/DiagnosticReport?_summary=true";
		String response = FhirQuerySender.get(path);
		String msg = "Response from server: \n\n" + response + "\n";
		log.info(msg);
		log.info("Done.");
	}

}
