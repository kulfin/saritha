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

public class ManageDocumentType extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_document_type"))
			getDocumentType(request, response);

		else if (userAction.equals("set_document_type"))
			setDocumentType(request, response);

		else if (userAction.equals("delete_document_type"))
			deleteDocumentType(request, response);

		else if (userAction.equals("update_document_type"))
			updateDocumentType(request, response);
	}

	protected void getDocumentType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getDocumentType();
        Gson gson = new Gson();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void setDocumentType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String documentTypeName = request.getParameter("documentTypeName");
		String description = request.getParameter("description");

		String owner = request.getParameter("owner");
		
		DocumentType ss = new DocumentType();
		ss.setDocumentTypeName(documentTypeName);
		ss.setDescription(description);
		ss.setOwner(description);
		
		Service scr = new Service();
		int status = scr.setDocumentType(ss);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void deleteDocumentType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String documentTypeIdsTemp[] = request.getParameterValues("documentTypeId[]");
		
	    int documentTypeIds[]=new int[documentTypeIdsTemp.length];
		
		for (int i = 0; i < documentTypeIdsTemp.length; i++) {
			documentTypeIds[i] = Integer.parseInt(documentTypeIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteDocumentTypeByDocumentTypeIds(documentTypeIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		Gson gson = new Gson();
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void updateDocumentType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String documentTypeId = request.getParameter("documentTypeId");
		String documentTypeName = request.getParameter("documentTypeName");
		String description = request.getParameter("description");
		String owner = request.getParameter("owner");
		
		DocumentType ss = new DocumentType();
		ss.setDocumentTypeId(Integer.parseInt(documentTypeId));
		ss.setDocumentTypeName(documentTypeName);
		ss.setDescription(description);
        ss.setOwner(description);
        
		Service scr = new Service();
		int status = scr.updateDocumentType(ss);

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
