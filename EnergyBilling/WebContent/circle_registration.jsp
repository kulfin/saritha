<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font face="AC"><b><a style="text-decoration: none" href="circle_new.jsp">
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
	String nname = request.getParameter("name");

	st=con.createStatement();

rs=st.executeQuery("select * from circle where idno="+nid+"");
			
	if(!rs.next())
		{	
int i = st.executeUpdate("insert into circle values("+nid+",'"+nname+"')");


if(i==1)
	response.sendRedirect("circle_new.jsp");

%>
  
    
<%
		}
		else
		{
			out.println("<h1><font color=red><b><center><b> Please Check Circle Id ");
		}
	%>