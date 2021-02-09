<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<nav class="navbar navbar-default">
		 <div class="container-fluid">
		 <!-- Brand and toggle get grouped for better mobile display -->
		 <div class="navbar-header">
		 <button type="button" class="navbar-toggle collapsed" datatoggle="collapse"
		data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		 <span class="sr-only">Toggle navigation</span>
		 <span class="icon-bar"></span>
		 <span class="icon-bar"></span>
		 <span class="icon-bar"></span>
		 </button>
		 <a class="navbar-brand" href="#">Photo Album</a>
		 </div>
		 <!-- Collect the nav links, forms, and other content for toggling -->
		 <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		 <ul class="nav navbar-nav">
		 <li class="active"><a href="http://localhost:8080/Instapics1/Upload.jsp">Upload Page </a></li>
		 <li class="active"><a href="http://localhost:8080/Instapics1/index.html">Home </a></li>
		 <li ><a href="http://localhost:8080/Instapics1/album.jsp">Album</a></li>
		 </ul>
		 </div><!-- /.navbar-collapse -->
		 </div><!-- /.container-fluid -->
	</nav>  
<div id="dvTable"></div>
<script>

function UploadtoAWS()
{
    document.Form1.action = "http://localhost:8080/Instapics1/UploadFileServlet/upload";
       
    document.Form1.submit();             
    return true;
}


function DownloadfromAWS()
{
    document.Form1.action = "http://localhost:8080/Instapics1/DownloadFile/download";
       
    document.Form1.submit();             
    return true;
}
</script>

</head>
<body> 

 
<form name="Form1" method="post">  
  
<input type="file" name="filename"/>  
<input class="btn btn-success" type="submit" value="UploadtoS3Bucket" onclick="UploadtoAWS();" />
 <input class="btn btn-success"  type="submit" value="DownloadfromAWS" onclick="DownloadfromAWS();" /> 
</form>  
</body>  
</html>