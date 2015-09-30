<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="">
  <p align="left"> 
    <%! Connection con;
ResultSet rs;
%>
    <% System.out.println("custall**********************premdates*******************************");
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

String obj=(String)session.getAttribute("UID");
int uid=Integer.parseInt(obj);
System.out.println("this is custpolyset.jsp and customer id is...."+uid);

String pid=request.getParameter("bid").trim();

Statement st=null;

con=DBConn.getConnection();
st=con.createStatement();
rs=st.executeQuery("select * from custpremiumdates where custpolicyid=(select custpolicyid from custpolicies where custid="+ uid + " and policyid="+pid + ")");

System.out.println("in the custall html"+rs+ "   sop");
%>
    <strong>Date:</strong> 
    <input name="bdate" type="text" id="bdate4" value="<%=d2%>" readonly="true">
  </p>
  <p align="center"><font color="#CC0099" size="6" face="Monotype Corsiva"><em><strong>Customer 
    Policy Premium Dates</strong></em></font></p>
  <div align="center">
<p>&nbsp;</p>
    <table width="632" height="64" border="2" cellpadding="3">
      <tr> 
        <td width="150" height="56"> <div align="center"><font size="3"><em><strong>CustPolicyID</strong></em></font></div></td>
        <td width="219"><div align="center"><font size="3"><em><strong>Previous Premium Date</strong></em></font></div></td>
        <td width="227"><div align="center"><font size="3"><em><strong>Next Premium Date</strong></em></font></div></td>
      </tr>
         </table>
    <table width="556" height="46" border="0" cellpadding="3">
      <% while(rs.next())
	  {%>
      <tr> 
        <td width="164" height="38"><%=rs.getInt(1)%><div align="center"></div></td>
        <td width="218"><%=rs.getDate(2)%> <div align="center"></div></td>
        <td width="148"><%=rs.getDate(3)%> <div align="center"></div></td>
      </tr>
      <% }
	  %>
    </table>
    <p align="left">&nbsp;</p>
    <p>&nbsp;</p>
    <div align="center"></div>
    
  </div>
  
</form>
</body>
</html>
