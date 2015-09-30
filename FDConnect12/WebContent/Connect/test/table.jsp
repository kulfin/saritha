<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">

		
		<style type="text/css" media="screen" >
			@import "site_jui.ccss.css";
			@import "demo_table_jui.css";
			@import "jquery-ui-1.7.2.custom.css";
			
			/*
			 * Override styles needed due to the mix of three different CSS sources! For proper examples
			 * please see the themes example in the 'Examples' section of this site
			 */
			.dataTables_info { padding-top: 0; }
			.dataTables_paginate { padding-top: 0; }
			.css_right { float: right; }
			#example_wrapper .fg-toolbar { font-size: 0.8em }
			#theme_links span { float: left; padding: 2px 10px; }
			#example_wrapper { -webkit-box-shadow: 2px 2px 6px #666; box-shadow: 2px 2px 6px #666; border-radius: 5px; }
			#example tbody {
				border-left: 1px solid #AAA;
				border-right: 1px solid #AAA;
			}
			#example thead th:first-child { border-left: 1px solid #AAA; }
			#example thead th:last-child { border-right: 1px solid #AAA; }
		</style>
		
		<script type="text/javascript" src="complete.min.js"></script>
		<script type="text/javascript" src="jquery.dataTables.min.js"></script>
		<script type="text/javascript">
			function fnFeaturesInit ()
			{
				/* Not particularly modular this - but does nicely :-) */
				$('ul.limit_length>li').each( function(i) {
					if ( i > 10 ) {
						this.style.display = 'none';
					}
				} );
				
				$('ul.limit_length').append( '<li class="css_link">Show more<\/li>' );
				$('ul.limit_length li.css_link').click( function () {
					$('ul.limit_length li').each( function(i) {
						if ( i > 5 ) {
							this.style.display = 'list-item';
						}
					} );
					$('ul.limit_length li.css_link').css( 'display', 'none' );
				} );
			}
			
			$(document).ready( function() {
				fnFeaturesInit();
				$('#example').dataTable( {
					"bJQueryUI": true,
					"sPaginationType": "full_numbers"
				} );
				
				SyntaxHighlighter.config.clipboardSwf = 'media/javascript/syntax/clipboard.swf';
				SyntaxHighlighter.all();
			} );
		</script>
<script type="text/javascript">

(function(){
  var bsa = document.createElement('script');
     bsa.type = 'text/javascript';
     bsa.async = true;
     bsa.src = '//s3.buysellads.com/ac/bsa.js';
  (document.getElementsByTagName('head')[0]||document.getElementsByTagName('body')[0]).appendChild(bsa);
})();

</script>
		
	</head>
	<body >
		<div id="fw_container">
		<div class="css_clear css_spacing"></div>
		<div class="full_width">
<table cellpadding="0" cellspacing="0" border="0" class="display" id="example" style="wid:980px">
	<thead>
		<tr>
			
			<th>Select</th>
			<th>Edit</th>
			<th>Project No.</th>
			<th>Project Name</th>
			<th>Client Name</th>
			<th>FD Division</th>
			<th>Project No</th>
			<th>Project Name</th>
			<th>Client Name</th>
		</tr>
	</thead>
	<%
		
		
		ProjectServices services =new ProjectServices();
		String result=services.Project_open();
		if(result==null){
	%>
		<tr>
			<td></td>		
		</tr>
		<tr>
			<td>NO DATA</td>		
		</tr>
		<%
		}else {
			%>
			
		<tbody>
			
			<%				
				String[] row_data=result.split(Constants.rowSeperator);
				for(int i=0;i<row_data.length;i++){
					String[] column_data=row_data[i].split(Constants.columnSeperator);
					%>
		<tr class="gradeA">
					<td><input type="checkbox" onclick="alert('Select');"> </td>
					<td>
					<img src="../../images/edit.png" alt="Edit" onclick="alert('edit');"></img>
					</td>
					<td><%=column_data[0]%></td>
					<td><%=column_data[1]%></td>
					<td><%=column_data[2]%></td>
					<td class="center"><%=column_data[3]%></td>
					<td class="center"><%=column_data[0]%></td>
					<td class="center"><%=column_data[1]%></td>
					<td><%=column_data[2]%></td>
		</tr>
	
	<%
				}
	%>

		
	</tbody>
	
	
	<%  }	%>
	
</table>
				</div>
				

<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-365466-5']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>

		</div>
	</body>
</html>
