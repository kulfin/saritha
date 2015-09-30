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

public class ManageBrandCategory extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_brand_category"))
			getBrandCategory(request, response);

		else if (userAction.equals("set_brand_category"))
			setBrandCategory(request, response);

		else if (userAction.equals("delete_brand_category"))
			deleteBrandCategory(request, response);

		else if (userAction.equals("update_brand_category"))
			updateBrandCategory(request, response);
	}

	protected void getBrandCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getBrandCategory();
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setBrandCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String brandCategoryName = request.getParameter("brandCategoryName");

		BrandCategory bc = new BrandCategory();
		bc.setBrandCategoryName(brandCategoryName);
		
		Service scr = new Service();
		int status = scr.setBrandCategory(bc);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteBrandCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String brandCategoryIdsTemp[] = request.getParameterValues("brandCategoryId[]");
		
	    int brandCategoryIds[]=new int[brandCategoryIdsTemp.length];
		
		for (int i = 0; i < brandCategoryIdsTemp.length; i++) {
			brandCategoryIds[i] = Integer.parseInt(brandCategoryIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteBrandCategoryByBrandCategoryIds(brandCategoryIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateBrandCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String brandCategoryId = request.getParameter("brandCategoryId");
		String brandCategoryName = request.getParameter("brandCategoryName");

		BrandCategory bc = new BrandCategory();
		bc.setBrandCategoryId(Integer.parseInt(brandCategoryId));
		bc.setBrandCategoryName(brandCategoryName);

		Service scr = new Service();
		int status = scr.updateBrandCategory(bc);

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
