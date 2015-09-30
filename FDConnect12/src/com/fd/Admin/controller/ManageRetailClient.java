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

public class ManageRetailClient extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_retail_client"))
			getRetailClient(request, response);

		else if (userAction.equals("set_retail_client"))
			setRetailClient(request, response);

		else if (userAction.equals("delete_retail_client"))
			deleteRetailClient(request, response);

		else if (userAction.equals("update_retail_client"))
			updateRetailClient(request, response);
	}

	protected void getRetailClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getRetailClient();
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setRetailClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String retailClientName = request.getParameter("retailClientName");
		String address = request.getParameter("address");

		RetailClient rc = new RetailClient();
		rc.setRetailClientName(retailClientName);
		rc.setAddress(address);
		
		Service scr = new Service();
		int status = scr.setRetailClient(rc);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteRetailClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String retailClientIdsTemp[] = request.getParameterValues("retailClientId[]");
		
	    int retailClientIds[]=new int[retailClientIdsTemp.length];
		
		for (int i = 0; i < retailClientIdsTemp.length; i++) {
			retailClientIds[i] = Integer.parseInt(retailClientIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteRetailClientByRetailClientIds(retailClientIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateRetailClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String retailClientId = request.getParameter("retailClientId");
		String retailClientName = request.getParameter("retailClientName");
		String address = request.getParameter("address");

		RetailClient rc = new RetailClient();
		rc.setRetailClientId(Integer.parseInt(retailClientId));
		rc.setRetailClientName(retailClientName);
		rc.setAddress(address);

		Service scr = new Service();
		int status = scr.updateRetailClient(rc);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);

		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
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
