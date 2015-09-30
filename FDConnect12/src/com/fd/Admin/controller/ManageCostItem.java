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

public class ManageCostItem extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_cost_item"))
			getCostItem(request, response);

		else if (userAction.equals("set_cost_item"))
			setCostItem(request, response);

		else if (userAction.equals("delete_cost_item"))
			deleteCostItem(request, response);

		else if (userAction.equals("update_cost_item"))
			updateCostItem(request, response);
		
		else if (userAction.equals("get_cost_type"))
			getCostType(request, response);
	
	
	}

	protected void getCostItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int costTypeId = Integer.parseInt(request.getParameter("costTypeId"));
	
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getCostItemByCostTypeId(costTypeId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setCostItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String costItemName = request.getParameter("costItemName");
		int costTypeId = Integer.parseInt(request.getParameter("costTypeId"));
	
		
        CostItem ci = new CostItem();
		ci.setCostItemName(costItemName);
        ci.setCostTypeId(costTypeId);
	
		
		Service scr = new Service();
		int status = scr.setCostItem(ci);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteCostItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String costItemIdsTemp[] = request.getParameterValues("costItemId[]");
		
	    int costItemIds[]=new int[costItemIdsTemp.length];
		
		for (int i = 0; i < costItemIdsTemp.length; i++) {
			costItemIds[i] = Integer.parseInt(costItemIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteCostItemByCostItemIds(costItemIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateCostItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		String costItemName = request.getParameter("costItemName");
	    int costItemId = Integer.parseInt(request.getParameter("costItemId"));
		int costTypeId = Integer.parseInt(request.getParameter("costTypeId"));
		
		
        CostItem ci = new CostItem();
        ci.setCostItemId(costItemId);
		ci.setCostItemName(costItemName);
	    ci.setCostTypeId(costTypeId);
	

		Service scr = new Service();
		int status = scr.updateCostItem(ci);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);

		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getCostType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getCostType();
		
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
