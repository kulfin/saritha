<%@page import="com.connect.dbConnect"%>
<%@page import="java.sql.*"%>

<%String u=request.getParameter("username");
int a=Integer.parseInt(request.getParameter("amount"));

    try
    {
    Connection con=dbConnect.getConnect();
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select * from record where username='"+u+"'");
    
    if(!rs.next())
    {
    		session.setAttribute("deposit_status","not_found");
    }
    else
    {
    		
    int x =st.executeUpdate("update record set amount=amount+"+a+" where username='"+u+"'");
	    
	if(x>0)
		session.setAttribute("deposit_status","deposit");
	else
    	response.sendRedirect("error.jsp");
	}
				
	response.sendRedirect("deposit.jsp");
    }catch(Exception e)
    {
    	response.sendRedirect("error.jsp");
    }
    %>