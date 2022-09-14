package org.nachc.testing.external.examples.fhir.search.synthea;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.fhir.parser.FhirJsonParserForDstu3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A003a_FindPatientsWithHivTestExampleIntegrationTest {

	/**
	 * This search uses the FHIR search API to find observations for a given code.
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		// query the server and get the json response string
		String code = "2899083";
		String path = "/Observation/?code=" + code;
		String response = FhirQuerySender.get(path);
		String msg = "Response from server: \n\n" + response + "\n";
		log.info(msg);
		// crete a bundle object from the response json
		Bundle bundle = FhirJsonParserForDstu3.parse(response, Bundle.class);
		BundleParser parser = new BundleParser(bundle);
		List<Observation> obsList = parser.getResourceListForType(Observation.class);
		log.info("Got " + obsList.size() + " observations.");
		assertTrue(obsList.size() == 50);
		log.info("Done.");
	}

}
