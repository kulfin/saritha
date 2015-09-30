
<%String name=(String)session.getAttribute("username");
 String s=(String)session.getAttribute("transfer_status");


if(name==null)
{
    response.sendRedirect("index.jsp");
}
else
{
if(!name.equals("admin"))
{
%>
<div id="container">
<link href="css.css" rel="stylesheet" type="text/css"/>
<jsp:include page="home-header.jsp"></jsp:include>

<script type="text/javascript">
    
    function check()
    {
   
       if(document.f1.amount.value=="")
    {
        alert("plz enter amount!");
        return false;
    }
   
	if(document.f1.user.value=="")
    {
        alert("plz enter username!");
        return false;
    }
       
    if(document.f1.user.value!=document.f1.reuser.value)
    {
        alert("username does not match!");
        return false;
    }


     }
</script>

<div id="content-container">
<div id="content">
    <center>
    
 <%if(s.equals("username_not_found")) 
{%>
<h2 style="color: red;">Account Not Found!!</h2><br/>
<%session.setAttribute("transfer_status","no_status");
} 

if(s.equals("true")) 
{%>
<h2 style="color: red;">Transfer Successfull!!</h2><br/>
<%session.setAttribute("transfer_status","no_status");
} 

if(s.equals("insufficient_amount"))
{%><h2 style="color: red;">Insufficient Amount!!</h2><br/><%
session.setAttribute("transfer_status","no_status");}%>   
    
    <br><br><h2>Transfer :-</h2><br/><br/>
<form action="transfermoney.jsp" name="f1" onsubmit="return check();">
<b>Amount:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <input type="text" name="amount"/><br/><br/>
<b>Destination Username:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b> <input type="text" name="user"/><br/><br/>
<b>Retype Username:&nbsp;</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="reuser"/><br/><br/> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>&nbsp; 
<input type="submit" value="Transfer"/>
&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Clear"/>
</form><br/>
</center>

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