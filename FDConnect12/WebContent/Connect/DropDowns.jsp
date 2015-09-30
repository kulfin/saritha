
<%@page import="com.fd.Connect.DropDown"%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%
System.out.println("DropDowns Jsp");
DropDown projectServices=new DropDown();
String param=request.getParameter("param");
System.out.println("inside drop down"+param);



if(param.equals("regionwithID")){

						String country=request.getParameter("country");
					
						String dropvalue=projectServices.drop_down_region_against_country(country);
						if(dropvalue.equals(Constants.NO_DATA))
						{
							out.println("NO_DATA");
						}
						else{
							out.println(dropvalue);
						}
						

	}
else
	if(param.equals("statewithID")){

		String region=request.getParameter("region");
	
		String dropvalue=projectServices.drop_down_state_against_region(region);
		
		if(dropvalue.equals(Constants.NO_DATA))
		{
			out.println("NO_DATA");
		}
		else{
			out.println(dropvalue);
		}
		

}
else
		if(param.equals("citywithID")){

			String state=request.getParameter("state");
		
			String dropvalue=projectServices.drop_down_city_against_state(state);

			if(dropvalue.equals(Constants.NO_DATA))
			{
				out.println("NO_DATA");
			}
			else{
				out.println(dropvalue);
			}
			

	}


else	
if(param.equals("region")){
	
%>

		<select id="Region_id" name="Region_id">
					<%
					System.out.println("inside region");
					
					String dropvalue=projectServices.drop_down_region();
					String[] resultemp=dropvalue.split(Constants.columnSeperator);
					for(int i=0;i<resultemp.length;i++)
					{
						if(resultemp[i].equals(""))
						{}
						else
						{	
							System.out.println("Drop Down ::"+resultemp[i]);
							%>
							<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
						<%
						}
					}
					%>
					</select>
<%
}
else if(param.equals("state")){
	%>

	<select id="State_id" name="State_id">
				<%
				System.out.println("inside State");
				
				String dropvalue=projectServices.drop_down_state();
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
				</select>
<%
}
else 	if(param.equals("trade")){
	%>

	<select id="Trade_id" name="Trade_id">
				<%
				System.out.println("inside Trade");
				
				String dropvalue=projectServices.drop_down_trade();
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
				</select>
<%
} else 	if(param.equals("chain")){
	%>

	<select id="Chain_id" name="Chain_id">
				<%
				System.out.println("inside Chain");
				
				String dropvalue=projectServices.drop_down_chain();
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
				</select>
<%
}
else 	if(param.equals("city")){
	%>

	<select id="City_id" name="City_id">
				<%
				System.out.println("inside Chain");
				
				String dropvalue=projectServices.drop_down_city();
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
				</select>
<%
} else 	if(param.equals("fdhub")){
	%>

	<select id="td_fd_hub_list" name="td_fd_hub_list" style="width: 155px;"> 
				<%
				System.out.println("inside Chain");
				
				String dropvalue=projectServices.drop_down_fdhub();
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
	</select>
<%
} else 	if(param.equals("fdemp")){
	%>

	<select id="td_fd_hub_list" name="td_fd_hub_list" style="width: 155px;">
				<%
				System.out.println("inside Chain");
				
				String dropvalue=projectServices.drop_down_fdemp();
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
				</select>
<%
}
else if(param.equals("measurementStatus")){
	%>

	<select style="width: 156px;" id="measure_status_u" name="measure_status_u" >
		<option value="select"> Select</option>
				<%
				System.out.println("inside measurementStatus");
				String dropvalue=projectServices.dropDownMeasurementStatus();
				String[] rowData=dropvalue.split(Constants.rowSeperator);
				
				
				for(int i=0;i<rowData.length;i++)
				{
					String columnData[]=rowData[i].split(Constants.columnSeperator);
					out.println("<option value="+columnData[0]+">"+columnData[1]+"</option>");
				}
				%>
				</select>
<%

}
else if(param.equals("heightDropDown")){
	%>

	<select id="unit_h_w_u" name="unit_h_w_u" style="width:75px;">
				<%
				System.out.println("inside heightDropDown");
				String dropvalue=projectServices.dropDownUnitMaster();
				System.out.println(" this is drop down .jsp and values are "+dropvalue);
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
				</select>
<%

}
else if(param.equals("depthDropDown")){
	%>

	<select id="unit_d_u" name="unit_d_u" style="width:75px;">
				<%
				System.out.println("inside depthDropDown");
				String dropvalue=projectServices.dropDownUnitMasterTemp();
				String[] resultemp=dropvalue.split(Constants.columnSeperator);
				for(int i=0;i<resultemp.length;i++)
				{
					if(resultemp[i].equals(""))
					{}
					else
					{	
						//System.out.println("Drop Down ::"+resultemp[i]);
						%>
						<option value="<%=resultemp[i]%>"><%=resultemp[i]%></option>
					<%
					}
				}
				%>
				</select>
<%

}

%>
