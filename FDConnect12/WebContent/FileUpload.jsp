<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
<script type="text/javascript">
function check()
{
	
	var fileName = document.getElementById("file").value;
	alert("file is" + fileName);
	if(fileName == "")
	{
		alert("Please Select File to Upload");
		file.focus();
		return false;
	}

	/* if(!/(\.xls|\.xlsx)$/i.test(fileName.value)) {
        alert("Invalid image file type.");      
        file.form.reset();
        file.focus();        
        return false;   
    }  
    return true; 
	*/
	var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
    if(ext == "xls" || ext == "xlsx" )
    {
   		return true;
    } 
    else
    {
	    alert("Upload Excel files only");
	    file.focus();
	    return false;
    }
}
</script>
</head>
<body>
<div id="Test">
	<form action="Upload" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<center>
		<h3>File Upload:</h3>
		Select a file to upload: <br />
		<input type="file" name="file" size="20" id="file" accept="applicatoin/excel"/> <br />
		
		<input type="submit" value="Upload File" />
	</center>
	</form>
</div>
</body>
</html>