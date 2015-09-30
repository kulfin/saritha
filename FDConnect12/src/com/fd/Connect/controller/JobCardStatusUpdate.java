package com.fd.Connect.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fd.Connect.Service;
import com.google.gson.Gson;

/**
 * Servlet implementation class JobCardStatusUpdate
 */
public class JobCardStatusUpdate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		 
		 else if (userAction.equals("get_mapped_Project_store"))
				getMappedProjectStore(request, response);
		 
		 else if (userAction.equals("get_job_card_by_Project_select_mode"))
			 get_job_card_by_Project_select_mode(request,response);
		 
		 else if(userAction.equals("update_job_card"))
			 update_job_card(request,response);
	}	

	
	protected void getClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String divisionName = request.getParameter("divisionName");
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getClientByProjectDivision(divisionName);
		//System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void getProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        String divisionName = request.getParameter("divisionName");
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getProjectDropDownByClientIdAndProjectDivisionName(clientId, divisionName);
		
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
	
	protected void getMappedProjectStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getMappedProjectStoreDropDownByProjectId(ProjectId);
		System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void get_job_card_by_Project_select_mode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		 int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		 String fd_division_name=request.getParameter("fd_division_name");
			Service scr = new Service();
			List<HashMap<String,Object>> list = scr.get_job_card_by_Project_select_mode(ProjectId,fd_division_name);
			System.out.println(list);
			String json = new Gson().toJson(list);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

	}
	
	protected void update_job_card(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		 int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		 String fd_division_name=request.getParameter("fd_division_name");
			Service scr = new Service();
			List<HashMap<String,Object>> list = scr.get_job_card_by_Project_select_mode(ProjectId, fd_division_name);
			System.out.println(list);
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
