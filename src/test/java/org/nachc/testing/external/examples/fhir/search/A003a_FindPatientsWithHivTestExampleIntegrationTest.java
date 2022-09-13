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
public class A003a_FindPatientsWithHivTestExampleIntegrationTest {

	/**
	 * This search uses the FHIR search API to find a patient for a given ID.
	 * 
	 * The hiv-cds project has a set of files that define ValueSet resources
	 * associated with CDS HIV testing guidelines.
	 * 
	 * Some of these resources have been copied to this project and are in the
	 * <project-root>/docs/hiv-cds folder.
	 * 
	 * This includes the ValueSets in the valueset folder in that directory.
	 * 
	 * The value sets are somewhat cryptically named. The
	 * ValueSetUtilGetValueSetFilesIntegrationTest integration test in the NACHC
	 * fhir-utils project was used to generate a concordance for the file names and
	 * the ValueSet each file represents.
	 * 
	 * The full path to this integration test is:
	 * org.nachc.fhirutils.valueset.ValueSetUtilGetValueSetFilesIntegrationTest).
	 * 
	 * This concordance is included in this project in the
	 * <project-root>/docs/hiv-cds as hiv-cds-value-sets.xlsx.
	 * 
	 * From this file, it look like there are four ValueSets that could potential
	 * point to HIV tests:
	 * 
	 * HIV1HIV2AbAgtestsCodes: valueset-nachc-a2-de2.json
	 * 
	 * HIVTestCodesGrouper: valueset-nachc-a2-de1-codes-grouper.json
	 * 
	 * HIVTestOrderedCodes: valueset-nachc-a2-de217.json
	 * 
	 * HIVViralLoadCodes: valueset-nachc-a2-de173.json
	 * 
	 * The files for these ValueSet resources can be found in this project at
	 * <project-root>/docs/_ETC/hiv-cds/vocabulary/valueset/generated.
	 * 
	 * If we look for results for an arbitrarily selected non-deprecated entry in
	 * each file we get:
	 * 
	 * https://r4.smarthealthit.org/Observation?code=44607-0
	 * https://r4.smarthealthit.org/Observation?code=2898779
	 * https://r4.smarthealthit.org/Observation?code=31676001
	 * https://r4.smarthealthit.org/Observation?code=2899083
	 * 
	 * None of these codes return any Observation resources.
	 * 
	 * This is also the case for DiagnosticReport:
	 * 
	 * https://r4.smarthealthit.org/DiagnosticReport?code=44607-0
	 * https://r4.smarthealthit.org/DiagnosticReport?code=2898779
	 * https://r4.smarthealthit.org/DiagnosticReport?code=31676001
	 * https://r4.smarthealthit.org/DiagnosticReport?code=2899083
	 * 
	 * In the next example, we will use a list to query for any Observation that
	 * contains any of the codes contained in each of the four ValueSet resources
	 * mentioned above.
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		// query the server and get the json response string
		String requestedPatient = "2899083";
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
