package com.fd.Connect.controller;

import com.google.gson.Gson;
import com.fd.Connect.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MndJobCardAssignment extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userAction = request.getParameter("userAction");
		 System.out.println(" user action is "+userAction);

	
		 if (userAction.equals("get_client"))
			getClient(request, response);
		
		 else if (userAction.equals("get_Project"))
			getProject(request, response);
		 
		 else if (userAction.equals("get_division"))
				getDivision(request, response);
		 
		 else if (userAction.equals("get_mapped_Project_store"))
				getMappedProjectStore(request, response);
		 
		 else if (userAction.equals("get_Project_store"))
				getProjectStore(request, response);
		 
		 else if (userAction.equals("get_unmapped_Project_store"))
				getUnmappedProjectStore(request, response);
		 
		 else if (userAction.equals("get_store_status"))
			 getStoreStatusDropDown(request, response);
		 
		 else if (userAction.equals("is_Project_store_mapping"))
			   isProjectStoreMapping(request, response);
		 
		 else if (userAction.equals("get_mapped_Project_store_detail"))
				getMappedProjectStoreDetail(request, response);
		 
		 else if (userAction.equals("get_Project_store_detail"))
				getProjectStoreDetail(request, response);
		 
		 else if (userAction.equals("get_mapped_Project_element"))
				getMappedProjectElement(request, response);
		 
		 else if (userAction.equals("get_Project_element"))
				getProjectElement(request, response);
		 
		 else if (userAction.equals("is_Project_store_element_mapping"))
			   isProjectStoreElementMapping(request, response);
		 
		 else if (userAction.equals("get_Project_store_element_detail"))
				getProjectStoreElementDetail(request, response);
		 
		 else if (userAction.equals("get_Project_element_detail"))
				getProjectElementDetail(request, response);
		 
         else if (userAction.equals("get_job_card_number"))
				getJobCardNumber(request, response);
		 
		 else if (userAction.equals("set_job_card"))
				setJobCard(request, response);
		 
		 else if (userAction.equals("set_element_status"))
				setElementStatus(request, response);
		 
		 else if (userAction.equals("set_store_status"))
				setStoreStatus(request, response);
		 
		 else if(userAction.equals("get_element_status"))
			 	getElementStatus(request,response);
		 
		 else if(userAction.equals("update_mnd_Data"))
			 update_Mnd_Data(request,response);
	}

	
	protected void getClient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String divisionName = request.getParameter("divisionName");
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getClientByProjectDivision(divisionName);
		//System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void getProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        String divisionName = request.getParameter("divisionName");
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getProjectDropDownByClientIdAndProjectDivisionName(clientId, divisionName);
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getDivision(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getDivisionByProjectId(ProjectId);
		
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getMappedProjectStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getMappedProjectStoreDropDownByProjectId(ProjectId);
		System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getProjectStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getProjectStoreDropDownByProjectId(ProjectId);
		System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getUnmappedProjectStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getUnmappedProjectStoreDropDownByProjectId(ProjectId);
		System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getStoreStatusDropDown(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getStoreStatusDropDown();
		System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void isProjectStoreMapping(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int storeId = Integer.parseInt(request.getParameter("storeId"));
     
        
		Service scr = new Service();
		boolean isMapping = scr.isProjectStoreMapping(storeId);
		HashMap<String,Boolean> hMap=new HashMap();
		hMap.put("isMapping",isMapping);
		System.out.println(hMap);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getProjectStoreDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int storeId = Integer.parseInt(request.getParameter("storeId"));
		Service scr = new Service();
		HashMap<String,Object> hMap = scr.getProjectStoreByStoreId(storeId);
		System.out.println(hMap);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	
	protected void getMappedProjectStoreDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int storeId = Integer.parseInt(request.getParameter("storeId"));
		Service scr = new Service();
		HashMap<String,Object> hMap = scr.getMappedProjectStoreByStoreId(storeId);
		System.out.println(hMap);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getMappedProjectElement(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
        int storeId = Integer.parseInt(request.getParameter("storeId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getMappedProjectElementDropDownByProjectIdAndStoreId(ProjectId,storeId);
		System.out.println(list);
		String json = new Gson().toJson(list);
		System.out.println("json is "+json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getProjectElement(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int ProjectId = Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getProjectElementDropDownByProjectId(ProjectId);
		System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void isProjectStoreElementMapping(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        int elementId = Integer.parseInt(request.getParameter("elementId"));
        
		Service scr = new Service();
		boolean isMapping = scr.isProjectStoreElementMapping(storeId,elementId);
		HashMap<String,Boolean> hMap=new HashMap();
		hMap.put("isMapping",isMapping);
		System.out.println(hMap);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getProjectStoreElementDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int elementId = Integer.parseInt(request.getParameter("elementId"));
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        
		Service scr = new Service();
		HashMap<String,Object> hMap = scr.getProjectStoreElementByElementId(storeId,elementId);
		System.out.println(hMap);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getProjectElementDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        int elementId = Integer.parseInt(request.getParameter("elementId"));
		Service scr = new Service();
		HashMap<String,Object> hMap = scr.getProjectElementByElementId(elementId);
		System.out.println(hMap);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getJobCardNumber(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
      
        
   
//    	int ProjectId=Integer.parseInt(request.getParameter("ProjectId"));
		Service scr = new Service();
		int jobCardNumber = scr.getJobCardNumberForMnd();
		System.out.println("job card number is "+jobCardNumber);
		HashMap<String,Integer> hMap=new HashMap<String,Integer>();
		hMap.put("jobCardNumber", jobCardNumber);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void getElementStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside getElementStatus servelet Method");
		
		//int ProjectId =  Integer.parseInt(request.getParameter("ProjectId"));
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getElementStatus();
		//System.out.println(list);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void setJobCard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int ProjectId =  Integer.parseInt(request.getParameter("ProjectId"));
		
        String storeIdTemp[] =  request.getParameterValues("storeId[]");
        String jcNumber[] =  request.getParameterValues("jcNumber[]");
        String jcDate[] =  request.getParameterValues("jcDate[]");
        
        int storeId[]=new int[storeIdTemp.length];
        System.out.println("Length of storeIdTemp" + storeIdTemp.length);
    	for (int i = 0; i < storeIdTemp.length; i++) {
    		if(storeIdTemp[i] != ""){
    			storeId[i]=Integer.parseInt(storeIdTemp[i]);
    		}
		}
    	
		Service scr = new Service();
		int status = scr.setJobCardForMnd(ProjectId,storeId,jcNumber,jcDate);
		System.out.println("status is "+status);
		HashMap<String,Integer> hMap=new HashMap<String,Integer>();
		hMap.put("status", status);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void setElementStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int ProjectId =  Integer.parseInt(request.getParameter("ProjectId"));
		
        String storeIdTemp[] =  request.getParameterValues("storeId[]");
        String elementIdTemp[] =  request.getParameterValues("elementId[]");
        String elementStatus[] =  request.getParameterValues("elementStatus[]");
        
        
        int storeId[]=new int[storeIdTemp.length];
        int elementId[]=new int[elementIdTemp.length];
        
        
    	for (int i = 0; i < storeIdTemp.length; i++) {
			storeId[i]=Integer.parseInt(storeIdTemp[i]);
			elementId[i]=Integer.parseInt(elementIdTemp[i]);
			
		}
    	
		Service scr = new Service();
		int status = scr.setElementStatus(ProjectId,storeId,elementId,elementStatus);
		System.out.println("status is "+status);
		HashMap<String,Integer> hMap=new HashMap<String,Integer>();
		hMap.put("status", status);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void setStoreStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int ProjectId =  Integer.parseInt(request.getParameter("ProjectId"));
		
        String storeIdTemp[] =  request.getParameterValues("storeId[]");
        String storeStatusIdTemp[] =  request.getParameterValues("statusId[]");
  
        
        int storeId[]=new int[storeIdTemp.length];
        int storeStatusId[]=new int[storeStatusIdTemp.length];
      
        
    	for (int i = 0; i < storeIdTemp.length; i++) {
			storeId[i]=Integer.parseInt(storeIdTemp[i]);
			storeStatusId[i]=Integer.parseInt(storeStatusIdTemp[i]);
			
		}
    	
		Service scr = new Service();
		int status = scr.setStoreStatus(ProjectId,storeId,storeStatusId);
		System.out.println("status is "+status);
		HashMap<String,Integer> hMap=new HashMap<String,Integer>();
		hMap.put("status", status);
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void update_Mnd_Data(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int ProjectId =  Integer.parseInt(request.getParameter("ProjectId"));
		System.out.println("project Id is:\t " + ProjectId);
        int storeId =  Integer.parseInt(request.getParameter("StoreId"));
        System.out.println("store Id :\t" + storeId);
        int storeStatusId =  Integer.parseInt(request.getParameter("StoreStatusId"));
        System.out.println("store Status Id:\t" + storeStatusId);
        int quantity = Integer.parseInt(request.getParameter("Quantity"));
        System.out.println("quantity :\t" + quantity);
        String comments = request.getParameter("Comments");
        System.out.println("comments:\t" + comments);
        int elementStatusId = Integer.parseInt(request.getParameter("ElementStatusId"));
        System.out.println("element status id "  + elementStatusId);
        int elementId = Integer.parseInt(request.getParameter("ElementId"));
        System.out.println("project/Element id is " + elementId);
        int element_mapping_Id = Integer.parseInt(request.getParameter("Project_element_mappingId"));
        System.out.println("project/Element id is " + elementId);
    	
		Service scr = new Service();
		String  status = scr.updateMndDetails(ProjectId, storeId, storeStatusId, quantity, comments, elementStatusId,elementId,element_mapping_Id);
		System.out.println("status is "+status);
		PrintWriter out = response.getWriter();
		out.write(status);
		
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
