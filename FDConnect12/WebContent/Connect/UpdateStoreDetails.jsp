<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

System.out.println("UpdateStoreDetails.jsp");
String columnSeperator="!@!";

String projectStoreid = request.getParameter("projectStoreid");
int projectID=Integer.parseInt(projectStoreid);
System.out.println("projectStoreid--->"+projectStoreid);

String store_name_v = request.getParameter("store_name_v");
System.out.println("store_name_v--->"+store_name_v);

String project_id = request.getParameter("project_id");
System.out.println("project_id--->"+project_id);

String store_addr_v = request.getParameter("store_addr_v");
System.out.println("store_addr_v--->"+store_addr_v);

String store_status_v = request.getParameter("store_status_v");
System.out.println("store_status_v--->"+store_status_v);

String fd_hub_v = request.getParameter("fd_hub_v");
System.out.println("fd_hub_v--->"+fd_hub_v);

String handle_by_v = request.getParameter("handle_by_v");
System.out.println("handle_by_v--->"+handle_by_v);

String tsi_name_v = request.getParameter("tsi_name_v");
System.out.println("tsi_name_v--->"+tsi_name_v);

String tsi_phone_v = request.getParameter("tsi_phone_v");
System.out.println("tsi_phone_v--->"+tsi_phone_v);


String chain_name_v = request.getParameter("chain_name_v");
System.out.println("chain_name_v--->"+chain_name_v);

String trade_name_v = request.getParameter("trade_name_v");
System.out.println("trade_name_v--->"+trade_name_v);

String city_name_v = request.getParameter("city_name_v");
System.out.println("city_name_v--->"+city_name_v);

String town_name_v = request.getParameter("town_name_v");
System.out.println("town_name_v--->"+town_name_v);


String area_name_v = request.getParameter("area_name_v");
System.out.println("area_name_v--->"+area_name_v);

//String country_name_v = request.getParameter("country_name_v");
//System.out.println("country_name_v--->"+country_name_v);

String state_name_v = request.getParameter("state_name_v");
System.out.println("state_name_v--->"+state_name_v);

//String region_name_v = request.getParameter("region_name_v");
//System.out.println("region_name_v--->"+region_name_v);

String project_name_v=request.getParameter("project_name_v");

//String project_code_v=request.getParameter("project_code_v");
//System.out.println("project_code_v------>"+project_code_v); 

String project_QTY=request.getParameter("project_QTY");
int qty_val=Integer.parseInt(project_QTY);
System.out.println("project_QTY======"+project_QTY);

String Element_id_v=request.getParameter("Element_id");
int Element_id=Integer.parseInt(Element_id_v);

String store_status_id_v=request.getParameter("store_status_id");
int store_status_id=Integer.parseInt(store_status_id_v);

String project_element_mapping=request.getParameter("project_element_mapping_id");
int project_element_mapping_id=Integer.parseInt(project_element_mapping);

String param=store_name_v+columnSeperator+store_addr_v+columnSeperator+store_status_v +columnSeperator+ fd_hub_v+columnSeperator+
handle_by_v +columnSeperator+tsi_name_v+columnSeperator+ tsi_phone_v+columnSeperator+chain_name_v+columnSeperator+trade_name_v+columnSeperator+
state_name_v+columnSeperator+city_name_v+columnSeperator+town_name_v+columnSeperator+area_name_v+columnSeperator+store_status_id;

System.out.println("param value===for update store===>"+param);

ProjectServices services=new ProjectServices();
String result=services.update_Project_store(param,projectStoreid);

if(result.equals(Constants.DATA_UPDATED)){
	String resultVal=services.update_project_store_element_mapping(Element_id,qty_val,project_element_mapping_id);
	if(resultVal.equals(Constants.DATA_UPDATED)){
		out.println("DATA UPDATED");	
	}else{
		out.println("DATA NOT UPDATED");	
	}
	
}else
if(result.equals(Constants.DATA_NOT_UPDATED)){
		out.println("DATA NOT UPDATED");
}else
if(result.equals(Constants.DATA_INSUFFICIENT)){
		out.println("DATA INSUFFICIENT");
}		

%>