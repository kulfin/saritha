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

public class ManageChain extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_chain"))
			getChain(request, response);

		else if (userAction.equals("set_chain"))
			setChain(request, response);

		else if (userAction.equals("delete_chain"))
			deleteChain(request, response);

		else if (userAction.equals("update_chain"))
			updateChain(request, response);
		
		else if (userAction.equals("get_trade"))
			getTrade(request, response);
		
		else if (userAction.equals("get_retail_client"))
			getRetailClient(request, response);
	
	}

	protected void getChain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int tradeId = Integer.parseInt(request.getParameter("tradeId"));
	
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getChainByTradeId(tradeId);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("Json Object As a Response\t;;;"+json.toString());
		response.getWriter().write(json);

	}

	protected void setChain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String chainName = request.getParameter("chainName");
		int tradeId = Integer.parseInt(request.getParameter("tradeId"));
		int retailClientId = Integer.parseInt(request.getParameter("retailClientId"));
		
        Chain chn = new Chain();
		chn.setChainName(chainName);
        chn.setTradeId(tradeId);
		chn.setRetailClientId(retailClientId);
		
		Service scr = new Service();
		int status = scr.setChain(chn);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteChain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String chainIdsTemp[] = request.getParameterValues("chainId[]");
		
	    int chainIds[]=new int[chainIdsTemp.length];
		
		for (int i = 0; i < chainIdsTemp.length; i++) {
			chainIds[i] = Integer.parseInt(chainIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteChainByChainIds(chainIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateChain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		String chainName = request.getParameter("chainName");
	    int chainId = Integer.parseInt(request.getParameter("chainId"));
		int tradeId = Integer.parseInt(request.getParameter("tradeId"));
		int retailClientId = Integer.parseInt(request.getParameter("retailClientId"));
		
        Chain chn = new Chain();
        chn.setChainId(chainId);
		chn.setChainName(chainName);
	    chn.setTradeId(tradeId);
		chn.setRetailClientId(retailClientId);

		Service scr = new Service();
		int status = scr.updateChain(chn);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);

		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getTrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getTrade();
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getRetailClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getRetailClient();
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
