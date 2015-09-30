<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font face="AC"><b><a style="text-decoration: none" href="division_details.jsp">
<font color="#000000" size="7">Back</font></a></a></b></font></p>


<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>
<%
con=com.DBConnection.getConnection();
		
	String nid = request.getParameter("idno");
	String cname = request.getParameter("cname");
	String dname = request.getParameter("dname");
	st=con.createStatement();

rs=st.executeQuery("select * from division where didno="+nid+"");
			
	if(!rs.next())
		{	
int i =st.executeUpdate("insert into division values("+nid+",'"+cname+"','"+dname+"')");

//out.println("<font color=blue size=4><b><center><b><h1>Division Added");
//	out.println("<p><h1><font color=blue><b><center><b><center>Thanks</p>");

if(i==1)
	response.sendRedirect("division_new.jsp");
%>
  
    
<%
		}
		else
		{
			out.println("<h1><font color=red><b><center><b> Please Check Circle Id ");
		}
	%>