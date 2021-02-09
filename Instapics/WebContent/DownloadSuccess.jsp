<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<script>

function GenerateTable() {
    //Build an array containing Customer records.
    var customers = new Array();
    var name = '<%= session.getAttribute("arrayObjImage") %>';   
    name = name.substring(1, name.length-1);
     
    var eventlist = JSON.stringify(name);

    var eventstring = new String();

    eventstring = eventlist.toString().replace(/"/g, "");
   var str = eventstring.replace(/\\/g, '');
   var result2 = str.split(',').join('')
   
   
     alert("name is::" +result2);
    
    
    
    customers.push(result2);
   
   
  
    
 

    //Create a HTML Table element.
    var table = document.createElement("TABLE");
    table.border = "1";

    //Get the count of columns.
    var columnCount = customers[0].length;

    //Add the header row.
    var row = table.insertRow(-1);
    for (var i = 0; i < columnCount; i++) {
        var headerCell = document.createElement("TH");
        headerCell.innerHTML = customers[0][i];
        row.appendChild(headerCell);
    }

    //Add the data rows.
    for (var i = 1; i < customers.length; i++) {
        row = table.insertRow(-1);
        for (var j = 0; j < columnCount; j++) {
        	
        	
         var img = document.createElement("IMG");
        img.src = "http://localhost:8080/Instapics/DownloadFile/download";
        	    
        	
            var cell = row.insertCell(-1);
            cell.innerHTML = customers[i][j];
        }
    }

    var dvTable = document.getElementById("dvTable");
    dvTable.innerHTML = "";
    dvTable.appendChild(table);
}


window.onload = GenerateTable;


</script>

</head>
<body> 
<div id="dvTable"></div>
 
<form name="Form1" method="post">  
  

</form>  
</body>  
</html>