package org.nachc.testing.external.examples.fhir.search;

import java.io.File;
import java.util.List;

import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.fhir.patient.r4.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.r4.factory.FhirPatientFactory;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A002e_GetObservationsForDiagnosticReportExampleIntegrationTest {

	private static final String PATIENT_DIR = "/src/test/java/org/nachc/testing/external/examples/fhir/downloadfhirpatient/patient/d78af07c-9cb9-4f31-9b11-c45a28f2eec8";

	/**
	 * In this example we will get a DiagnosticReport and get the Observations
	 * associated with that DiagnosticReport.
	 * 
	 * We use a patient we have previously downloaded. This patient is different
	 * that the one used in previous examples (the previous patient gave timeout
	 * errors when we tried to download it).
	 * 
	 * To see how the files were downloaded, look at the A001_DownloadAPatient.java
	 * file.
	 * 
	 * Running this example, you will see that this patient has an Observation for
	 * Total Cholesterol. This has a LIOINC code of 2093-3.
	 * 
	 * In the next example we will use this LOINC code to search for all patients
	 * that have an Observation with a code of 2093-3.  
	 */
	@Test
	public void shouldMatchObservations() {
		log.info("Starting test...");
		// get the test patient
		File dir = FileUtil.getFromProjectRoot(PATIENT_DIR);
		FhirPatient patient = FhirPatientFactory.build(dir);
		List<DiagnosticReport> reportList = patient.getResourceListForType(DiagnosticReport.class);
		log.info("Got patient: " + patient.getPatientId());
		log.info("Got " + reportList.size() + " DiagnosticReport resoures");
		for (DiagnosticReport report : reportList) {
			showReport(report, patient);
		}
		log.info("Done.");
	}

	private void showReport(DiagnosticReport report, FhirPatient patient) {
		// TODO: MOVE JOINING VALUE TO REPORT TO PARSER
		List<Reference> results = report.getResult();
		String msg = "\n\n";
		msg += "------------------------------------------\n";
		msg += "Diagnostic Report (" + report.getId() + ")\n";
		msg += "------------------------------------------\n";
		msg += "Observation ID\t\t\t\t\t\tCode\tValue\t\t\tName\t";
		msg += "\n";
		for (Reference result : results) {
			// values from result (from DiagnosticReport)
			String display = result.getDisplay();
			String obsId = result.getReference();
			// values from Observation
			Observation obs = patient.getResourceForType(Observation.class, obsId);
			ObservationParser parser = new ObservationParser(obs, patient);
			String val = parser.getValueDisplay();
			String obsCode = parser.getObservationCodeCode();
			patient.getPatient();
			msg += obsId + "\t" + obsCode + "\t" + val + "\t" + display;
			msg += "\n";
		}
		msg += "------------------------------------------\n";
		log.info("REPORT:\n" + msg);
	}

}
