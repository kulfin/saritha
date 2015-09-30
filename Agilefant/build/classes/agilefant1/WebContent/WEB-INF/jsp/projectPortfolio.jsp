<%@ include file="./inc/_taglibs.jsp"%>

<struct:htmlWrapper navi="portfolio">

<jsp:attribute name="includeInHeader">
<aef:css path="simile" minify="true" />

<script type="text/javascript" src="static/js/excanvas.js"></script>
<%@include file="inc/includeSimile.jsp" %>
<script type="text/javascript" src="static/js/simile/extensions/portfolio-eventsource.js"></script>

<script type="text/javascript">
window.Timeline.DateTime = window.SimileAjax.DateTime;
Timeline.GregorianDateLabeller.monthNames["en"] = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
$(document).ready(function() {
  var controller = new PortfolioController({
    timelineElement: $("#timeline"),
    rankedProjectsElement: $("#rankedProjects"),
    unrankedProjectsElement: $("#unrankedProjects")
  });

  /*
   * Change to -dropdown
   */
  $('#changeToSelection').change(function() {
    var value = $(this).val();
    if (value === "portfolio") {
      window.location.href = "projectPortfolio.action"
    }
    else {
      window.location.href = "portlets.action?collectionId=" + value
    }
  });
});

function createNewDashboard() {
  window.location.href = "createPortfolio.action";
};
</script>
</jsp:attribute>

<jsp:body>
<h2>Ongoing projects</h2>

<p>

Change dashboard to
<select id="changeToSelection">
  <option selected="selected" style="color: #666;">Select...</option>

  <optgroup label="General">
    <option value="portfolio">Ongoing projects</option>
  </optgroup>
  
  <optgroup label="Dashboards">
    <c:forEach items="${collections}" var="collection">
      <option value="${collection.id}">${collection.name}</option>
    </c:forEach>
  </optgroup>
  
</select>

<button class="dynamics-button" onclick="createNewDashboard()" style="width: 130px; margin-left: 15px;">Create new dashboard</button>

</p>


	<div class="structure-main-block">	
		<div class="dynamictable ui-widget ui-widget-content ui-corner-all">
			<div class="dynamictable-caption dynamictable-caption-block ui-widget-header ui-corner-all">Timeline</div>
			<div id="timeline">
			</div>
		</div>
	</div>
	<div id="rankedProjects" class="structure-main-block">
	</div>
	<div id="unrankedProjects" class="structure-main-block">
	</div>
</jsp:body>
</struct:htmlWrapper>
