package org.nachc.testing.external.examples.fhir.valueset;

import java.io.File;
import java.util.List;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ValueSet;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.valueset.ValueSetParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetValueSetCodesAsCsvIntegrationTest {

	private static final String FILE_PATH= "/docs/_ETC/hiv-cds/vocabulary/valueset/generated/valueset-nachc-a2-de2.json";
	
	/**
	 * This example gets one of the valuesets for the hiv-cds project and gives the codes
	 * in that value set as a csv string that can be used to create a url that can be used
	 * to query for any observation that has any of the codes.  
	 */
	@Test
	public void shouldGetCsvString() {
		log.info("Starting test...");
		// create the value set and the value set parser
		File file = FileUtil.getFromProjectRoot(FILE_PATH);
		String json = FileUtil.getAsString(file);
		log.info("Got json\n" + json);
		ValueSet valueSet = FhirJsonParser.parse(json, ValueSet.class);
		ValueSetParser parser = new ValueSetParser(valueSet);
		// get the concepts in the valueset as Coding resources
		List<Coding> concepts = parser.getConcepts();
		for(Coding coding : concepts) {
			log.info("\tgot coding: " + coding.getSystem() + "\t" + coding.getCode() + "\t" + coding.getDisplay());
		}
		// get the concept codes as a csv string
		String csv = parser.getCodesAsCsv();
		log.info("Codes as csv: \n" + csv);
		log.info("Done.");
	}
}
