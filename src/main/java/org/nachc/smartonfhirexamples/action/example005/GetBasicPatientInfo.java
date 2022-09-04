package org.nachc.smartonfhirexamples.action.example005;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.nachc.smartonfhirexamples.action.abs.AppAction;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpClientFactory;
import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBasicPatientInfo extends AppAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			log.info("Getting basic patient info from JSON string...");
			String patientJson = IOUtils.toString(req.getReader());
			patientJson = JsonUtil.prettyPrint(patientJson);
			String msg = "";
			msg += "Got patient string: \n\nSTART PATIENT ------------------------";
			msg += patientJson;
			msg += "\nEND PATIENT ------------------------------------------------";
			log.info(msg);
			Patient patient = FhirJsonParser.parse(patientJson, Patient.class);
			List<HumanName> names = patient.getName();
			log.info("Successfully parsed patient");
			req.setAttribute("patientJson", patientJson);
			this.forward(req, resp, "/WEB-INF/jsp/pages/005-serverside/impl/PatientInfo.jsp");
			log.info("Done");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
