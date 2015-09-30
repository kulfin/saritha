<html>
<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

<head><link rel="stylesheet" style="text/css" href="cascadess.css"/>
</head>
<body background="images/bg6.jpg">
<%

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String EMPName = request.getParameter("EMPName");
String EmpNo = request.getParameter("EMPNO");
int EMPNO= Integer.parseInt(EmpNo);
String EmpEmailID = request.getParameter("EmpEmailID");
String CurrentLocation=request.getParameter("CurrentLocation");
String strJoiningDateYYYY=request.getParameter("JoiningDateYYYY");
int JoiningDateYYYY= Integer.parseInt(strJoiningDateYYYY);
String strJoiningDateMM=request.getParameter("JoiningDateMM");
String strJoiningDateDD=request.getParameter("JoiningDateDD");
int JoiningDateDD= Integer.parseInt(strJoiningDateDD);
String strJoiningDate=JoiningDateDD+"-"+strJoiningDateMM+"-"+JoiningDateYYYY;
String Role=request.getParameter("Role");

try
{

	 con=DBConnection.getConnection();
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
		//		test","root","root"); 
		 stmt= con.createStatement(); 
	String SelQ = "Select eno from o_emp where eno="+EMPNO;
	System.out.println(SelQ);
	rs=stmt.executeQuery(SelQ);
	System.out.println(SelQ);
if(!(rs.next())){
System.out.println("hello");
String Query = "insert into o_emp values(\'"+EMPName+"\',"+EMPNO+",\'"+EmpEmailID+"\',\'"+CurrentLocation+"\',\'"+strJoiningDate+"\',\'"+Role+"\')";
	
	System.out.println(Query);
	int rowsAffected=stmt.executeUpdate(Query);		
%>
	
	<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
		<H3 align="center">Details Updated Successfully </H3>
	<BR><center><a href="AddNewEmployee.jsp">back</a></center>
	
	<%}
else{
%>
<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
	<H3 align="center">Sorry Employee already exists in the databases</H3>
	<BR><center><a href="AddNewEmployee.jsp">back</a></center>
	
	<%}	
}
catch(Exception e)
{

	e.printStackTrace();}
%>

</body>
</html>