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

public class ManageDepot extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);
		 if(userAction.equals("get_client"))
		 		getClient(request, response);
		else if (userAction.equals("get_depot"))
			getDepot(request, response);

		else if (userAction.equals("set_depot"))
			setDepot(request, response);

		else if (userAction.equals("delete_depot"))
			deleteDepot(request, response);

		else if (userAction.equals("update_depot"))
			updateDepot(request, response);
		
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
	
	protected void getDepot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int clientId=Integer.parseInt(request.getParameter("clientId"));
		Service scr = new Service();
//		List<HashMap<String,Object>> list = scr.getDepotByAreaId(areaId);
		List<HashMap<String,Object>> list = scr.getDepotByClientId(clientId);
        String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}


	protected void setDepot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String depotName = request.getParameter("depotName");
		String contactName = request.getParameter("contactName");
		String contactPhone = request.getParameter("contactPhone");
		String landMarkDetails = request.getParameter("landMarkDetails");
		String notes = request.getParameter("notes");
	    String address = request.getParameter("address");
		
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		int regionId = Integer.parseInt(request.getParameter("regionId"));
		int stateId = Integer.parseInt(request.getParameter("stateId"));
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		int townId = Integer.parseInt(request.getParameter("townId"));
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		int clientId = Integer.parseInt(request.getParameter("clientId"))
;        

		Depot dpt = new Depot();
		dpt.setDepotName(depotName);
		dpt.setContactName(contactName);
		dpt.setContactPhone(contactPhone);
		dpt.setLandMarkDetails(landMarkDetails);
		dpt.setNotes(notes);
		dpt.setAddress(address);
		dpt.setCountryId(countryId);
		dpt.setRegionId(regionId);
		dpt.setStateId(stateId);
		dpt.setCityId(cityId);
		dpt.setTownId(townId);
		dpt.setAreaId(areaId);
		dpt.setClientId(clientId);
		
		Service scr = new Service();
		int status = scr.setDepot(dpt);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteDepot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String depotIdsTemp[] = request.getParameterValues("depotId[]");
		int depotIds[]=new int[depotIdsTemp.length];
		
		for (int i = 0; i < depotIdsTemp.length; i++) {
			depotIds[i] = Integer.parseInt(depotIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteDepotByDepotIds(depotIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateDepot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int depotId = Integer.parseInt(request.getParameter("depotId"));
		String depotName = request.getParameter("depotName");
		String contactName = request.getParameter("contactName");
		String contactPhone = request.getParameter("contactPhone");
		String landMarkDetails = request.getParameter("landMarkDetails");
		String notes = request.getParameter("notes");
	    String address = request.getParameter("address");
		
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		int regionId = Integer.parseInt(request.getParameter("regionId"));
		int stateId = Integer.parseInt(request.getParameter("stateId"));
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		int townId = Integer.parseInt(request.getParameter("townId"));
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		int clientId = Integer.parseInt(request.getParameter("clientId"));
        

		Depot dpt = new Depot();
		dpt.setDepotId(depotId);
		dpt.setDepotName(depotName);
		dpt.setContactName(contactName);
		dpt.setContactPhone(contactPhone);
		dpt.setLandMarkDetails(landMarkDetails);
		dpt.setNotes(notes);
		dpt.setAddress(address);
		dpt.setCountryId(countryId);
		dpt.setRegionId(regionId);
		dpt.setStateId(stateId);
		dpt.setCityId(cityId);
		dpt.setTownId(townId);
		dpt.setAreaId(areaId);
		dpt.setClientId(clientId);

		Service scr = new Service();
		int status = scr.updateDepot(dpt);

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
		int countryId=Integer.parseInt(request.getParameter("countryId"));
		
	   
        Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getRegionByCountryId(countryId);
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int regionId=Integer.parseInt(request.getParameter("regionId"));
	
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getStateByRegionId(regionId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getCity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int stateId=Integer.parseInt(request.getParameter("stateId"));
	
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getCityByStateId(stateId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getTown(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int cityId=Integer.parseInt(request.getParameter("cityId"));
	
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getTownByCityId(cityId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getArea(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int townId=Integer.parseInt(request.getParameter("townId"));
		
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getAreaByTownId(townId);
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
