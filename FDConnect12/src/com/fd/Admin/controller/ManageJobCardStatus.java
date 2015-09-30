package com.fd.Admin.controller;

import com.google.gson.Gson;
import com.fd.Admin.*;
import com.fd.Admin.bean.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageJobCardStatus extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_job_card_status"))
			getJobCardStatus(request, response);

		else if (userAction.equals("set_job_card_status"))
			setJobCardStatus(request, response);

		else if (userAction.equals("delete_job_card_status"))
			deleteJobCardStatus(request, response);

		else if (userAction.equals("update_job_card_status"))
			updateJobCardStatus(request, response);
		
		else if (userAction.equals("get_role"))
			getRole(request, response);
	}

	protected void getJobCardStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getJobCardStatus();
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setJobCardStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String jobCardStatusName = request.getParameter("jobCardStatusName");
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		JobCardStatus jcs = new JobCardStatus();
		jcs.setJobCardStatusName(jobCardStatusName);
		jcs.setRoleId(roleId);
		Service scr = new Service();
		int status = scr.setJobCardStatus(jcs);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteJobCardStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String jobCardStatusIdsTemp[] = request.getParameterValues("jobCardStatusId[]");
		
	    int jobCardStatusIds[]=new int[jobCardStatusIdsTemp.length];
		
		for (int i = 0; i < jobCardStatusIdsTemp.length; i++) {
			jobCardStatusIds[i] = Integer.parseInt(jobCardStatusIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteJobCardStatusByJobCardStatusIds(jobCardStatusIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateJobCardStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String jobCardStatusId = request.getParameter("jobCardStatusId");
		String jobCardStatusName = request.getParameter("jobCardStatusName");
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		JobCardStatus jcs = new JobCardStatus();
		jcs.setJobCardStatusId(Integer.parseInt(jobCardStatusId));
		jcs.setJobCardStatusName(jobCardStatusName);
		jcs.setRoleId(roleId);

		Service scr = new Service();
		int status = scr.updateJobCardStatus(jcs);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);

		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getRole (HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getRoleForJobCardStatus();
        Gson gson = new Gson();
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
