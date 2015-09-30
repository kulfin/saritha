<html>
  <head> <link rel="stylesheet" style="text/css" href="cascadess.css"/><title>Items Detaill jsp</title>
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection" %>
<%
String bill_id=(String)session.getAttribute("billid");
%>
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
<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
	<center>
	<FORM NAME="form1" onSubmit="return check()" action="get_bill_details2.jsp" method="post">
	Enter BillID:<input type="text" name="bill_id" value=<%=bill_id%> ><br>
	<input type="submit" name="submit" value="Get BillDetails">
	<input type="reset" name="reset" value="Clear">
	</form>
	</center>

</body>
</html>