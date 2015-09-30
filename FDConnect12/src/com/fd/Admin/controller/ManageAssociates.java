package com.fd.Admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fd.Admin.Service;
import com.fd.Admin.bean.Associates;
import com.fd.Admin.bean.Brand;
import com.google.gson.Gson;

/**
 * Servlet implementation class ManageAssociates
 */
public class ManageAssociates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	   String userAction = request.getParameter("userAction");
	   System.out.println("UserAction is" + userAction);
	   if(userAction.equals("get_associates"))
	   {
		   System.out.println("");
		   get_associates(request, response);
	   }
	   else if(userAction.equals("set_associate")){
		   set_associates(request, response);
	   }
	   else if(userAction.equals("delete_associate")){
		   deleteAssociate(request, response);
	   }
	   else if(userAction.equals("update_associate")){
		   updateAssociate(request, response);
	   }
	   else if(userAction.equals("associate_capacity_details")){
		   get_associate_capacity_details(request,response);
	   }
   }
	
	protected void get_associates(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getAssociates();
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	protected void set_associates(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String Name = request.getParameter("name");
		String address = request.getParameter("address");
		String city= request.getParameter("city");
		String email = request.getParameter("email");
		String contact_name = request.getParameter("contact_name");
		String contact_phone = request.getParameter("contact_phone");
		String  tin_no = request.getParameter("tin_no");
        String cst_no = request.getParameter("cst_no");
        String excisable = request.getParameter("excisable");
		
        Associates associate = new Associates();
        associate.setName(Name);
        associate.setAddress(address);
        associate.setCity(city);
        associate.setEmail(email);
        associate.setContactName(contact_name);
        associate.setContactPhone(contact_phone);
		associate.setTinNumber(tin_no);
		associate.setCstNumber(cst_no);
		associate.setExcisable(excisable);
		Service scr = new Service();
		int status = scr.setAssociates(associate);
		
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void deleteAssociate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String associateIdsTemp[] = request.getParameterValues("associateId[]");
		
	    int associateIds[]=new int[associateIdsTemp.length];
		
		for (int i = 0; i < associateIdsTemp.length; i++) {
			associateIds[i] = Integer.parseInt(associateIdsTemp[i]);
		}
		
		Service scr = new Service();
		int status = scr.deleteAssociateByAssociateIds(associateIds);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);
		
		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	protected void updateAssociate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int associateId = Integer.parseInt(request.getParameter("associateId"));
		String associateName = request.getParameter("associateName");
		String associateAddress = request.getParameter("associateAddress");
		String associateCity = request.getParameter("associateCity");
		String associateEmail =request.getParameter("associateEmail");
		String associateContact_name =request.getParameter("associateContact_name");
		String associateContact_phone =request.getParameter("associateContact_phone");
		String associateTin_no = request.getParameter("associateTin_no");
		String associateCst_no = request.getParameter("associateCst_no");
		String associateExcisable = request.getParameter("associateExcisable");
		
		Associates associates = new Associates();
		associates.setId(associateId);
		associates.setName(associateName);
		associates.setAddress(associateAddress);
		associates.setCity(associateCity);
		associates.setEmail(associateEmail);
		associates.setContactName(associateContact_name);
		associates.setContactPhone(associateContact_phone);
		associates.setTinNumber(associateTin_no);
		associates.setCstNumber(associateCst_no);
		associates.setExcisable(associateExcisable);
       /* Brand brnd = new Brand();
        brnd.setBrandId(brandId);
		brnd.setBrandName(brandName);
		brnd.setNotes(notes);
		brnd.setClientId(clientId);
		brnd.setBrandCategoryId(brandCategoryId);*/

		Service scr = new Service();
		int status = scr.updateAssociate(associates);

		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("status", status);

		
		String json = new Gson().toJson(hMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	protected void get_associate_capacity_details(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int assciate_Id = Integer.parseInt(request.getParameter("associateId"));
		Associates associates = new Associates();
		associates.setId(assciate_Id);
		Service scr = new Service();
		List<HashMap<String,Object>> list = scr.getAssociate_Capacity_Details(associates);
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
