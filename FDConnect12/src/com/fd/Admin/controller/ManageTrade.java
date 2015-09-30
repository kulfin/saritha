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

public class ManageTrade extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_trade"))
			getTrade(request, response);

		else if (userAction.equals("set_trade"))
			setTrade(request, response);

		else if (userAction.equals("delete_trade"))
			deleteTrade(request, response);

		else if (userAction.equals("update_trade"))
			updateTrade(request, response);
	}

	protected void getTrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getTrade();
		System.out.println(list);
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setTrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String tradeName = request.getParameter("tradeName");

		Trade trd = new Trade();
		trd.setTradeName(tradeName);
		
		Service scr = new Service();
		int status = scr.setTrade(trd);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteTrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String tradeIdsTemp[] = request.getParameterValues("tradeId[]");
		
	    int tradeIds[]=new int[tradeIdsTemp.length];
		
		for (int i = 0; i < tradeIdsTemp.length; i++) {
			tradeIds[i] = Integer.parseInt(tradeIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteTradeByTradeIds(tradeIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateTrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tradeId = request.getParameter("tradeId");
		String tradeName = request.getParameter("tradeName");

		Trade trd = new Trade();
		trd.setTradeId(Integer.parseInt(tradeId));
		trd.setTradeName(tradeName);

		Service scr = new Service();
		int status = scr.updateTrade(trd);

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
