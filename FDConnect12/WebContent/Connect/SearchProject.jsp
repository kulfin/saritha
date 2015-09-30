<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    
<%
System.out.println("SearchProject.jsp");

String flag=request.getParameter("flag");
System.out.println("flag   --->"+flag);
ProjectServices services=new ProjectServices();

 if(flag.equals("search_project_id")){
	String search_project_name=request.getParameter("search_project_id");
	System.out.println("search_Project_id "+search_project_name);
	
	String result=services.search_Project_by_name(search_project_name);
	
	
	%>
	
	<h3 style="color:white;font-size: 17px;background-color:#e0e0e0;">Searched Result</h3> 
	
	<table class="display" id="example">
		<thead>
			<tr>
							<th>Project No</th>
							<th>Project Name</th>
							<th>Client</th>
							<th>Division</th>		
			</tr>
		</thead>
		<tbody>	
		<% if(result.equalsIgnoreCase(Constants.NO_DATA)){ %>					
						
							<!-- <tr class="gradeX">
								<td></td>
								<td></td>
								
								<td><font color="#FF0000"> NO Project</font></td>
								<td></td>
							</tr> -->
							
			<%
			} else { 
			
			%> 
			
			<%
			String[] list_project=result.split(Constants.columnSeperator);
			
			%>			
					<tr class="gradeA" onclick="open_details(<%=list_project[4]%>);">
									<td><%=list_project[0]%></td>
									<td><%=list_project[1]%></td>
									<td><%=list_project[2]%></td>
									<td><%=list_project[3]%></td>
									
					</tr>			
				
			 	<%
			}
				%>	
			</tbody>
		</table>	
	<%
			
}
 
else if(flag.equals("search_client")){
	String search_client_name=request.getParameter("search_client");
	System.out.println("search_client "+search_client_name);
	
	String result=services.search_Project_by_clientname(search_client_name);
	//System.out.println("result"+result);
	
	%>
 	<h3 style="color:white;font-size: 17px;background-color:#e0e0e0;">Searched Result</h3> 
	
		<table class="display" id="example">
					<thead>
								<tr>
									<th>Project No</th>
									<th>Project Name</th>
									<th>Client</th>
									<th>Division</th>
								</tr>
					</thead>
					
						
				<%if(result.equalsIgnoreCase(Constants.NO_DATA)){ %>					
					<tbody>
							<!-- <tr>
								<font color="#FF0000"> NO Project</font>
							</tr> -->
					</tbody>
		<%} else { 
			System.out.println("DATA in search_Project_by_clientname");
			
			String[] list_project=result.split(Constants.rowSeperator);
		%>
		<tbody>
		
		<%	
			for(int i=0;i<list_project.length;i++){
				String[] row_data=list_project[i].split(Constants.columnSeperator);
				System.out.println("list "+list_project[i]);
		
		%>					
						<tr class="gradeA" onclick="open_details(<%=row_data[4]%>);">
							<td><%=row_data[0]%></td>
							<td><%=row_data[1]%></td>
							<td><%=row_data[2]%></td>
							<td><%=row_data[3]%></td>
						</tr>			
		<%
			}
		%>	
		</tbody>				
			

<%
		}
	%>
	</table>

<%
}
else if(flag.equals("search_division")){
	
	String search_division_name=request.getParameter("search_division");
	System.out.println("search_division "+search_division_name);
	String result=services.search_Project_by_divisionname(search_division_name);
	//System.out.println("result"+result);
	
	%>
	<h3 style="color:white;font-size: 17px;background-color:#e0e0e0;">Searched Result</h3>
	<table class="display" id="example">
					<thead>
								<tr>
									<th>Project No</th>
									<th>Project Name</th>
									<th>Client</th>
									<th>Division</th>
								</tr>
					</thead>
				
				<%if(result.equalsIgnoreCase(Constants.NO_DATA)){ %>					
					<tbody>	
						<!-- 	<tr>
								<font color="#FF0000"> NO Project</font>
							</tr> -->
					</tbody>
		<%} else { 
		
			String[] list_project=result.split(Constants.rowSeperator);
			
			%>
			<tbody>
			<%
			for(int i=0;i<list_project.length;i++){
				String[] row_data=list_project[i].split(Constants.columnSeperator);
				System.out.println("list "+ list_project[i]);
		%>					
							<tr class="gradeA" onclick="open_details(<%=row_data[4]%>);">
								<td><%=row_data[0]%></td>
								<td><%=row_data[1]%></td>
								<td><%=row_data[2]%></td>
								<td><%=row_data[3]%></td>
							</tr>			
			<%
			}
			%>	
			<tbody>			
		

<%
		}
%>
				</table>	
<%
				
}
%>