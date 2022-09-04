package org.nachc.smartonfhirexamples.servlet.forward;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nachc.smartonfhirexamples.action.abs.AppAction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForwardServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new AppAction().processRequest(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new AppAction().processRequest(req, resp);
	}

}
