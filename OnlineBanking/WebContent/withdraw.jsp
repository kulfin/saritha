<%String name=(String)session.getAttribute("username");
String s=(String)session.getAttribute("withdraw_status");

if(name==null)
{
    response.sendRedirect("index.jsp");
}
else
{
if(!name.equals("admin"))
{
%>

<script type="text/javascript">
    function check()
    {
   
       if(document.f1.amount.value=="")
    {
        alert("plz enter amount!");
        return false;
    }

     }
</script>


<div id="container">
<link href="css.css" rel="stylesheet" type="text/css"/>
<jsp:include page="home-header.jsp"></jsp:include>

<div id="content-container">
<div id="content">
    <center>
   
   <%if(s.equals("true")) 
{%>
<h2 style="color: red;">Withdraw Successfull!!</h2><br/>
<%session.setAttribute("withdraw_status","no_status");
} 

if(s.equals("false")) 
{%>
<h2 style="color: red;">Insufficient Amount!!</h2><br/>
<%session.setAttribute("withdraw_status","no_status");
}%>    
    
    <h2>Withdraw :-</h2><br/>
<form action="withdrawBean.jsp" name="f1" onsubmit="return check();">
<b>Amount:</b> <input type="text" name="amount"/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="Withdraw"/>
&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Clear"/>
</form><br/>
<img src="images/withdraw.jpg" width="550" height="300px"/></center>

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