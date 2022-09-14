package org.nachc.testing.external.examples.fhir.search.synthea;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.ValueSet;
import org.junit.Test;
import org.nachc.smartonfhirexamples.util.FhirQuerySender;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.valueset.ValueSetParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.file.FileUtil;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A003b_FindPatientsWithAnyHivTestExampleIntegrationTest {

	private static final String DIR_PATH = "/docs/_ETC/hiv-cds/vocabulary/valueset/generated/";

	private static final String URL = "https://syntheticmass.mitre.org/v1/fhir/Observation?code=";
	
	/**
	 * This looks for any observations for an hiv test.  
	 */
	@Test
	public void shouldGetUrls() {
		log.info("Starting test...");
		List<String> urls = getUrls();
		for(String url : urls) {
			String json = FhirQuerySender.getForUrl(url);
			json = JsonUtil.prettyPrint(json);
			log.info("\n" + json);
		}
		log.info("Done.");
	}
	
	private List<String> getUrls() {
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
		ArrayList<String> rtn = new ArrayList<String>();
		rtn.add(url1);
		rtn.add(url2);
		rtn.add(url3);
		rtn.add(url4);
		return rtn;
	}

}
