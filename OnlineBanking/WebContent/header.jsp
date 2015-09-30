<%String name=(String)session.getAttribute("username");%>

<div id="header">
<img src="images/logo.jpg"/>

</div>

	<div id="navigation">
		<ul>
                    
			<li><a href="index.jsp">Home</a></li>
						<%
                        if(name!=null)
                        {%>
                        <li> <a href="home.jsp">My Account</a></li>
                        <%}%>
                        
                       	
                        
			<li><a href="about.jsp">About</a></li>
			
                        <%
                        if(name!=null)
                        {%>
                        <li> <a href="logout.jsp">Logout</a></li>
                        <%}%>
		</ul>
    
        
            	</div>
