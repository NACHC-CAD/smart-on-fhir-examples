package org.nachc.testing.external.examples.fhir.valueset;

import org.hl7.fhir.r4.model.ValueSet;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.valueset.ValueSetParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetUrlsForHivTestingObservations {

	private static final String DIR_PATH = "/docs/_ETC/hiv-cds/vocabulary/valueset/generated/";

	private static final String URL = "https://r4.smarthealthit.org/Observation?code=";
	
	@Test
	public void shouldGetUrls() {
		log.info("Starting test...");
		// HIV1HIV2AbAgtestsCodes
		String HIV1HIV2AbAgtestsCodes = FileUtil.getAsString(FileUtil.getFromProjectRoot(DIR_PATH + "valueset-nachc-a2-de2.json"));
		ValueSet valueset1 = FhirJsonParser.parse(HIV1HIV2AbAgtestsCodes, ValueSet.class);
		ValueSetParser parser1 = new ValueSetParser(valueset1);
		String url1 = URL + parser1.getCodesAsCsv();
		// HIVTestCodesGrouper
		String HIVTestCodesGrouper = FileUtil.getAsString(FileUtil.getFromProjectRoot(DIR_PATH + "valueset-nachc-a2-de1-codes-grouper.json"));
		ValueSet valueset2 = FhirJsonParser.parse(HIVTestCodesGrouper, ValueSet.class);
		ValueSetParser parser2 = new ValueSetParser(valueset2);
		String url2 = URL + parser2.getCodesAsCsv();
		log.info("\n" + url2);
		// HIVTestOrderedCodes
		String HIVTestOrderedCodes = FileUtil.getAsString(FileUtil.getFromProjectRoot(DIR_PATH + "valueset-nachc-a2-de217.json"));
		ValueSet valueset3 = FhirJsonParser.parse(HIVTestOrderedCodes, ValueSet.class);
		ValueSetParser parser3 = new ValueSetParser(valueset3);
		String url3 = URL + parser3.getCodesAsCsv();
		// HIVViralLoadCodes
		String HIVViralLoadCodes = FileUtil.getAsString(FileUtil.getFromProjectRoot(DIR_PATH + "valueset-nachc-a2-de173.json"));
		ValueSet valueset4 = FhirJsonParser.parse(HIVViralLoadCodes, ValueSet.class);
		ValueSetParser parser4 = new ValueSetParser(valueset4);
		String url4 = URL + parser4.getCodesAsCsv();
		log.info("URLS: \n" + url1 + "\n" + url2 + "\n" + url3 + "\n" + url4 + "\n");
		log.info("Done.");
	}

}
