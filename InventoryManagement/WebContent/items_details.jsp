 <html>
  <head><link rel="stylesheet" style="text/css" href="cascadess.css"/><title>Items Detaill jsp</title>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<script language="javascript">
function check()
{
	var billid=document.form1.bill_id.value;
	if(billid=="")
	{
		alert("billid is mandantory");
		document.form1.bill_id.focus();
		return false;
			}
			return;
	}
</script>
</head>
<body background="images/bg6.jpg">
<%
Connection con=null;
 Statement stmt=null;
 ResultSet rs=null;
try{
	 con=DBConnection.getConnection();
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
		//		test","root","root"); 
		 stmt= con.createStatement(); 
	String selq1="select item_code from o_item where item_quantity>="+1;
	rs=stmt.executeQuery(selq1);%>
	<center>Available Items Are(Select from these Items)
	<br>
	<%while(rs.next()){%>
	
	<FORM NAME="form1" onSubmit="return check()" action="do_billing.jsp" method="post">
	<input type="checkbox" name="item_code" value='<%=rs.getString(1)%>'><%=rs.getString(1)%><br>
	<%}%>
	Enter BillID:<input type="text" name="bill_id" ><br>
	<input type="submit" name="submit" value="Do Bill">
	<input type="reset" name="reset" value="Clear">
	</form><br><BR>
	
	</center>

	<%}catch(Exception e)
{
	//System.out.println("Exception"+e);
}
%>
</body>
</html>

		
