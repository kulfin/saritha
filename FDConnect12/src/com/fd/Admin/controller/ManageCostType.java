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

public class ManageCostType extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_cost_type"))
			getCostType(request, response);

		else if (userAction.equals("set_cost_type"))
			setCostType(request, response);

		else if (userAction.equals("delete_cost_type"))
			deleteCostType(request, response);

		else if (userAction.equals("update_cost_type"))
			updateCostType(request, response);
	}

	protected void getCostType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getCostType();
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setCostType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String costTypeName = request.getParameter("costTypeName");

		CostType ct = new CostType();
		ct.setCostTypeName(costTypeName);
		
		Service scr = new Service();
		int status = scr.setCostType(ct);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteCostType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String costTypeIdsTemp[] = request.getParameterValues("costTypeId[]");
		
	    int costTypeIds[]=new int[costTypeIdsTemp.length];
		
		for (int i = 0; i < costTypeIdsTemp.length; i++) {
			costTypeIds[i] = Integer.parseInt(costTypeIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteCostTypeByCostTypeIds(costTypeIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateCostType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String costTypeId = request.getParameter("costTypeId");
		String costTypeName = request.getParameter("costTypeName");

		CostType ct = new CostType();
		ct.setCostTypeId(Integer.parseInt(costTypeId));
		ct.setCostTypeName(costTypeName);

		Service scr = new Service();
		int status = scr.updateCostType(ct);

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
