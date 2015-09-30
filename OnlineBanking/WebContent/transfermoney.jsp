<%@page import="com.connect.dbConnect"%>
<%@page import="java.sql.*"%>

<%int a=Integer.parseInt(request.getParameter("amount"));
String u=request.getParameter("user");
String s=(String)session.getAttribute("username");

		try
        {
            Connection con=dbConnect.getConnect();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from record where username='"+u+"'");
       		
       		if(rs.next())
       		{
       			rs=st.executeQuery("select * from record where username='"+s+"'");
       			if(rs.next())
       			{
       				if(rs.getInt(3)>a)
       				{
       					st.executeUpdate("update record set amount=amount+"+a+" where username='"+u+"'");
       					st.executeUpdate("update record set amount=amount-"+a+" where username='"+s+"'");
       					session.setAttribute("transfer_status","true");
       				}
       				else
       					session.setAttribute("transfer_status","insufficient_amount");
       			}
       			else
       				response.sendRedirect("error.jsp");
       		}
       		else
       			session.setAttribute("transfer_status","username_not_found");
       					
       		response.sendRedirect("transfer.jsp");
         
         }catch(Exception e)
           {
       			response.sendRedirect("error.jsp");
           }
           
 %>