package org.nachc.smartonfhirexamples.action.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nachc.smartonfhirexamples.action.abs.AppAction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldAction extends AppAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		log.info("Doing hello world action");
		log.info("Done");
		this.forward(req, resp, "/WEB-INF/jsp/pages/hello/HelloWorld.jsp");
	}

}
