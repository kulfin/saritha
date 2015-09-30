package com.fd.Admin.controller;

import com.google.gson.Gson;
import com.fd.Admin.*;
import com.fd.Admin.bean.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageBrand extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_brand"))
			getBrand(request, response);

		else if (userAction.equals("set_brand"))
			setBrand(request, response);

		else if (userAction.equals("delete_brand"))
			deleteBrand(request, response);

		else if (userAction.equals("update_brand"))
			updateBrand(request, response);
		
		else if (userAction.equals("get_client"))
			getClient(request, response);
		
		else if (userAction.equals("get_brand_category"))
			getBrandCategory(request, response);
	
	}

	protected void getBrand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int clientId = Integer.parseInt(request.getParameter("clientId"));
	
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getBrandByClientId(clientId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setBrand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String brandName = request.getParameter("brandName");
		String notes = request.getParameter("notes");
		
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		int brandCategoryId = Integer.parseInt(request.getParameter("brandCategoryId"));
		
        Brand brnd = new Brand();
		brnd.setBrandName(brandName);
		brnd.setNotes(notes);
		brnd.setClientId(clientId);
		brnd.setBrandCategoryId(brandCategoryId);
		
		Service scr = new Service();
		int status = scr.setBrand(brnd);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteBrand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String brandIdsTemp[] = request.getParameterValues("brandId[]");
		
	    int brandIds[]=new int[brandIdsTemp.length];
		
		for (int i = 0; i < brandIdsTemp.length; i++) {
			brandIds[i] = Integer.parseInt(brandIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteBrandByBrandIds(brandIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateBrand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		String brandName = request.getParameter("brandName");
		String notes = request.getParameter("notes");
		
		int brandId = Integer.parseInt(request.getParameter("brandId"));
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		int brandCategoryId = Integer.parseInt(request.getParameter("brandCategoryId"));
		
        Brand brnd = new Brand();
        brnd.setBrandId(brandId);
		brnd.setBrandName(brandName);
		brnd.setNotes(notes);
		brnd.setClientId(clientId);
		brnd.setBrandCategoryId(brandCategoryId);

		Service scr = new Service();
		int status = scr.updateBrand(brnd);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);

		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getClient();
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getBrandCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getBrandCategory();
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
