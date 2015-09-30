<jsp:useBean id="t1" class="com.bean.loginBean" scope="session"/>
    <jsp:setProperty property="*" name="t1"></jsp:setProperty>

    <% String s=t1.validate();
            	                
      			session.setAttribute("transfer_status","null");
      			session.setAttribute("withdraw_status","null");
      			session.setAttribute("delete_status","null");
      			session.setAttribute("register_status","null");
       			session.setAttribute("deposit_status","null");     	
      			
                if(s.equals("home.jsp"))
                {
                    session.setAttribute("username",t1.getUsername());
                    session.setAttribute("password",t1.getPassword()); 
                                      
                    response.sendRedirect("home.jsp");
                }

                if(s.equals("error.jsp"))
                    response.sendRedirect("error.jsp");
                if(s.equals("index.jsp"))
                    response.sendRedirect("index.jsp");

             
    %>




