package org.nachc.smartonfhirexamples.action.example007;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r4.model.Patient;
import org.nachc.smartonfhirexamples.action.abs.AppAction;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.diagnosticreport.DiagnosticReportParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.json.JsonUtil;
import com.nachc.hivcds.util.valueset.hivtest.HivTestCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetHivTests extends AppAction {


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// get the patient string from the request
			log.info("Getting basic patient info from JSON string...");
			String patientJson = IOUtils.toString(req.getReader());
			patientJson = JsonUtil.prettyPrint(patientJson);
			String msg = "";
			msg += "Got patient string: \n\nSTART PATIENT ------------------------";
			msg += patientJson;
			msg += "\nEND PATIENT ------------------------------------------------";
			log.info(msg);
			// parse the patient string into an hl7 object and then into a parser object
			Patient patient = FhirJsonParser.parse(patientJson, Patient.class);
			PatientParser parser = new PatientParser(patient);
			log.info("Successfully parsed patient");
			// add the json string to the request
			req.setAttribute("patientJson", patientJson);
			// get the fname
			String fname = parser.getFirstName();
			req.setAttribute("fname", fname);
			log.info("Patient First Name: " + fname);
			// get the lname
			String lname = parser.getLastName();
			req.setAttribute("lname", lname);
			log.info("Patient Last Name: " + lname);
			// get the gender
			String gender = parser.getGenderDisplay();
			req.setAttribute("gender", gender);
			log.info("Patient Gender: " + gender);
			// get the dob
			String dob = parser.getBirthDateAsString();
			req.setAttribute("dob", dob);
			log.info("Patient DOB: " + dob);
			// done
			this.forward(req, resp, "/WEB-INF/jsp/pages/005-serverside/impl/PatientInfo.jsp");
			// get the diagnostic reports
			List<String> hivTestCodes = HivTestCodes.get();
			List<DiagnosticReportParser> hivReports = new ArrayList<DiagnosticReportParser>();
			log.info("Done");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}