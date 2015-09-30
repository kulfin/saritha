<html>
<base target="_self">

<body bgcolor=#FFFFFF>
<%@ page import="java.sql.*"%>
<%
 
try
{
	Connection con=com.DBConnection.getConnection();
%>
<% 
	String uname=request.getParameter("uname");
    String s2=request.getParameter("password");
   
  session.setAttribute("uname",uname);

   Statement st=con.createStatement();
   Statement st1=con.createStatement();
   
   ResultSet rs=st.executeQuery("select * from logindetails where UNAME='"+uname+"' and PASSWORD='"+s2+"'");

   if(rs.next())
	{
	   String s3= rs.getString(3);
	   
		if(s3.equals("admin"))
		  {
			response.sendRedirect("admin.jsp ");
		  }
	}


ResultSet rs1=st1.executeQuery("select * from consumer where UNAME='"+uname+"' and PASS='"+s2+"'");
if(rs1.next())
	{
response.sendRedirect("consumer.jsp");
	}
	else 
   {
    response.sendRedirect("error.html");
   }
}
catch(Exception e)
{
System.out.println(e);
}
%>
</body>
                                                                                        </html>