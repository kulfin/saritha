<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<div id="dvTable"></div>
<script>


	
   
   function GenerateTable() {
	   
	   var customers = new Array();
	    var name = '<%= session.getAttribute("jArrayImage") %>'; 
	    
	    var imgPath = '<%= session.getAttribute("imagePaths") %>'; 
	    
	    var imageNames = '<%= session.getAttribute("listImages") %>'; 
	   customers.push(imageNames);
    

	   var myHTMLStr = '<table>';

	   for(var i in customers) {
	     myHTMLStr += '<tr><td>'
	               + customers[i]['imageDescription']
	               + '</td><td>'
	               + `<img width='100' height='100' src="${listImageAWS}">`
	               + '</td></tr>';
	         
	   }
	   myHTMLStr+='</table>';
	  

       var dvTable = document.getElementById("dvTable");
       document.getElementById('dvTable').innerHTML = myHTMLStr;
       dvTable.appendChild(table);
   }

window.onload = GenerateTable;


</script>

</head>
<body> 

 
<form name="Form1" method="post">  
  
<input type="file" name="filename"/>  
<input  type="submit" value="UploadtoS3Bucket" onclick="UploadtoAWS();" />
 <input  type="submit" value="DownloadfromAWS" onclick="DownloadfromAWS();" /> 
</form>  
</body>  
</html>