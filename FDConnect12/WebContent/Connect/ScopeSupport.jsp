<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
System.out.println("ScopeSupport.jsp");
ProjectServices services =new ProjectServices();
String id=request.getParameter("id");
System.out.println("id-----> "+id);
String retrieve_element_scopes_date=services.retrieve_element_scope_date_plans(Integer.parseInt(id));
System.out.println("Data---->"+retrieve_element_scopes_date);
%>


<h3 style="color: #39939C;">Element Scope Work</h3>

			
<%		if(retrieve_element_scopes_date.equals(Constants.NO_DATA)){		%>
<table id="scope_example" class="display" align="center" style="width: 700 px; text-align: center;" border="1" >
		<thead>
			<tr style="color: #39939C;">
				<th colspan="2"></th>
				<th colspan="4">Client </th>
				<th colspan="4">Fourth Division</th>
			</tr>
				<tr style="color: #39939C;">
				<th colspan="2">Scope List</th>
				<th colspan="2">Planned</th>
				<th colspan="2">Actual</th>
				<th colspan="2">Planned </th>
				<th colspan="2">Actual</th>
				</tr>
			<tr style="color: #39939C;">
				<th width="14%">Scope List</th>
				<th width="6%">Hold</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Start Date</th>
				<th>End Date</th> 
				<th>Start Date</th>
				<th>End Date</th>
				<th>Start Date</th>
				<th>End Date</th> 
			</tr>
		</thead>
</table>
			
			<%
			}
			else
			{ 
			%>
	<table id="scope_example" class="display" align="center" 
	style="width: 700 px; text-align: center;" border="1" >
		<thead>
			<tr style="color: #39939C;">
				<th colspan="2"></th>
				<th colspan="4">Client </th>
				<th colspan="4">Fourth Division</th>
			</tr>
				<tr style="color: #39939C;">
				<th colspan="2">Scope List</th>
				<th colspan="2">Planned</th>
				<th colspan="2">Actual</th>
				<th colspan="2">Planned </th>
				<th colspan="2">Actual</th>
				</tr>
			<tr style="color: #39939C;">
				<th width="14%">Scope List</th>
				<th width="6%">Hold</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Start Date</th>
				<th>End Date</th> 
				<th>Start Date</th>
				<th>End Date</th>
				<th>Start Date</th>
				<th>End Date</th> 
			</tr>
		</thead>
		
		<tbody>	
			<%			
				
			String[] retrieve_element_scopes_dates=retrieve_element_scopes_date.split(Constants.rowSeperator);
			for(int j=0;j<retrieve_element_scopes_dates.length;j++)
			{
				String[] retrieve_element_scopes_dates_rows=retrieve_element_scopes_dates[j].split(Constants.columnSeperator);
				
				%>
				
				<tr>
				<td><%=retrieve_element_scopes_dates_rows[1]%></td>
				<td><%=retrieve_element_scopes_dates_rows[2]%></td>
				<td>
				<input type="text" value="<%=retrieve_element_scopes_dates_rows[3]%>"
				id="cl_plan_st_date" name="cl_plan_st_date">
				</td>
				<td>
				<input type="text" value="<%=retrieve_element_scopes_dates_rows[4]%>"
				id="cl_plan_ed_date" name="cl_plan_ed_date">
				</td>
				<td><input type="text" value="<%=retrieve_element_scopes_dates_rows[5]%>"
				id="cl_act_st_date" name="cl_act_st_date">
				</td>
				<td >
				<input type="text" value="<%=retrieve_element_scopes_dates_rows[6]%>"
				id="cl_act_ed_date" name="cl_act_ed_date">
				</td>
				<td>
				<input type="text" value="<%=retrieve_element_scopes_dates_rows[7]%>"
				id="fd_plan_st_date" name="fd_plan_st_date">
				</td>
				<td>
				<input type="text" value="<%=retrieve_element_scopes_dates_rows[8]%>"
				id="fd_plan_en_date" name="fd_plan_en_date">
				</td>
				<td>
				<input type="text" value="<%=retrieve_element_scopes_dates_rows[9]%>"
				id="fd_act_st_date" name="fd_act_st_date">
				</td> 
				<td>
				<input type="text" value="<%=retrieve_element_scopes_dates_rows[10]%>"
				id="fd_act_en_date" name="fd_act_en_date">
				</td>	
				</tr>
		
					<%
			}
			%>
		</tbody>
		</table>
			<%
			}
			
			%>

			