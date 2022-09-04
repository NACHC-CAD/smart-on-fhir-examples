package org.nachc.smartonfhirexamples.util.web.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletAction {
	
	public String getPath() {
		return "";
	}
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// do nothing by default
	}
	
	public void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String actionName = getActionName(req);
			Class cls = this.getClass().getClassLoader().getClass().forName(actionName);
			Object obj = cls.newInstance();
			ServletAction action = (ServletAction) obj;
			action.execute(req, resp);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
	private String getActionName(HttpServletRequest req) {
		String path = this.getPath();
		if(path == null) {
			path = "";
		}
		path = path.trim();
		if(path.length() > 0 && path.endsWith(".") == false) {
			path = path + ".";
		}
		String actionName = path + req.getParameter("actionName");
		return actionName;
	}
	
	public void forward(HttpServletRequest req, HttpServletResponse resp, String url) {
		try {
			RequestDispatcher disp = req.getRequestDispatcher(url);
			disp.forward(req, resp);
		}
		catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}
