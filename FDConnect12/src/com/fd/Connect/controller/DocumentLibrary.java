package com.fd.Connect.controller;

import com.google.gson.Gson;
import com.fd.Admin.*;
import com.fd.Admin.bean.*;
import com.fd.Connect.ProjectServices;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocumentLibrary extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

		if (userAction.equals("get_project"))
			getProject(request, response);

		else if (userAction.equals("get_brand"))
			getBrand(request, response);
		
		else if (userAction.equals("get_division"))
			getDivision(request, response);

		else if (userAction.equals("get_element"))
			getElement(request, response);

		else if (userAction.equals("get_hub"))
			getHub(request, response);
		
		else if (userAction.equals("get_sow"))
			getSow(request, response);
		
		else if (userAction.equals("get_dt"))
			getDt(request, response);
		
	    else if (userAction.equals("get_document_library"))
			getDocumentLibrary(request, response);
		
		else if (userAction.equals("get_document"))
			getDocument(request, response);
		
		else if (userAction.equals("update_document"))
			updateDocument(request, response);
		return;
	}

	protected void getProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String projectId=request.getParameter("projectId");
		//System.out.println("client name is controller "+clientName);
		ProjectServices scr = new ProjectServices();
		List<HashMap<String,Object>> list = scr.getProjectDropDownByClientId(Integer.parseInt(projectId));
		//System.out.println("project is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getBrand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String projectId=request.getParameter("projectId");
		//System.out.println("client name is controller "+clientName);
		ProjectServices scr = new ProjectServices();
		List<HashMap<String,Object>> list = scr.getBrandDropDownByProjectId(Integer.parseInt(projectId));
		//System.out.println("brand is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getDivision(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String projectId=request.getParameter("projectId");
		//System.out.println("client name is controller "+clientName);
		ProjectServices scr = new ProjectServices();
		HashMap<String,Object> hMap = scr.getDivisionNameByProjectId(Integer.parseInt(projectId));
		System.out.println("division is"+hMap);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getElement(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String projectId=request.getParameter("projectId");
		String brandId=request.getParameter("brandId");
		//System.out.println("client name is controller "+clientName);
		ProjectServices scr = new ProjectServices();
		List<HashMap<String,Object>> list = scr.getElementDropDownByProjectIdAndBrandId(Integer.parseInt(projectId),Integer.parseInt(brandId));
		//System.out.println("element is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getHub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//System.out.println("client name is controller "+clientName);
		ProjectServices scr = new ProjectServices();
		List<HashMap<String,Object>> list = scr.getHubDropDown();
		//System.out.println("project is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getSow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//System.out.println("client name is controller "+clientName);
		ProjectServices scr = new ProjectServices();
		List<HashMap<String,Object>> list = scr.getSowDropDown();
		//System.out.println("project is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getDt(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//System.out.println("client name is controller "+clientName);
		ProjectServices scr = new ProjectServices();
		List<HashMap<String,Object>> list = scr.getDtDropDown();
		//System.out.println("project is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getDocumentLibrary(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int projectId=Integer.parseInt(request.getParameter("projectId"));
		ProjectServices scr = new ProjectServices();
		List<HashMap<String,Object>> list = scr.getDocumentLibrary(projectId);
		System.out.println("project is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	
	protected void updateDocument(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int documentId = Integer.parseInt(request.getParameter("documentId"));
		String comments = request.getParameter("comments");
	
		ProjectServices scr = new ProjectServices();
        int status = scr.updateDocument(documentId,comments);
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	protected void getDocument(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int documentId=Integer.parseInt(request.getParameter("documentId"));
		ProjectServices scr = new ProjectServices();
		
		File fileToDownload=scr.getDocument(documentId);
		
	    OutputStream outStream = response.getOutputStream();
		ServletContext context1 = getServletConfig().getServletContext();
//
		String mimetype = context1.getMimeType(fileToDownload.getPath());

		System.out.println("MiMe type is "+mimetype);
		if (mimetype == null) {
			mimetype = "application/octet-stream";
		}
		response.setContentType(mimetype);
		response.setContentLength((int) fileToDownload.length());
		//String fileName1 = (new File(file1.getPath())).getName();
		//System.out.println("FIle name is:\t" + fileName);

	
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileToDownload.getName() + "\"");
		int BUFSIZE = 4096;
		byte[] byteBuffer = new byte[BUFSIZE];

		DataInputStream in = new DataInputStream(new FileInputStream(fileToDownload));
      int length;
		// reads the file's bytes and writes them to the response stream
		while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
			outStream.write(byteBuffer, 0, length);
		}

		in.close();
		outStream.close();
	

 		return;
		/*List<HashMap<String,Object>> list = scr.getDocumentLibrary();
		System.out.println("project is"+list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);*/

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
