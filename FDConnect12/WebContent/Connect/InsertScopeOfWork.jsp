<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%

System.out.println("InsertScopeOfWork JSP");
//alert("Brand Name"+brand_name+ "  element name"+element_name+" component name"+component_name);
//alert("\n Material_name"+material_name+quantity+depot_name+print_mode_name);
String column_seperator=Constants.columnSeperator;

String brand_name=request.getParameter("brand_name");
String element_name=request.getParameter("element_name");
String component_name=request.getParameter("component_name");
String material_name=request.getParameter("material_name");
String quantity=request.getParameter("quantity");
String print_mode_name=request.getParameter("print_mode_name");
String project_id=request.getParameter("id");
String checked_item_list=request.getParameter("checkeditem");
String prjct_name=request.getParameter("prjct_name");
String set_as_hold=request.getParameter("set_as_hold");

//String prjct_id=request.getParameter("prjct_id");

//"project_id,brand_id,element_id,component_id,material_id,quantity,depot_id,print_mode_id
ProjectServices services=new ProjectServices();




String insert_scope_of_work=services.Project_scope_of_work_insert(project_id+column_seperator+brand_name+column_seperator+
		element_name+column_seperator+component_name+column_seperator+material_name+
		column_seperator+quantity+column_seperator+print_mode_name,set_as_hold,prjct_name);

if(insert_scope_of_work.equalsIgnoreCase("DATA_NOT_INSERTED")){
	
	out.println("DATA_NOT_INSERTED");
	
}else if(insert_scope_of_work.equalsIgnoreCase("DATA_INSUFFICIENT")){
	
	out.println("DATA_INSUFFICIENT");
	
}else if(insert_scope_of_work.equalsIgnoreCase("DATA_INSERTED")){
	
	out.println("DATA_INSERTED");
}
else if(insert_scope_of_work.equalsIgnoreCase("PROJECT CODE EXIST")){
	
	out.println("PROJECT CODE ALREADY EXIST");
}
%>