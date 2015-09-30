<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%! String jobCardNo=null;  %>
<%! String jobCardDate=null;  %>
<%! String id=null; %>
<%! int i=0; %>
<%! int project_element_mapping_id=0; %>
<% 
String id=request.getParameter("id");
ProjectServices projectServices=new ProjectServices();
String list_services=projectServices.getProjectStores(id);
System.out.println("list_stores :: "+list_services);
%>

	<%
		if (list_services.equalsIgnoreCase(Constants.NO_DATA)) {
								System.out.println("DATA EMPTY");
						%>
<table id="area_detail_table1"
	style="word-spacing: .5px; width: 100%; font-weight: normal; text-align: center; background-color: rgb(255, 255, 255)"
	class="display">
				<thead>
					<tr style="color: #39939C">
						<th>Select</th>
						<th>Edit</th>
						<th>Store</th>
						<th>Address</th>
						<th>State</th>
						<th>City</th>
						<th>JobCard No</th>
						<th>JobCard Date</th>
						<th>Element Name</th>
						<th>Qty/Store</th>
						<th>Store Status</th>
						<th>Hub</th>
						<th>Handled By</th>
						<th>TSI Name</th>
						<th>TSI Phone</th>
					
					
					</tr>
				<thead>
</table>
	
	<%
	}
	else{
	%>	
		<table id="example" class="display"
	style="word-spacing: .5px; width: 100%; text-align: left; font-weight: normal; background-color: rgb(255, 255, 255); border: none;">
				<thead>
					<tr style="color: #39939C">
						<th>Select</th>
						<th>Edit</th>
						<th>Store</th>
						<th>Address</th>
						<th>State</th>
						<th>City</th>
						<th>JobCard No</th>
						<th>JobCard Date</th>
						<th>Element Name</th>
						<th>Qty/Store</th>
						<th>Store Status</th>
						<th>Hub</th>
						<th>Handled By</th>
						<th>TSI Name</th>
						<th>TSI Phone</th>
			
					</tr>
				</thead>
	<tbody>
		<%
									String[] rowStore = list_services.split(Constants.rowSeperator);
									
										for (i = 0; i < rowStore.length; i++) {
											String[] columnSeperator = rowStore[i]
													.split(Constants.columnSeperator);
											if((columnSeperator[1]).equalsIgnoreCase("null") ||(columnSeperator[1]).equalsIgnoreCase("") ){
												columnSeperator[1]="NA";
											}
											if((columnSeperator[2]).equalsIgnoreCase("null") ||(columnSeperator[2]).equalsIgnoreCase("") ){
												columnSeperator[2]="NA";
											}
											if((columnSeperator[3]).equalsIgnoreCase("null") ||(columnSeperator[3]).equalsIgnoreCase("") ){
												columnSeperator[3]="NA";
											}
											if (columnSeperator[4].equalsIgnoreCase("empty") ||(columnSeperator[4]).equalsIgnoreCase("null") ) {
												columnSeperator[4] = "NA";
											}
											if (columnSeperator[5].equalsIgnoreCase("empty") ||(columnSeperator[5]).equalsIgnoreCase("null")) {
												columnSeperator[5] = "NA";
											}
											if (columnSeperator[6].equalsIgnoreCase("0000000000") || columnSeperator[6].equalsIgnoreCase("null")) {
												columnSeperator[6] = "NA";
											}
											if((columnSeperator[7]).equalsIgnoreCase("null") ||(columnSeperator[7]).equalsIgnoreCase("") ){
												columnSeperator[7]="NA";
											}
											if((columnSeperator[9]).equalsIgnoreCase("null") ||(columnSeperator[9]).equalsIgnoreCase("") ){
												columnSeperator[9]="NA";
											}
											if((columnSeperator[10]).equalsIgnoreCase("null") ||(columnSeperator[10]).equalsIgnoreCase("") ){
												columnSeperator[10]="NA";
											}
											if((columnSeperator[11]).equalsIgnoreCase("null") ||(columnSeperator[11]).equalsIgnoreCase("") ){
												columnSeperator[11]="NA";
											}
											if((columnSeperator[12]).equalsIgnoreCase("null") ||(columnSeperator[12]).equalsIgnoreCase("") ){
												columnSeperator[12]="NA";
											}
											if((columnSeperator[13]).equalsIgnoreCase("null") ||(columnSeperator[13]).equalsIgnoreCase("") ){
												columnSeperator[13]="NA";
											}
											if((columnSeperator[14]).equalsIgnoreCase("null") ||(columnSeperator[14]).equalsIgnoreCase("") ){
												columnSeperator[14]="NA";
											}
											if((columnSeperator[15]).equalsIgnoreCase("null") ||(columnSeperator[15]).equalsIgnoreCase("") ){
												columnSeperator[15]="NA";
											}
											if((columnSeperator[16]).equalsIgnoreCase("null") ||(columnSeperator[16]).equalsIgnoreCase("") ){
												columnSeperator[16]="NA";
											}
											
										//	if((columnSeperator[19]).equalsIgnoreCase("null") ||(columnSeperator[19]).equalsIgnoreCase("") ){
										//		columnSeperator[19]="NA";
										//	}
											if((columnSeperator[19]).equalsIgnoreCase("null") ||(columnSeperator[19]).equalsIgnoreCase("") ){
												columnSeperator[19]="NA";
											}
											if((columnSeperator[20]).equalsIgnoreCase("null") ||(columnSeperator[20]).equalsIgnoreCase("") ){
												columnSeperator[20]="NA";
											}
											
											 jobCardNo=columnSeperator[17];
											 jobCardDate=columnSeperator[18];
											 if(jobCardDate.equals("NA")){
												 jobCardDate="NA";
											 }else{
											 	jobCardDate=jobCardDate.substring(0,10);
											 }
											 
											// project_element_mapping_id=Integer.parseInt(columnSeperator[21]);
								%>

		<tr id="<%=columnSeperator[8]%>" class="gradeA">
			<td><input name="chkbox" id=chkbox
				value="<%=columnSeperator[8]%>" type="checkbox"></td>
			<td><a class="edit" href=""
				onclick='return editRecordNew(
			&apos;"+<%=columnSeperator[0]%>+"&apos;,
			&apos;"+<%=columnSeperator[1]%>+"&apos;,&apos;"+<%=columnSeperator[2]%>+"&apos;,
			&apos;"+<%=columnSeperator[3]%>+"&apos;,&apos;"+<%=columnSeperator[4]%>+"&apos;,
			&apos;"+<%=columnSeperator[5]%>+"&apos;,&apos;"+<%=columnSeperator[6]%>+"&apos;,
			&apos;"+<%=columnSeperator[7]%>+"&apos;,&apos;"+<%=columnSeperator[8]%>+"&apos;,
			&apos;"+<%=columnSeperator[9]%>+"&apos;,&apos;"+<%=columnSeperator[10]%>+"&apos;,
			&apos;"+<%=columnSeperator[11]%>+"&apos;,&apos;"+<%=columnSeperator[12]%>+"&apos;,
			&apos;"+<%=columnSeperator[13]%>+"&apos;,&apos;"+<%=columnSeperator[14]%>+"&apos;,
			&apos;"+<%=columnSeperator[15]%>+"&apos;,&apos;"+<%=columnSeperator[16]%>+"&apos;,
			&apos;"+<%=columnSeperator[17]%>+"&apos;,&apos;"+<%=jobCardDate%>+"&apos;,
			&apos;"+<%=columnSeperator[21]%>+"&apos;);'>
			<img alt="edit" src="../images/edit.png"></img></a></td>
			<%-- <td><%=row_list[0] %></td> --%>
								<td><%=columnSeperator[1]%></td><%--store name--%>
								<td><%=columnSeperator[2]%></td><%--address --%>
								<td><%=columnSeperator[11]%></td><%--state name --%>
								<td><%=columnSeperator[12]%></td><%-- city name--%>
								<td><%=columnSeperator[17]%></td><%--job card no --%>
								<td><%=jobCardDate%></td><%--job card date --%>
								<td><%=columnSeperator[19]%></td><%--project name--%>
								<td><%=columnSeperator[20]%></td><%--quantity --%>
								<td><%=columnSeperator[7]%></td><%--status --%>
								<td><%=columnSeperator[3]%></td><%--fd hub --%>
								<td><%=columnSeperator[4]%></td><%-- handle by--%>
								<td><%=columnSeperator[5]%></td><%--tsi name --%>
								<td><%=columnSeperator[6]%></td><%-- tsi phone--%>
								
			</tr>

		<%
		}
	%>
	</tbody>
</table>
	<%
	}
	%>		

