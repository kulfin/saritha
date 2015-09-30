<%@page import="com.aseema.fourthdimension.Service"%>
<% 
	response.setHeader("Pragma","no-cache");  
	response.setHeader("Cache-Control","no-store");  
	response.setHeader("Expires","0");  
	response.setDateHeader("Expires",-1);
	System.out.println("Login JSP in Common");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/Common/loginstyle.css" />
<link rel="stylesheet" type="text/css" media="all"	href="css/adminniceforms-default.css" />
<link rel="stylesheet" type="text/css" href="css/Common/login.css" />
<link rel="icon" href="images/fd_title_logo.png" type="image/x-icon" />
<title>ProTrac</title>
<script type="text/javascript">
	function noBack()
	{
        window.history.forward();
        noBack();
        window.onload = noBack;
        window.onpageshow = function(evt) { if (evt.persisted) noBack(); };
        window.onunload = function() { void (0); };
	}
function validate(username,password)
{
       	var myForm = document.forms[0];
       	
       	if(username.value=="" || username.length==0)
        {
        	alert("Please Enter Username");
            username.focus();
            return false;        
        }
        if(password.value=="" || password.length==0)
        {
            alert("Please Enter Password");
            password.focus();
            return false;
        }
}
	
</script>
</head>
<!------JSP Scripting ------>





<!-----   Body   ----->
<body>
	<div></div>

	<div id="main_container" style="margin-top: 15%">
		<div class="header_login">
			<!--    <div class="logo">   <a href="#"><img src="images/croma_logo.png" alt="" title="" border="0" /></a>         </div>-->
		</div>
		<div class="container">
	<section id="content">
		<form action="LoginAuthentication" method="post">
			<h1>Login Form</h1>
			<div>
				<input type="text" placeholder="Username" id="username" name="username"/>
			</div>
			<div>
				<input type="password" placeholder="Password" id="password" name="password"/>
			</div>
			<div id="login_button">
				<input type="submit" value="Log in" onclick="validate(username,password)"/>
				<!-- <a href="#">Lost your password?</a>
				<a href="#">Register</a> -->
			</div>
		</form><!-- form -->
	</section><!-- content -->
	
	<div class="versionDiv">
		<label>
			Version: 0.3.1
		</label>
	</div>
</div><!-- container -->
		
		
		<div class="footer_login">
		<%
			String notUser=(String)request.getAttribute("notUser");
			if(notUser!=null){
			if(notUser.equalsIgnoreCase("notUser")){
			System.out.println("notUser2 --  >" +notUser);
		%>
		<div class="left_footer_login" style="margin-top:0px; padding-left: 170px;">
		<font style="font-size: 17px;font-weight: bold;" color="FC3030">UserName/Password didn't match !!</font>
		</div>
		<%
		}else
			if(notUser.equalsIgnoreCase("alphanumeric")){
				%>
				<div class="left_footer_login" style="margin-top:0px; padding-left: 208px;">
				<font style="font-size: 17px;font-weight: bold;" color="FC3030">Alpha-Numeric Not Allowed !!</font>
				</div>
				<%
				
			}
			
			}
		%>
<!-- 			<div class="left_footer_login" style="padding-left: 90px;">
				<font style="font-size: 11px" color="#000">Version</font>&nbsp;&nbsp;&nbsp;
				<font style="font-size: 11px;font-weight: bold;" color="#A0A0A0">0.3.1</font>
			</div>
			<div class="left_footer_login"	style="margin-top: 0px; padding-left: 50px;">
				<font style="font-size: 11px" color="#000">Powered
					by</font>&nbsp;&nbsp;&nbsp;
				<a href="http://www.aseema.in"><font color="#A0A0A0" style="font-size: 11px;font-weight: bold;">
					Aseema Softnet Technologies Private Limited
				</font>
				</a>
			</div> -->
			<div class="right_footer_login">
				<a href="#">
					
				</a>
			</div>
		</div>
		
	</div>
</body>
</html>