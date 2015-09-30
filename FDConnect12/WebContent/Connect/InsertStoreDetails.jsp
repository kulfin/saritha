<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	System.out.println("InsertStoreDetails Jsp");
	String columnSeperator = "@!@";
	ProjectServices projectServices = new ProjectServices();
	//store_name_v
	//store_addr_v
	//store_status_v
	//fd_hub_v
	//handle_by_v
	//tsi_name_v
	//tsi_phone_v chain_name_v
	String project_id = request.getParameter("project_id");
	System.out.println("project_id ----> " + project_id);

	String store_name_v = request.getParameter("store_name_v");
	System.out.println("store_name_v----> " + store_name_v);

	String store_addr_v = request.getParameter("store_addr_v");
	System.out.println("store_addr_v----> " + store_addr_v);

	String store_status_v = request.getParameter("store_status_v");
	System.out.println("store_status_v----> " + store_status_v);

	String fd_hub_v = request.getParameter("fd_hub_v");
	System.out.println("fd_hub_v----> " + fd_hub_v);

	String handle_by_v = request.getParameter("handle_by_v");
	System.out.println("handle_by_v----> " + handle_by_v);

	String tsi_name_v = request.getParameter("tsi_name_v");
	System.out.println("tsi_name_v " + tsi_name_v);

	String tsi_phone_v = request.getParameter("tsi_phone_v");
	System.out.println("tsi_phone_v----> " + tsi_phone_v);

	String chain_name_v = request.getParameter("chain_name_v");
	System.out.println("chain_name_v " + chain_name_v);

	String trade_name_v = request.getParameter("trade_name_v");
	System.out.println("trade_name_v " + trade_name_v);

	/**String country_name_v = request.getParameter("country_name_v");
	 System.out.println("country_name_v----> "+country_name_v);

	 String region_name_v= request.getParameter("region_name_v");
	 System.out.println("region_name_v "+region_name_v);**/

	String state_name_v = request.getParameter("state_name_v");
	System.out.println("state_name_v " + state_name_v);

	String city_name_v = request.getParameter("city_name_v");
	System.out.println("city_name_v----> " + city_name_v);

	String town_name_v = request.getParameter("town_name_v");
	System.out.println("town_name_v " + town_name_v);

	String area_name_v = request.getParameter("area_name_v");
	System.out.println("area_name_v " + area_name_v);

	String project_name_v = request.getParameter("project_name_v");
	System.out.println(project_name_v + "============project_name_v");
	int element_project_id = Integer.parseInt(project_name_v);

	String qty = request.getParameter("project_QTY");

	System.out.println(qty + "=================qty");
	int project_QTY = Integer.parseInt(qty);

	String store_status = request.getParameter("store_status_id");
	int store_status_id = Integer.parseInt(store_status);

	//trade_name_v
	//country_name_v
	//region_name_v state_name_vcity_name_v town_name_v area_name_v

	String param = store_name_v + columnSeperator + store_addr_v
			+ columnSeperator + store_status_v + columnSeperator
			+ fd_hub_v + columnSeperator + handle_by_v
			+ columnSeperator + tsi_name_v + columnSeperator
			+ tsi_phone_v + columnSeperator + chain_name_v
			+ columnSeperator + trade_name_v + columnSeperator
			+ state_name_v + columnSeperator + city_name_v
			+ columnSeperator + town_name_v + columnSeperator
			+ area_name_v + columnSeperator + store_status_id;

	int result = projectServices
			.insert_Project_store(param, project_id);
	System.out.println("result---- > " + result);

	if (result != -1) {

		//String getMaxStoreId=projectServices.getLatestInsertedStoreId();

		String resultFlag = projectServices
				.insert_project_store_element_mapping(result,
						element_project_id, project_QTY);
		if (resultFlag.equals(Constants.DATA_INSERTED)) {
			out.println("DATA INSERTED");
		}

	}
%>
