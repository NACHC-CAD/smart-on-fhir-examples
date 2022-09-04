package org.nachc.smartonfhirexamples.action.example005;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nachc.smartonfhirexamples.action.abs.AppAction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBasicPatientInfo extends AppAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		log.info("Doing hello world action");
		log.info("Done");
		this.forward(req, resp, "/WEB-INF/jsp/pages/hello/HelloWorld.jsp");
	}

}
