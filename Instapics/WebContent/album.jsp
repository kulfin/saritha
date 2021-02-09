<%@ page import="java.io.ByteArrayInputStream"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.List"%>
<%@ page import="com.amazonaws.auth.AWSCredentials"%>
<%@ page import="com.amazonaws.auth.BasicAWSCredentials"%>
<%@ page import="com.amazonaws.services.s3.AmazonS3"%>
<%@ page import="com.amazonaws.services.s3.AmazonS3Client"%>
<%@ page import="com.amazonaws.services.s3.model.Bucket"%>
<%@ page import="com.amazonaws.services.s3.model.CannedAccessControlList"%>
<%@ page import="com.amazonaws.services.s3.model.ObjectMetadata"%>
<%@ page import="com.amazonaws.services.s3.model.PutObjectRequest"%>
<%@ page import="com.amazonaws.services.s3.model.S3ObjectSummary"%>
<%@ page import="java.io.*, java.util.*, javax.servlet.*" %>
<%@ page import="com.amazonaws.AmazonClientException" %>
<%@ page import="com.amazonaws.auth.profile.ProfileCredentialsProvider" %>
<%@ page import="com.amazonaws.services.s3.transfer.TransferManager" %>
<%@ page import="com.amazonaws.services.s3.transfer.Upload" %>
<%@ page import="com.amazonaws.services.s3.AmazonS3Client" %>
<%@ page import="com.amazonaws.services.s3.iterable.S3Versions" %>
<%@ page import="com.amazonaws.services.s3.model.ObjectListing" %>
<%@ page import="com.amazonaws.services.s3.model.S3ObjectSummary" %>
<%@ page import="com.amazonaws.util.StringUtils"%>
<%@ page import="com.amazonaws.services.s3.model.GetObjectRequest"%>
<%@ page import="java.sql.*"%>
<HTML>
<head>
<TITLE>Display file upload form to the user</TITLE>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
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
 <li ><a href="http://localhost:8080/Instapics1/index.html">Home</a></li>
 <li class="active"><a href="http://localhost:8080/Instapics1/album.jsp">Album</a></li>
 </ul>
 </div><!-- /.navbar-collapse -->
 </div><!-- /.container-fluid -->
</nav>
<div class="container">

<%
try{	
	   String url = "jdbc:mysql://localhost:3310/";
	   String userName = "root";
	   String password = "root";
	   String dbName = "awsdemo";
	   Class.forName("com.mysql.jdbc.Driver").newInstance();
	   Connection connection = DriverManager.getConnection(url + dbName, userName, password);
	   Statement statement = connection.createStatement();
	   String command = "SELECT * FROM description";
       ResultSet resultSet =  statement.executeQuery(command);
       
       String bucketPath = "https://mytestbucket23275.s3.amazonaws.com/";
  	   AWSCredentials credentials = new BasicAWSCredentials("AKIAJBAX6ZISLRFMMBXA",	"QWQmXUhdf/hH/ixVQ27Ee1DQ9mVTDYEKsCReLtOT");
  	   AmazonS3Client s3 = new AmazonS3Client(credentials);
  	   ObjectListing photos = s3.listObjects("mytestbucket23275");
  	   
  	   out.println("</br></br>");
  	   
  	 ResultSetMetaData metaData = resultSet.getMetaData();
  	 out.println("<div class='row'>");
       while(resultSet.next()){
    	   out.println("<div class='col-md-3'><img style='height:250px;width:100%;'src='"+bucketPath+resultSet.getObject(3)+"' /><div><div style='padding:8px 5px 8px 5px;background-color:#feff41; font-size:20px; color:#00000;'>"+resultSet.getObject(2)+"</div></div></div>");
       }
       out.println("</div>"); 
       
	   
	}
	 catch(SQLException ex){
	 	ex.printStackTrace();
	 	}		

	 
	 
	 
	  

%>
</div>

</BODY>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</HTML>