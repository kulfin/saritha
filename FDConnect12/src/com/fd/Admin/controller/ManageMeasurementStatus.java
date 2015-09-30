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

public class ManageMeasurementStatus extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_measurement_status"))
			getMeasurementStatus(request, response);

		else if (userAction.equals("set_measurement_status"))
			setMeasurementStatus(request, response);

		else if (userAction.equals("delete_measurement_status"))
			deleteMeasurementStatus(request, response);

		else if (userAction.equals("update_measurement_status"))
			updateMeasurementStatus(request, response);
	}

	protected void getMeasurementStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getMeasurementStatus();
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setMeasurementStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String measurementStatusName = request.getParameter("measurementStatusName");

		MeasurementStatus ms = new MeasurementStatus();
		ms.setMeasurementStatusName(measurementStatusName);
		
		Service scr = new Service();
		int status = scr.setMeasurementStatus(ms);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteMeasurementStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String measurementStatusIdsTemp[] = request.getParameterValues("measurementStatusId[]");
		
	    int measurementStatusIds[]=new int[measurementStatusIdsTemp.length];
		
		for (int i = 0; i < measurementStatusIdsTemp.length; i++) {
			measurementStatusIds[i] = Integer.parseInt(measurementStatusIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteMeasurementStatusByMeasurementStatusIds(measurementStatusIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateMeasurementStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String measurementStatusId = request.getParameter("measurementStatusId");
		String measurementStatusName = request.getParameter("measurementStatusName");

		MeasurementStatus ms = new MeasurementStatus();
		ms.setMeasurementStatusId(Integer.parseInt(measurementStatusId));
		ms.setMeasurementStatusName(measurementStatusName);

		Service scr = new Service();
		int status = scr.updateMeasurementStatus(ms);

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
