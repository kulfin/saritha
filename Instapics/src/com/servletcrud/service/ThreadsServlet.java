package com.servletcrud.service;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/threadsServlet")
public class ThreadsServlet extends HttpServlet {
	private static final long serialVersionUID = 7770323867448369047L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int number = Integer.valueOf(req.getParameter("number"));
		try {
			System.out.println("Servlet no. "+number+" called.");
			URL url = new URL(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getRequestURI()+"?number="+(number+1));
			Object content = url.getContent();
			resp.setContentType("plain/text");
			resp.getWriter().write("OK: "+content);
		} catch (Throwable e) {
			String message = "Reached "+number+" of connections";
			System.out.println(message);
			System.out.println(e);
			resp.getWriter().write(message);
		}
	}
}