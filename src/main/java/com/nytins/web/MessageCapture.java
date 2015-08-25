package com.nytins.web;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageCapture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Recieved message.");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		try(FileWriter writer = new FileWriter(getServletContext().getRealPath("/") + "/user-messages.log", true)) {
			writer.write(String.format("%s|%s|%s|%s|%s\n", new Date().toString(), name, email, subject, message));
		}
		
		request.getRequestDispatcher("/index.html").forward(request, response);
	}
}
