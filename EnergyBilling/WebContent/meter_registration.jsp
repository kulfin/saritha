<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font face="AC"><b><a style="text-decoration: none" href="select_circle_name.jsp">
<font color="#000000" size="7">Back</font></a></a></b></font></p>


<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>
<%
con=com.DBConnection.getConnection();
		
	String cname = request.getParameter("cname").trim();
	String dname = request.getParameter("dname").trim();
	String mno = request.getParameter("mno");
	String mcname = request.getParameter("mcname");
	st=con.createStatement();

	if(cname.equals(null) || dname.equals(null) || mno.equals(null) || mcname.equals(null))
	{
		out.println(" Please check the fields ! Some fields are empty"); 
	}
	else
	{

rs=st.executeQuery("select * from meter where mno="+mno+"");
			
	if(!rs.next())
		{	
int i = st.executeUpdate("insert into meter values('"+cname+"','"+dname+"',"+mno+",'"+mcname+"')");

//out.println("<font color=blue size=4><b><center><b><h1>Meter Added");
//	out.println("<p><h1><font color=blue><b><center><b><center>Thanks</p>");
if(i == 1)
	response.sendRedirect("select_circle_name.jsp");
%>
  
    
<%
		}
		else
		{
			out.println("<h1><font color=red><b><center><b> Please Check Meter Id ");
		}
	}%>