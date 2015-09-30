<%String name=(String)session.getAttribute("username");%>

<div id="header">
<img src="images/logo.jpg"/>
</div>

<div id="navigation">
    <ul>
						<%
                        if(name!=null)
                        {%>
                        <li> <a href="index.jsp">Home</a></li>
                        <%}%>
    <li><a href="transfer.jsp">Transfer Money</a></li>
    <li><a href="withdraw.jsp">Withdraw Money</a></li>
    <li><a href="view.jsp">View Account</a></li>
    <%
    if(name!=null)
    {%>
        <li> <a href="logout.jsp">Logout</a></li>
    <%}%>
    </ul>
</div>