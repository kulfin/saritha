<html>
  <head><link rel="stylesheet" style="text/css" href="cascadess.css"/><title>Items Detaill jsp</title>
<%@ page language="java" %>
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
<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
	<center>
	<FORM NAME="form1" onSubmit="return check()" action="cancel_bill.jsp" method="post">
	Enter BillID:<input type="text" name="bill_id" ><br>
	<input type="submit" name="submit" value="Cancel Bill">
	<input type="reset" name="reset" value="Clear">
	</form>
	</center>

</body>
</html>