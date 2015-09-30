<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<style>
<!--
#td_id{
	size:2;
	color: #39939C;
}
-->
</style>

<%
System.out.println("GetProjectStoreDetails.jsp");
String project_id = request.getParameter("project_id");
System.out.println("Project ID---- > "+project_id);
String areaSelect = request.getParameter("areaSelect");
System.out.println("areaSelect---- > "+areaSelect);
ProjectServices projectServices=new ProjectServices();
String list_services=projectServices.list_Project_store(project_id,areaSelect);
System.out.println("list_services"+list_services);
%>
<form action="#" name="myDisplayForm">	

		<%
		if(list_services.equals(Constants.NO_DATA)){
		%>	
		
		<div style="margin-left: -1080px;margin-top: 15px;">
				<input style="width: 117px;" type="button" value="Delete"
						onclick="deleteStore(<%=project_id%>)" >	
		</div>
		<table id="area_detail_table" style="word-spacing: .5px;width:100%;text-align: center;">
		<tr style="font-weight: bold;">
			<td id="td_id">Select</td>
			<td id="td_id">Edit</td>
			<!-- <td>Project</td> -->
			<td id="td_id">Store</td>
			<td id="td_id">Address</td>
			<td id="td_id">Hub</td>
			<td id="td_id">Handle By</td>
			<td id="td_id">TSI Name</td>
			<td id="td_id">TSI Phone</td>
			<td id="td_id">Status</td>
		</tr>
		</table>
		<font style="color: #FF0000;padding-left:220px;" >NO DATA</font>
		
		<%
} else {
	String[] data_list=list_services.split(Constants.rowSeperator);
	%>
	<div style="margin-left: -450px;margin-top: 15px;">
				<input style="width: 117px;" type="button" value="Delete"
						onclick="deleteStore(<%=project_id%>)" >	
	</div>
	
	<%-- <input style="width: 140px;" type="button" value="Delete" onclick="deleteStore(<%=project_id%>)"> --%>
	<table id="area_detail_table" style="word-spacing: .5px;width:100%;text-align: center;">
		<tr style="font-weight: bold;">
			<td id="td_id">Select</td>
			<td id="td_id">Edit</td>
			<!-- <td>Project</td> -->
			<td id="td_id">Store</td>
			<td id="td_id">Address</td>
			<td id="td_id">Hub</td>
			<td id="td_id">Handle By</td>
			<td id="td_id">TSI Name</td>
			<td id="td_id">TSI Phone</td>
			<td id="td_id">Status</td>
		</tr>
	
	<%
	for(int i=0;i<data_list.length;i++){
		String[] row_list=data_list[i].split(Constants.columnSeperator);
		//System.out.println(i+"    "+row_list[9]);
%>	
		

		<tr id="<%=row_list[8]%>">
			<td><input name="chkbox" value="<%=row_list[8]%>" type="checkbox"></td>
			<td><img src="../images/edit.png" onclick="editRecord('<%=row_list[0]%>',
			'<%=row_list[1]%>','<%=row_list[2]%>','<%=row_list[3]%>','<%=row_list[4]%>',
			'<%=row_list[5]%>','<%=row_list[6]%>','<%=row_list[7]%>','<%=row_list[8]%>')"></td>
			<%-- <td><%=row_list[0] %></td> --%>
			<td><%=row_list[1]%></td>
			<td><%=row_list[2]%></td>
			<td><%=row_list[3]%></td>
			<td><%=row_list[4]%></td>
			<td><%=row_list[5]%></td>
			<td><%=row_list[6]%></td>
			<td><%=row_list[7]%></td>
			
			
			
				
		</tr>
<%
	}
}
%>	
</table>
</form>		