<jsp:useBean id="t2" class="com.bean.registerBean"></jsp:useBean>
<jsp:setProperty name="t2" property="*"/>

<%
    String s=t2.insert();
    if(s.equals("register"))
    {
    	session.setAttribute("register_status","register");
        response.sendRedirect("register.jsp");
    }

    if(s.equals("error"))
    {
    	session.setAttribute("register_status","error");
        response.sendRedirect("register.jsp");
    }
    
    if(s.equals("error.jsp"))
    {
        response.sendRedirect("error.jsp");
    }
%>
