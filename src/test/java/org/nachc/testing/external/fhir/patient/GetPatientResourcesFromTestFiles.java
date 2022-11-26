package org.nachc.testing.external.fhir.patient;

import java.io.File;

import org.hl7.fhir.dstu3.model.Patient;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;

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
		log.info("Removing patient...");
		FileUtil.rmdir(new File(ROOT, "patient"));
		// set the fhir context to DSTU3
		log.info("Setting context...");
		FhirJsonParser.setFhirContext(FhirContext.forDstu3());
		// get the patients
		log.info("Getting patient info for test patients...");
		File root = FileUtil.getFromProjectRoot(ROOT);
		File patientEverythingDir = new File(root, "_everything");
		File[] dirList = patientEverythingDir.listFiles();
		// for each patient
		for(File dir : dirList) {
			FhirPatient patient = FhirPatientFactory.build(dir);
			String patientId = patient.getPatientId();
			File patientDir = new File(root, "patient");
			FileUtil.mkdirs(patientDir);
			log.info("Got patient: " + patientId);
			writePatient(patient, patientDir);
		}
		log.info("Done.");
	}

	private static void writePatient(FhirPatient patient, File dir) {
		Patient fhirPatient = patient.getPatient().getPatient();
		String json = FhirJsonParser.serialize(fhirPatient);
		json = JsonUtil.prettyPrint(json);
		String patientId = patient.getPatientId();
		File patientDir = FileUtil.mkdirs(new File(dir, patientId));
		File patientFile = new File(patientDir, patientId);
		FileUtil.write(json, patientFile);
		log.info("File written to: " + FileUtil.getCanonicalPath(patientFile));		
	}
	
}
