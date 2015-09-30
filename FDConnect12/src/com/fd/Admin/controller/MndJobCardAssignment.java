package com.fd.Admin.controller;

import com.google.gson.Gson;
import com.fd.Connect.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MndJobCardAssignment extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

	
		 if (userAction.equals("get_client"))
			getClient(request, response);
		
		 else if (userAction.equals("get_Project"))
			getProject(request, response);
		 
		 else if (userAction.equals("get_division"))
				getDivision(request, response);
	
	}

	
	protected void getClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getClientDropDown();
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void getProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int clientId = Integer.parseInt(request.getParameter("clientId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getProjectDropDownByClientId(clientId);
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getDivision(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getDivisionByProjectId(ProjectId);
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
