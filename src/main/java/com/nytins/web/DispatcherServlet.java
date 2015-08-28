package com.nytins.web;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Recieved request " + request.getRequestURI());
		
		String page = getInitParameter(request.getRequestURI());
		System.out.println("Page to dispatch to: " + page);

		logUserActivity(request.getRemoteAddr(), page);
		request.getRequestDispatcher(page).forward(request, response);
	}

	private void logUserActivity(String ipAddress, String page) throws IOException {
		String logDir = getServletContext().getRealPath("/");
		
		try(FileWriter writer = new FileWriter(logDir + "/user-activity.log", true)) {
			writer.write(new Date().toString() + "," + ipAddress + "," + page + "\n");
		}
	}
}
