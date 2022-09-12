package org.nachc.testing.external.examples.fhir.search;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DiagnosticReport;
// import org.hl7.fhir.r4.model.Bundle;
// import org.hl7.fhir.r4.model.DiagnosticReport;
import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.bundle.BundleParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A002c_GetDiagnosticReportForPatientExampleIntegrationTest {

	/**
	 * If we look at the responses from the previous examples, we find that The
	 * initial response for the list of Patients that have a DiagnosticReport we
	 * find this user: d78af07c-9cb9-4f31-9b11-c45a28f2eec8
	 * 
	 * We can then use something like the following to query for the Patient
	 * resource for this user.
	 * https://r4.smarthealthit.org/Patient/d78af07c-9cb9-4f31-9b11-c45a28f2eec8/$everything
	 * 
	 * The response for the above query gives the first page of a paged response for
	 * the Patient/$everything resource. The response also includes a "next" link
	 * that gives us a way to get the next page of the paged response.
	 * 
	 * The following url is the next link that is provided. This link gives us a
	 * response that has a DiagnosticReport in it. A link similar to the one below
	 * is used in this example. The link below will not work and will give an error
	 * indicating "Search ID \"483b5a20-60e6-4007-8b63-3f7bbd366b18\" does not exist
	 * and may have expired".
	 * https://r4.smarthealthit.org/?_getpages=483b5a20-60e6-4007-8b63-3f7bbd366b18&_getpagesoffset=50&_count=50&_pretty=true&_bundletype=searchset
	 * 
	 * This example steps through the queries described above to get to the
	 * DiagnosticReport for this patient.
	 * 
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		// get the first page for the Patient/$everything resource
		String path = "/Patient/d78af07c-9cb9-4f31-9b11-c45a28f2eec8/$everything";
		String everythingJson = FhirQuerySender.get(path);
		Bundle everythingBundle = FhirJsonParser.parse(everythingJson, Bundle.class);
		BundleParser everythingParser = new BundleParser(everythingBundle);
		String nextUrl = everythingParser.getNextUrl();
		log.info("Next URL: \n" + nextUrl);
		// get the next page for the Patient/$everything resource
		String nextPageJson = FhirQuerySender.getForUrl(nextUrl);
		String msg = "Response from server: \n\n" + nextPageJson + "\n";
		Bundle bundle = FhirJsonParser.parse(nextPageJson, Bundle.class);
		BundleParser parser = new BundleParser(bundle);
		List<DiagnosticReport> reports = parser.getResourceListForType(DiagnosticReport.class);
		log.info("Got " + reports.size() + " reports.");
		log.info(nextPageJson);
		log.info("Done.");
	}

}
