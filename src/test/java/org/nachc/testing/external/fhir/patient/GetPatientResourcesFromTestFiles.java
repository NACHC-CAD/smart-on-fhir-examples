package org.nachc.testing.external.fhir.patient;

import java.io.File;
import java.util.List;

import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Patient;
import org.nachc.tools.fhirtoomop.fhir.patient.r4.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.r4.factory.FhirPatientFactory;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.file.FileUtil;
import com.nach.core.util.json.JsonUtil;

import ca.uhn.fhir.context.FhirContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetPatientResourcesFromTestFiles {

	private static final String ROOT = "src/test/resources/fhir/patients";

	public static void main(String[] args) {
		// delete existing output directories
		log.info("Cleaning up old dirs...");
		log.info("Removing patient...");
		FileUtil.rmdir(new File(ROOT, "patient"));
		// create the patient dir
		File root = FileUtil.getFromProjectRoot(ROOT);
		File patientRoot = new File(root, "patient");
		FileUtil.mkdirs(patientRoot);
		log.info("PATIENT ROOT: " + FileUtil.getCanonicalPath(patientRoot));
		// set the fhir context to R4
		log.info("Setting context...");
		FhirJsonParser.setFhirContext(FhirContext.forR4());
		// get the patients
		log.info("Getting patient info for test patients...");
		File patientEverythingDir = new File(root, "_everything");
		File[] fileList = patientEverythingDir.listFiles();
		// for each patient
		for (File file : fileList) {
			FhirPatient patient = FhirPatientFactory.buildFromSingleFile(file);
			File patientDir = FileUtil.mkdirs(patientRoot, file.getName());
			log.info("Got patient: " + file.getName());
			writePatient(patient, patientDir);
		}
		log.info("Done.");
	}

	private static void writePatient(FhirPatient patient, File dir) {
		Patient fhirPatient = patient.getPatient().getPatient();
		String json = FhirJsonParser.serialize(fhirPatient);
		json = JsonUtil.prettyPrint(json);
		File patientFile = new File(dir, dir.getName());
		FileUtil.write(json, patientFile);
		writeDiagnosticReports(patient, dir);
		log.info("File written to: " + FileUtil.getCanonicalPath(patientFile));
	}

	private static void writeDiagnosticReports(FhirPatient patient, File patientDir) {
		File dir = FileUtil.mkdirs(patientDir, "DiagnosticReport");
		List<DiagnosticReport> list = patient.getResourceListForType(DiagnosticReport.class);
		int cnt = 0;
		for(DiagnosticReport report : list) {
			cnt++;
			String json = FhirJsonParser.serialize(report);
			json = JsonUtil.prettyPrint(json);
			String fileName = "report-";
			if(cnt < 10) {
				fileName += "0";
			}
			if(cnt < 100) {
				fileName += "0";
			}
			fileName +=  cnt + ".json";
			File file = new File(dir, fileName);
			FileUtil.write(json, file);
		}
		log.info("Got " + list.size() + " Diagnostic Reports.");
	}

}
