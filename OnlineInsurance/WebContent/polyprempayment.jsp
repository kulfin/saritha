<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="./PremiumTX">
  <p>
    <%! Connection con;
ResultSet rs;
CallableStatement cstmt;
String cid;
int cupid;

%>
    <%
	System.out.println("Premium Payment jspform  ");
	
	
System.out.println(" before parametere              custpolicyyidddddd  :::  ");

String cid=request.getParameter("cpyid").trim();
System.out.println(" custpolicyyidddddd  :::  "+cid);
//session.setAttribute("CUSTPYID",cid);

cupid=Integer.parseInt(cid);

java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

Statement st=null;

con=DBConn.getConnection();
//st=con.createStatement();
//rs=st.executeQuery
st=con.createStatement();
cstmt=con.prepareCall("{call dues(?,?,?,?,?)}");
cstmt.registerOutParameter(2,Types.INTEGER);
cstmt.registerOutParameter(3,Types.INTEGER);
cstmt.registerOutParameter(4,Types.INTEGER);
cstmt.registerOutParameter(5,Types.DATE);
cstmt.setInt(1,cupid);
cstmt.execute();

System.out.println(" cstmtttttt values ");
System.out.println( "after cstmtttttt");

rs=st.executeQuery("select c.custid,c.policyid,c.premiumtype,c.premiumamount,c.expirydate,d.nextpremiumdate from custpolicies c,custpremiumdates d where d.custpolicyid=c.custpolicyid and  c.custpolicyid=" + cupid);

%>
  </p>
  <p align="left"> <strong>Date:</strong> 
    <input name="bdate" type="text" id="bdate4" value="<%=d2%>" readonly="true">
  </p>
   <p align="left"> <strong>CustPolicyID:</strong> 
    <input name="cupid" type="text" id="cupid" value="<%=cupid%>" readonly="true">
  </p>
  <p align="center"><font color="#CC0099" size="6" face="Monotype Corsiva"><em><strong>Customer 
    Premium Amount Payment Form</strong></em></font></p>
  <table width="987" height="63" border="2" cellpadding="3">
    <tr> 
      <td width="56"><div align="center"><strong><em>CustID</em></strong></div></td>
      <td width="77" height="59"> <div align="center"><strong><em>PolicyID</em></strong></div></td>
      <td width="120"><p align="center"><strong><em>Premium Type</em></strong></p></td>
      <td width="97"><div align="center"><strong><em>Premium Amount</em></strong></div></td>
      <td width="76"><div align="center"><strong><em>Due</em></strong></div></td>
      <td width="112"><div align="center"><strong><em>Total Premium Amount</em></strong></div></td>
      <td width="124"><div align="center"><strong><em>Net Amount</em></strong></div></td>
      <td width="106"><div align="center"><strong><em>Next Premium Date</em></strong></div></td>
      <td width="123"><div align="center"><strong><em><strong>Actual Premium Date</strong></em> 
          </strong></div></td>
      <td width="123"><div align="center"><em><strong><em>Policy Expiry Date</em></strong></em></div></td>
    </tr>
  </table>
  <% while(rs.next())
  {
  %>
  <table width="986" height="49" border="0" cellpadding="3">
    <tr> 
      <td width="73" height="45"><%=rs.getInt(1)%></td>
      <td width="82"><%=rs.getInt(2)%></td>
      <td width="127"><%=rs.getString(3)%></td>
      <td width="103"><%=rs.getInt(4)%></td>
      <td width="93"><%=cstmt.getInt(2)%></td>
      <td width="114"><%=cstmt.getInt(3)%></td>
      <td width="113"><%=cstmt.getInt(4)%></td>
      <td width="112"><%=cstmt.getDate(5)%></td>
      <td width="95"><%=rs.getDate(6)%></td>
      <td width="95"><%=rs.getDate(5)%></td>
    </tr>
    <% }%>
  </table>
  <p>&nbsp;</p>
  <table width="321" border="0" align="center" cellpadding="3">
 
    <tr>
      <td width="128"><div align="center"><strong><em>Amount Paid </em></strong></div></td>
      <td width="175"><input name="paidamt" type="text" id="paidamt"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="399" height="42" border="0" align="center" cellpadding="3">
    <tr> 
      <td height="34"> <div align="center"> 
          <input type="submit" name="Submit" value="Submit">
        </div></td>
      <td><div align="center"> 
          <input type="submit" name="Submit2" value="Reset">
        </div></td>
    </tr>
  </table>
  <p>.</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
