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

public class ManageStoreStatus extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_store_status"))
			getStoreStatus(request, response);

		else if (userAction.equals("set_store_status"))
			setStoreStatus(request, response);

		else if (userAction.equals("delete_store_status"))
			deleteStoreStatus(request, response);

		else if (userAction.equals("update_store_status"))
			updateStoreStatus(request, response);
	}

	protected void getStoreStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getStoreStatus();
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setStoreStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String storeStatusName = request.getParameter("storeStatusName");
		String description = request.getParameter("description");

		StoreStatus ss = new StoreStatus();
		ss.setStoreStatusName(storeStatusName);
		ss.setDescription(description);
		
		Service scr = new Service();
		int status = scr.setStoreStatus(ss);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteStoreStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String storeStatusIdsTemp[] = request.getParameterValues("storeStatusId[]");
		
	    int storeStatusIds[]=new int[storeStatusIdsTemp.length];
		
		for (int i = 0; i < storeStatusIdsTemp.length; i++) {
			storeStatusIds[i] = Integer.parseInt(storeStatusIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteStoreStatusByStoreStatusIds(storeStatusIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateStoreStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String storeStatusId = request.getParameter("storeStatusId");
		String storeStatusName = request.getParameter("storeStatusName");
		String description = request.getParameter("description");

		StoreStatus ss = new StoreStatus();
		ss.setStoreStatusId(Integer.parseInt(storeStatusId));
		ss.setStoreStatusName(storeStatusName);
		ss.setDescription(description);

		Service scr = new Service();
		int status = scr.updateStoreStatus(ss);

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
