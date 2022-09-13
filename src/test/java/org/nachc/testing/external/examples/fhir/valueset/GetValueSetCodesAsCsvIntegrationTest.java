package org.nachc.testing.external.examples.fhir.valueset;

import java.io.File;
import java.util.List;

import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.r4.model.ValueSet.ConceptSetComponent;
import org.junit.Test;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetValueSetCodesAsCsvIntegrationTest {

	private static final String FILE_PATH= "/docs/_ETC/hiv-cds/vocabulary/valueset/generated/valueset-nachc-a2-de2.json";
	
	@Test
	public void shouldGetCsvString() throws Exception {
		File file = FileUtil.getFromProjectRoot(FILE_PATH);
		String json = FileUtil.getAsString(file);
		log.info("Got json\n" + json);
		ValueSet valueSet = FhirJsonParser.parse(json, ValueSet.class);
		List<ConceptSetComponent> component = valueSet.getCompose().getInclude();
		log.info("Got " + component.size() + " values");
		log.info("Got values");
		for(ConceptSetComponent comp : component) {
			List<ConceptReferenceComponent> concepts =  comp.getConcept();
			for(ConceptReferenceComponent concept : concepts) {
				log.info(concept.getDisplay());
			}
		}
		log.info("Starting test...");
		log.info("Done.");
	}
}
