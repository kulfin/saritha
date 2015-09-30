

<%
String type = request.getParameter("type");
out.println(type);

if (type.equals("commercial"))
{
	response.sendRedirect("commercial_bill.jsp");
}
else
if(type.equals("residential"))
{
	response.sendRedirect("residential_bill.jsp");
}
%>