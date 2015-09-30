<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Apna Life Insurance</title>
</head><body bgcolor="#A3A3D1">
<form name="form1" method="post" action="./polybonusdetails.jsp" target="mainFrame">
  <p> 
  
<%! Connection con;
ResultSet rs;
%>
    <% System.out.println("custall*****************************************************");
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

String obj=(String)session.getAttribute("UID");
int uid=Integer.parseInt(obj);
System.out.println("this is custpolyset.jsp and customer id is...."+uid);

con=DBConn.getConnection();

Statement st=con.createStatement();
rs=st.executeQuery("select policyid from custpolicies where custid="+uid);

//String id=request.getParameter("cid").trim();
//System.out.println(id);  
System.out.println("in the custall html"+rs+ "   sop");
%>
  </p>
  <p><strong>Date:</strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <p align="left"><font color="#CC0099" size="6" face="Monotype Corsiva"> <em><strong> 
    Customer Policy </strong></em></font></p>
  <p align="left">&nbsp;</p>
  <table width="213" height="72" border="1" align="left" cellpadding="3">
    <tr> 
      <td width="116" height="62"> <div align="right"><font size="4" face="Times New Roman, Times, serif"><em><strong>Select 
          PolicyID</strong></em></font><strong> </strong></div>
        <strong> 
        <label> </label>
        </strong> <div align="center"><strong><font size="5" face="Times New Roman, Times, serif"></font></strong></div></td>
      <td width="73"><select name="bid" id="bid">
          <% while(rs.next())
	  {%>
          <option><%=rs.getInt(1)%></option>
          <% }
	  %>
        </select></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <div align="center">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <div align="center"></div>
    <table width="217" border="0" align="left" cellpadding="3">
      <tr> 
        <td width="101"><div align="center">
            <input type="submit" name="Submit" value="Submit">
          </div></td>
        <td width="98"> <div align="center">
            <input type="reset" name="Submit2" value="Reset">
          </div></td>
      </tr>
    </table>
    <p>&nbsp;</p>
    <p align="left">&nbsp;</p>
    <p>&nbsp;</p>
  </div>
  
<% /*if(id.equals("PremiumPay"))
response.sendRedirect("PremPayment.jsp");
else if(id.equals("Bonus"))
response.sendRedirect("PremBonus.jsp");
else if(id.equals("PDates"))
response.sendRedirect("PremDates.jsp");*/
%>  
</form>
</body>
</html>
