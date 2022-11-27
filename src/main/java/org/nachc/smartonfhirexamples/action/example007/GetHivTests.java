package org.nachc.smartonfhirexamples.action.example007;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Patient;
import org.nachc.smartonfhirexamples.action.abs.AppAction;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.diagnosticreport.DiagnosticReportParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;
import com.nachc.hivcds.api.hashivtest.HasHivTest;
import com.nachc.hivcds.api.hashivtest.HasHivTestResponse;
import com.nachc.hivcds.util.valueset.hivtest.HivTestCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetHivTests extends AppAction {


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// get the patient string from the request
			log.info("-----------------------");
			log.info("Getting basic patient info and HIV TEST DATA from JSON string...");
			log.info("-----------------------");
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
			// add the patient data to the request
			processPatientInfo(req, patientJson, parser);
			// add the diagnostic report data to the request
			processDiagnosticReports(req, parser);
			// done
			this.forward(req, resp, "/WEB-INF/jsp/pages/007-hivtest/impl/HivTestInfo.jsp");
			// get the diagnostic reports
			List<String> hivTestCodes = HivTestCodes.get();
			List<DiagnosticReportParser> hivReports = new ArrayList<DiagnosticReportParser>();
			log.info("Done");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private void processPatientInfo(HttpServletRequest req, String patientJson, PatientParser parser) {
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
		// get the id
		String patientId = parser.getId();
		req.setAttribute("patientId", patientId);
		log.info("Patient ID: " + patientId);
	}

	private void processDiagnosticReports(HttpServletRequest req, PatientParser patientParser) {
		// THIS NEEDS TO BE REFACTORED, SHOULD USE SMART ON FHIR API IN JSP
		String patientId = patientParser.getId();
		log.info("PatientId: " + patientId);
		// get the diagnostic reports from the server
		String url = "https://r4.smarthealthit.org/DiagnosticReport?patient=" + patientId;
		log.info("Sending request to: \n" + url);
		HttpRequestClient client = new HttpRequestClient(url);
		client.doGet();
		String response = client.getResponse();
		response = JsonUtil.prettyPrint(response);
		String msg = "\n";
		msg += "Got Diagnostic reports: \n";
		msg += "---------------------------\n";
		msg += response + "\n";
		msg += "---------------------------\n";
		log.info(msg);
		// parser the response
		BundleParser parser = new BundleParser(response);
		List<DiagnosticReport> reports = parser.getResourceListForType(DiagnosticReport.class);
		List<DiagnosticReportParser> parsers = DiagnosticReportParser.getParserList(reports);
		req.setAttribute("diagnosticReportList", parsers);
		log.info("Got " + reports.size() + " Diagnostic reports");
		// check for hiv test
		HasHivTestResponse hivTestCheckResults = HasHivTest.getForDiagnosticReportList(reports);
		log.info("HAS TEST: " + hivTestCheckResults.hasTest());
		req.setAttribute("hivTestCheckResults", hivTestCheckResults);
		log.info("Done getting diagnostic reports");
	}

}
