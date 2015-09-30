 
<HTML>
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

 <body background="images/bg6.jpg"><center><BR><BR>

<!--Declaration of varaibles-->

<%! String iid,iname;%>
<%! int iq,iq1;%>
    

<% 
   /*Retreiving user id and password*/
   iid=request.getParameter("iid");
	iname= request.getParameter("iname"); 
    iq=Integer.parseInt(request.getParameter("iq"));
    iq1=Integer.parseInt(request.getParameter("iq1"));
/*Declaration of variables*/

Connection con=null; Statement stmt=null;   ResultSet rs=null;
try
{
	/*Getting the connection variable from session*/
 con=DBConnection.getConnection();
	//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
	//		test","root","root"); 
	 stmt= con.createStatement(); 
String Query = 
	"Update o_item set item_quantity="+(iq1+iq)+"where item_code='"+iid+"' and item_name='"+iname+"'";
System.out.println("select update executed");
System.out.println(Query);

int rowsAffected = stmt.executeUpdate(Query);
		
	System.out.println("Rows Affected = " + rowsAffected);

if(rowsAffected==1)
	{

%>
	<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
		<H3 align="center">Item quantity Updated Successfully </H3>
	<BR>
<%
		}

}
catch(Exception e)
{
	e.printStackTrace();
}
%>
</BODY>
</HTML>




