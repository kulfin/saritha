 
<HTML><head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>

<%@ page language="java" %>
<%@ page import="com.DBConnection" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

 <body background="images/bg6.jpg"><center><BR><BR>

<!--Declaration of varaibles-->

<%! String cat ; String item; String nprice ;%>
    

<% 
   /*Retreiving user id and password*/
    cat= request.getParameter("cat"); 
    item=request.getParameter("item");
    nprice=request.getParameter("price");
/*Declaration of variables*/

Connection con=null; Statement stmt=null;   ResultSet rs=null;
try
{
	/*Getting the connection variable from session*/

 con=DBConnection.getConnection();
	//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
	//		test","root","root"); 
	 stmt= con.createStatement(); 
String Query = "SELECT * from o_item where category_code='"+cat+"' and item_code='"+item+"'";
System.out.println("select statement executed");
System.out.println(Query);

rs = stmt.executeQuery(Query);
		
/* If user provides valid username & password then update the new password to database*/

if(rs!=null)
{
String UpdateQuery = 
"Update o_item set item_price='"+nprice+"' where category_code='"+cat+"' and item_code='"+item+"'";
System.out.println(UpdateQuery);
	int rowsAffected=stmt.executeUpdate(UpdateQuery);	
	
	System.out.println("Rows Affected = " + rowsAffected);

if(rowsAffected==1)
	{

%>
	<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
		<H3 align="center">Item Price Updated Successfully </H3>
	<BR>
	<center>
		<A href="changepriceofitem.jsp"> Back </A>
	</center>
<%
		}



/* If user provides invalid category/item  */
else{
	
%>
	<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
		<H3 align="center">category/item code Invalid Please Try again </H3>
	<BR>
	<center>
		<A href="changepriceofitem.jsp"> Back </A>
	</center>
<%
}
}
}
catch(Exception e)
{
	e.printStackTrace();
}

%>
</BODY>
</HTML>




