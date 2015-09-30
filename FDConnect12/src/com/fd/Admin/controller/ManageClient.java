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

public class ManageClient extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_client"))
			getClient(request, response);

		else if (userAction.equals("set_client"))
			setClient(request, response);

		else if (userAction.equals("delete_client"))
			deleteClient(request, response);

		else if (userAction.equals("update_client"))
			updateClient(request, response);
		
		else if (userAction.equals("get_country"))
			getCountry(request, response);
		
		else if (userAction.equals("get_state"))
			getState(request, response);
		
		else if (userAction.equals("get_city"))
			getCity(request, response);
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

	protected void setClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String clientName = request.getParameter("clientName");
		String localCurrency = request.getParameter("localCurrency");
		String baseCurrency = request.getParameter("baseCurrency");
		String tinNumber = request.getParameter("tinNumber");
		String cstNumber = request.getParameter("cstNumber");
	    String pinCode =request.getParameter("pinCode");
		String address = request.getParameter("address");
		
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		int stateId = Integer.parseInt(request.getParameter("stateId"));
		int cityId = Integer.parseInt(request.getParameter("cityId"));
        

		Client clt = new Client();
		clt.setClientName(clientName);
		clt.setLocalCurrency(localCurrency);
		clt.setBaseCurrency(baseCurrency);
		clt.setTinNumber(tinNumber);
		clt.setCstNumber(cstNumber);
		clt.setPinCode(pinCode);
		clt.setAddress(address);
		clt.setCountryId(countryId);
		clt.setStateId(stateId);
		clt.setCityId(cityId);
		
		Service scr = new Service();
		int status = scr.setClient(clt);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String clientIdsTemp[] = request.getParameterValues("clientId[]");
		
	
		int clientIds[]=new int[clientIdsTemp.length];
		
		for (int i = 0; i < clientIdsTemp.length; i++) {
			clientIds[i] = Integer.parseInt(clientIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteClientByClientIds(clientIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int clientId=Integer.parseInt(request.getParameter("clientId"));
		String clientName = request.getParameter("clientName");
		String localCurrency = request.getParameter("localCurrency");
		String baseCurrency = request.getParameter("baseCurrency");
		String tinNumber = request.getParameter("tinNumber");
		String cstNumber = request.getParameter("cstNumber");
	    String pinCode =request.getParameter("pinCode");
		String address = request.getParameter("address");
		
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		int stateId = Integer.parseInt(request.getParameter("stateId"));
		int cityId = Integer.parseInt(request.getParameter("cityId"));
        

		Client clt = new Client();
		clt.setClientId(clientId);
		clt.setClientName(clientName);
		clt.setLocalCurrency(localCurrency);
		clt.setBaseCurrency(baseCurrency);
		clt.setTinNumber(tinNumber);
		clt.setCstNumber(cstNumber);
		clt.setPinCode(pinCode);
		clt.setAddress(address);
		clt.setCountryId(countryId);
		clt.setStateId(stateId);
		clt.setCityId(cityId);

		Service scr = new Service();
		int status = scr.updateClient(clt);

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
	
	protected void getState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		int countryId=Integer.parseInt(request.getParameter("countryId"));
	
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getStateByCountryId(countryId);
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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
