<%@ include file="../inc/_taglibs.jsp"%>
<form>
    <div class="shareIterationForm">
      <div><img src="static/img/agilefant-logo-80px.png" alt="Share iteration" style="float: left;" />
        <div style="margin-left: 90px">
          <p>Send the following URL to grant the recipients read-only access to this iteration:</p>

          <%--
            If you are using proxy, getRequestURL() will have serverName/serverPort pointing to the proxied host (eg. localhost).
            To fix this, add the following settings to your Tomcat server.xml file's Connector:
            proxyName="YOURHOSTNAME" (eg. "cloud.agilefant.org")
            proxyPort="YOURHOSTPORT" (eg. "80")
          --%>
          <%
          	String url = request.getRequestURL().toString();
          	url = url.substring(0, url.indexOf("WEB-INF"));
          	url = url.concat("token/"); 
          %>
          
          <p><b><%=url%>${readonlyToken}</b></p>
          <p>&nbsp;</p>
          <p style="font-size: 8pt">Note: If the server name/port are wrong in the above url, contact the person who is responsible for your Agilefant installation. He needs to set the proxyName and proxyPort parameters to Tomcat's server.xml. </p>
          <%--
          <p><b>${readonlyToken}</b></p>
          <p>Usage:<br>[the URL of your Agilefant instance]/ROIteration.action?readonlyToken=[token]</p>
          <p>Example:<br>http://cloud.agilefant.org/community/ROIteration.action?readonlyToken=${readonlyToken}</p>
           --%>
        
          <%--
          <script>
	          var text_input = document.getElementById ('tokenUrl');
	          text_input.focus ();
	          text_input.select ();
          </script>
          --%>
        </div>
      </div>
    </div>
</form>