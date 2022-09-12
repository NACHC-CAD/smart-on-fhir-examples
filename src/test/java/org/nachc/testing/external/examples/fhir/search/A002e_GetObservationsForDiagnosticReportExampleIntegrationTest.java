package org.nachc.testing.external.examples.fhir.search;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Patient;
// import org.hl7.fhir.r4.model.Bundle;
// import org.hl7.fhir.r4.model.DiagnosticReport;
import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A002e_GetObservationsForDiagnosticReportExampleIntegrationTest {

	/**
	 * From the previous example we have an id for a DiangosticReport:
	 * ca42703d-054c-4fc5-aaa5-c9d4f9688e89
	 * 
	 * We can now get this report using the id. This example does that.
	 * 
	 * After running this example, you will see that the DiagnosticReport does not
	 * actually contain the Observersion (values) for the tests. The
	 * DiagnosticReport contains references to the Observation resources that are
	 * (for some reason) elsewhere in the Patient resource. In the next example we
	 * will join the observations to the information in the diagnostic report. 
	 */
	@Test
	public void shouldGetResource() {
		log.info("Starting test...");
		// query the server and get the json response string
		String reportId = "ca42703d-054c-4fc5-aaa5-c9d4f9688e89";
		String path = "/DiagnosticReport/" + reportId;
		String response = FhirQuerySender.get(path);
		String msg = "";
		msg += "\n\nDIAGNOSTIC REPORT";
		msg += "\nResponse from server: \n\n" + response + "\n";
		log.info(msg);
		// crete a fhir object from the response json
		DiagnosticReport report = FhirJsonParser.parse(response, DiagnosticReport.class);
		String idFromResponse = report.getId();
		log.info(reportId);
		log.info(idFromResponse);
		assertTrue(idFromResponse.contains(reportId));
		log.info("Done.");
	}

}
