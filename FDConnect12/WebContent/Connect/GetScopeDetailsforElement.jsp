<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String Project_element = request
			.getParameter("Project_element_scope");
	System.out.println("Project_element :: " + Project_element);
	ProjectServices services = new ProjectServices();
	String getData = services
			.getScopeDetailsForElement(Project_element);
	System.out.println("GetScopedetailsforElement.jsp_______________");
	System.out.println("getData :: " + getData);
	if (getData.equals(Constants.NO_DATA)) {
%>
<table style="color: #39939C; border: 1px; width: 100%"
	id="scope_example" class="display">
	<thead>
		<tr>
			<th colspan="13"></th>
			<th colspan="4">Client Date</th>
			<th colspan="4">FD Date</th>
		</tr>

		<tr>
			<th colspan="13"></th>
			
			<th colspan="2">TARGET</th>
			<th colspan="2">ACTUAL</th>
			<th colspan="2">TARGET</th>
			<th colspan="2">ACTUAL</th>

		</tr>

		<tr>
			<th>Select</th>
			<th>Edit</th>
			<th>Country</th>
			<th>Region</th>
			<th>State</th>
			<th>Trade</th>
			<th>Chain</th>
			<th>City</th>
			<th>#Store/Qty</th>
			<th>Scope Of Work</th>
			<th>Client Approval Req</th>
			<th>FD Hub</th>
			<th>Responsible Person</th>

			<th>Start Date</th>
			<th>End Date</th>

			<th>Start Date</th>
			<th>End Date</th>

			<th>Start Date</th>
			<th>End Date</th>

			<th>Start Date</th>
			<th>End Date</th>
			<th>Scope no:</th>
		</tr>
	</thead>
</table>


<%
	} else {
%>
<table style="color: #39939C; border: 1px; width: 100%"
	id="scope_example" class="display">
	<thead>
		<tr>
			<th colspan="13"></th>
			<th colspan="4">Client Date</th>
			<th colspan="4">FD Date</th>
		</tr>

		<tr>
			<th colspan="13"></th>
			<!--<th colspan="2">National Plan</th>
												<th colspan="2">Regional Plan</th>
												<th colspan="2">National Plan</th>
												<th colspan="2">Regional Plan</th>
												-->
			<th colspan="2">TARGET</th>
			<th colspan="2">ACTUAL</th>
			<th colspan="2">TARGET</th>
			<th colspan="2">ACTUAL</th>
		</tr>

		<tr>
			<th width="1%">SELECT</th>
			<th width="1%">Edit</th>
			<th width="2%">Country</th>
			<th width="2%">Region</th>
			<th width="2%">State</th>
			<th width="2%">Trade</th>
			<th width="2%">Chain</th>
			<th width="2%">City</th>
			<th width="2%">#Store/Qty</th>
			<th width="3%">Scope Of Work</th>
			<th width="2%">Client Approval Req</th>
			<th width="2%">FD Hub</th>
			<th width="2%">Responsible Person</th>

			<th width="2%">Start Date</th>
			<th width="2%">End Date</th>

			<th width="2%">Start Date</th>
			<th width="2%">End Date</th>

			<th width="2%">Start Date</th>
			<th width="2%">End Date</th>

			<th width="2%">Start Date</th>
			<th width="2%">End Date</th>
			<th>Scope no:</th>
		</tr>
	</thead>
	<tbody>
		<%
				String dd = "";
				String mm = "";
				String yy = "";
				String clientTarStartDate = "";
				String clientTarEnddate = "";
				String clientActStartDate = "";
				String clientActEnddate = "";
				String FDTarStartDate = "";
				String FDTarEndDate = "";
				String FDActStartDate = "";
				String FDACtEndDate = "";
				String[] row_data = getData.split(Constants.rowSeperator);

				for (int i = 0; i < row_data.length; i++) {
					String[] column_data = row_data[i]
							.split(Constants.columnSeperator);
					System.out.println(column_data[12]);
		%>
		<tr class="gradeA">
			<td><input name="checkbox_scope" value="<%=column_data[0]%>"
				type="checkbox"></td>
			<td><a class="edit" href="#"
				onclick='editScope(<%=column_data[0]%>,<%=column_data[20]%>,<%=column_data[21]%>,<%=column_data[22]%>)'> <img alt="edit" src="../images/edit.png"></img></a></td>
			<td><%=column_data[1]%></td>
			<td><%=column_data[2]%></td>
			<td><%=column_data[3]%></td>
			<td><%=column_data[5]%></td>
			<td><%=column_data[6]%></td>
			<td><%=column_data[4]%></td>
			<%
				if (column_data[7].equals("0"))
							out.print("<td></td>");
						else
							out.print("<td>" + column_data[7] + "</td>");
			%>

			<td><%=column_data[8]%></td>
			<td><%=column_data[9]%></td>

			<td><%=column_data[10]%></td>
			<%
				if (column_data[11].equals("NONE"))
							out.print("<td></td>");
						else
							out.print("<td>" + column_data[11] + "</td>");
			%>

			<%
				if (column_data[12].equals("1970-01-01")){
							out.print("<td></td>");}
						else{
							dd = column_data[12].substring(8, 10);
						System.out.println("val of dd===========" + dd);

						mm = column_data[12].substring(5, 7);

						yy = column_data[12].substring(2, 4);
						clientTarStartDate = dd + "/" + mm + "/" + yy;
						out.print("<td>" + clientTarStartDate + "</td>");
						}
						if (column_data[13].equals("1970-01-01")){
							out.print("<td></td>");}
						else{
							dd = column_data[13].substring(8, 10);

						mm = column_data[13].substring(5, 7);

						yy = column_data[13].substring(2, 4);
						clientTarEnddate = dd + "/" + mm + "/" + yy;

						out.print("<td>" + clientTarEnddate + "</td>");
						}
						if (column_data[14].equals("1970-01-01")){
							out.print("<td></td>");}
						else{
							dd = column_data[14].substring(8, 10);
						mm = column_data[14].substring(5, 7);
						yy = column_data[14].substring(2, 4);
						clientActStartDate = dd + "/" + mm + "/" + yy;

						out.print("<td>" + clientActStartDate + "</td>");}

						if (column_data[15].equals("1970-01-01")){
							out.print("<td></td>");}
						else{
							dd = column_data[15].substring(8, 10);
						mm = column_data[15].substring(5, 7);
						yy = column_data[15].substring(2, 4);
						clientActEnddate = dd + "/" + mm + "/" + yy;

						out.print("<td>" + clientActEnddate + "</td>");
						}
						if (column_data[16].equals("1970-01-01")){
							out.print("<td></td>");}

						else{
							dd = column_data[16].substring(8, 10);
						mm = column_data[16].substring(5, 7);
						yy = column_data[16].substring(2, 4);
						FDTarStartDate = dd + "/" + mm + "/" + yy;
						out.print("<td>" + FDTarStartDate + "</td>");
						}
						if (column_data[17].equals("1970-01-01")){
							out.print("<td></td>");}
						else{
							dd = column_data[17].substring(8, 10);
						mm = column_data[17].substring(5, 7);
						yy = column_data[17].substring(2, 4);
						FDTarEndDate = dd + "/" + mm + "/" + yy;
						out.print("<td>" + FDTarEndDate + "</td>");
						}
						if (column_data[18].equals("1970-01-01")){
							out.print("<td></td>");}
						else{
							dd = column_data[18].substring(8, 10);
						mm = column_data[18].substring(5, 7);
						yy = column_data[18].substring(2, 4);
						FDActStartDate = dd + "/" + mm + "/" + yy;
						out.print("<td>" + FDActStartDate + "</td>");
						}
						if (column_data[19].equals("1970-01-01")){
							out.print("<td></td>");}
						else{

							dd = column_data[19].substring(8, 10);
						mm = column_data[19].substring(5, 7);
						yy = column_data[19].substring(2, 4);
						FDACtEndDate = dd + "/" + mm + "/" + yy;
						out.print("<td>" + FDACtEndDate + "</td>");}
			%>
					<td>Scope No: <%=i%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%
	}
%>
