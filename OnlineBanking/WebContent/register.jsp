
<%String name=(String)session.getAttribute("username");
String s=(String)session.getAttribute("register_status");

if(name==null)
{
    response.sendRedirect("index.jsp");
}
else
{
	if(name.equals("admin"))
{
%>

    <script type="text/javascript">
    
    function check()
    {
    if(document.f1.username.value=="")
    {
        alert("plz enter username!");
        return false;
    }

    if(document.f1.password.value=="")
    {
        alert("plz enter password!");
        return false;
    }

    if(document.f1.password.value!=document.f1.repassword.value)
    {
        alert("password does not match!");
        return false;
    }

    if(document.f1.amount.value=="")
    {
        alert("plz enter amount!");
        return false;
    }

    if(document.f1.address.value=="")
    {
        alert("plz enter address!");
        return false;
    }

    if(document.f1.phone.value=="")
    {
        alert("plz enter phone no.!");
        return false;
    }
    }
</script>
<div id="container">
<link href="css.css" rel="stylesheet" type="text/css"/>

<div id="header">
<img src="images/logo.jpg"/>

</div>

	<div id="navigation">
		<ul>
                    
			<li><a href="index.jsp">Home</a></li>
			<li><a href="allaccounts.jsp">Show All Accounts</a></li>
			<li><a href="register.jsp">Open Account</a></li>
			<li><a href="deposit.jsp">Deposit</a></li>
			<li><a href="delete.jsp">Close Account</a></li>
            <li> <a href="logout.jsp">Logout</a></li>
 
		</ul>   
   </div>


<div id="content-container">
<div id="content">
<center>

<%if(s.equals("register")) 
{%>
<h2 style="color: red;">Account Opened!!</h2><br/>
<%session.setAttribute("register_status","no_status");
} 

if(s.equals("error"))
{%><h2 style="color: red;">Account Already Exists!!</h2><br/><%
session.setAttribute("register_status","no_status");}%>


<br/><h2>Open Account :-</h2><br/><br/>
    <form name="f1" action="registerBean.jsp" onsubmit="return check();">
		<b>		 
		Username: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="username"/><br/><br/>
		Password: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password"/><br/><br/>
		Re-Password: &nbsp;<input type="password" name="repassword"/><br/><br/>
		Amount: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="amount"/><br/><br/>
		Address: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="address"/><br/><br/>
		Phone No: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="phone"/><br/><br/></b>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Submit"/>
    	&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Clear"/>
</form></center>
</div>
<div id="aside">
<p>* Online banking is the practice of making bank transactions or paying bills via the Internet.
<br/><br/>* Banking online allows a customer to make deposits, withdrawals, transfers with the click of a mouse.
<br/><br/>* Online banking also eliminates paper waste, which is a plus not only for those who have to handle all the paper work, but also for the environment.
<br/><br/>* Security is always an issue with Internet transactions. Although information is encrypted , and the chances of an account being hacked are slim, it happen.
</p>
</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>

<%}
else
	response.sendRedirect("index.jsp");
}
%>