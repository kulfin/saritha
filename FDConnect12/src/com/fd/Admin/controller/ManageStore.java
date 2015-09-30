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

public class ManageStore extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_store"))
			getStore(request, response);

		else if (userAction.equals("set_store"))
			setStore(request, response);

		else if (userAction.equals("delete_store"))
			deleteStore(request, response);

		else if (userAction.equals("update_store"))
			updateStore(request, response);
		
		else if (userAction.equals("get_country"))
			getCountry(request, response);
		
	    else if (userAction.equals("get_region"))
			getRegion(request, response);
		
		else if (userAction.equals("get_state"))
			getState(request, response);
		
		else if (userAction.equals("get_city"))
			getCity(request, response);
		
		else if (userAction.equals("get_town"))
			getTown(request, response);
		
		else if (userAction.equals("get_area"))
			getArea(request, response);
		
		else if (userAction.equals("get_chain"))
			getChain(request, response);
	}

	protected void getStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String areaName=request.getParameter("areaName");
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getStoreByAreaName(areaName);
        String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}


	protected void setStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String storeName = request.getParameter("storeName");
		String contactName = request.getParameter("contactName");
		String contactPhone = request.getParameter("contactPhone");
		String landMarkDetails = request.getParameter("landMarkDetails");
		String notes = request.getParameter("notes");
	    String address = request.getParameter("address");
	    String storeFlag = request.getParameter("storeFlag");
		
		String  countryName = request.getParameter("countryName");
		String  regionName = request.getParameter("regionName");
		String  stateName = request.getParameter("stateName");
		String  cityName = request.getParameter("cityName");
		String  townName = request.getParameter("townName");
		String  areaName = request.getParameter("areaName");
		String  chainName = request.getParameter("chainName");

		Store str = new Store();
		str.setStoreName(storeName);
		str.setContactName(contactName);
		str.setContactPhone(contactPhone);
		str.setLandMarkDetails(landMarkDetails);
		str.setNotes(notes);
		str.setAddress(address);
		str.setStoreFlag(storeFlag);
		str.setCountryName(countryName);
		str.setRegionName(regionName);
		str.setStateName(stateName);
		str.setCityName(cityName);
		str.setTownName(townName);
		str.setAreaName(areaName);
		str.setChainName(chainName);
		
		Service scr = new Service();
		int  status = scr.setStore(str);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String storeIdsTemp[] = request.getParameterValues("storeId[]");
		int  storeIds[]=new int [storeIdsTemp.length];
		
		for (int  i = 0; i < storeIdsTemp.length; i++) {
			storeIds[i] = Integer.parseInt(storeIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteStoreByStoreIds(storeIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int  storeId = Integer.parseInt(request.getParameter("storeId"));
		String storeName = request.getParameter("storeName");
		String contactName = request.getParameter("contactName");
		String contactPhone = request.getParameter("contactPhone");
		String landMarkDetails = request.getParameter("landMarkDetails");
		String notes = request.getParameter("notes");
	    String address = request.getParameter("address");
	    String storeFlag = request.getParameter("storeFlag");
		
		String  countryName = request.getParameter("countryName");
		String  regionName = request.getParameter("regionName");
		String  stateName = request.getParameter("stateName");
		String  cityName = request.getParameter("cityName");
		String  townName = request.getParameter("townName");
		String  areaName = request.getParameter("areaName");
		String  chainName = request.getParameter("chainName");
        

		Store str = new Store();
		str.setStoreId(storeId);
		str.setStoreName(storeName);
		str.setContactName(contactName);
		str.setContactPhone(contactPhone);
		str.setLandMarkDetails(landMarkDetails);
		str.setNotes(notes);
		str.setAddress(address);
		str.setStoreFlag(storeFlag);
		str.setCountryName(countryName);
		str.setRegionName(regionName);
		str.setStateName(stateName);
		str.setCityName(cityName);
		str.setTownName(townName);
		str.setAreaName(areaName);
		str.setChainName(chainName);

		Service scr = new Service();
		int  status = scr.updateStore(str);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);

		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void getCountry(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getCountry();
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getRegion(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int  countryId=Integer.parseInt(request.getParameter("countryId"));
		
	   
        Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getRegionByCountryId(countryId);
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int  regionId=Integer.parseInt(request.getParameter("regionId"));
	
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getStateByRegionId(regionId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getCity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int  stateId=Integer.parseInt(request.getParameter("stateId"));
	
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getCityByStateId(stateId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getTown(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int  cityId=Integer.parseInt(request.getParameter("cityId"));
	
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getTownByCityId(cityId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getArea(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int  townId=Integer.parseInt(request.getParameter("townId"));
		
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getAreaByTownId(townId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void getChain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getChain();
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
