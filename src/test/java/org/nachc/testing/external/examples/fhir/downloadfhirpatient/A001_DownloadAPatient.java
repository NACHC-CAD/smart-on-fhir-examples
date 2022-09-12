package org.nachc.testing.external.examples.fhir.downloadfhirpatient;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.tools.download.patient.writer.WriteFhirPatientToFile;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A001_DownloadAPatient {

	private static final String PATIENT_ID = "d78af07c-9cb9-4f31-9b11-c45a28f2eec8";
	
	private static final String OUT_DIR_NAME = "/src/test/java/org/nachc/testing/external/examples/fhir/downloadfhirpatient/patient";

	@Test
	public void shouldDownloadPatient() {
		log.info("Starting test...");
		File outDir = FileUtil.getFromProjectRoot(OUT_DIR_NAME);
		FileUtil.rmdir(outDir);
		new WriteFhirPatientToFile().exec(PATIENT_ID, "token-not-needed", outDir);
		log.info("Done.");
	}
	
}
