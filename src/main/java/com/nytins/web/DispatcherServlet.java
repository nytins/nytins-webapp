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
		
		System.out.println("Real path: " + getServletContext().getRealPath("/"));
		
		String page;
		switch(request.getRequestURI()) {
		case "/pages/left":
			page = "/left-sidebar.html";
			break;
		case "/pages/right":
			page = "/right-sidebar.html";
			break;
		case "/pages/contact-me":
			page = "/contact-me.jsp";
			break;
		case "/":
		case "/pages/home":
		default:
			page = "/index.html";
			break;
		}
		logUserActivity(request.getRemoteAddr(), getServletContext().getRealPath("/"), page);
		request.getRequestDispatcher(page).forward(request, response);
	}

	private void logUserActivity(String ipAddress, String realPath, String page) throws IOException {
		try(FileWriter writer = new FileWriter(realPath + "/user-activity.log", true)) {
			writer.write(new Date().toString() + "," + ipAddress + "," + page + "\n");
		}
	}
}
