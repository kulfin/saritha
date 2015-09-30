<%@ include file="/WEB-INF/jsp/inc/_taglibs.jsp" %>

<struct:htmlWrapper navi="portfolio">

<jsp:attribute name="includeInHeader">

<script type="text/javascript" src="static/js/widgets/agilefantWidget.js"></script>

<%@include file="inc/includeSimile.jsp" %>
<script type="text/javascript" src="static/js/simile/extensions/LoadPlot.js"></script>
<script type="text/javascript" src="static/js/simile/extensions/user-load-timeplot-source.js"></script>
<script type="text/javascript" src="static/js/simile/extensions/UserLoadPlotWidget.js"></script>

<script type="text/javascript" src="static/js/excanvas.js"></script>
<aef:css path="simile" minify="true" />

<script type="text/javascript">
$(document).ready(function() {

  $('.widgetList').sortable({
    connectWith: '.widgetList',
    dropOnEmpty: true,
    placeholder: 'widget-placeholder',
    handle: '.widgetHeader > div',
    items: '> :not(.staticWidget)',
    delay: 300,
    stop: function(event, ui) {
      var pos = ui.item.parent('ul').children('li').index(ui.item);
      $.ajax({
        type: 'POST',
        url:  'ajax/widgets/moveWidget.action',
        data: { widgetId: ui.item.aefWidget('option','widgetId'), listNumber: ui.item.parent('ul').attr('listNumber'), position: pos }
      });
    }
  });
  

  /*
   * New widget creation
   */
  var searchUrls = {
    "iterationMetrics": "iterations.action",
    "projectMetrics": "projects.action",
    "userLoad": "users.action",
    "story": "stories.action"
  };
  $('.newWidgetLink').click(function() {
    var clone = $('#templates > #newWidget').clone();
    clone.removeAttr('id');

    clone.find('.cancelNewWidget').click(function() {
      clone.remove();
    });

    var idField = clone.find('.objectId');
    var typeField = clone.find('.objectType');
    
     
    idField.autocomplete({
      source: function(request, response) {
        $.ajax({
          url: "ajax/" + searchUrls[typeField.val()],
          dataType: "json",
          data: { term: request.term },
          success: function(data) {
            response($.map(data, function(item) {
              return {
                label: item.label,
                value: item.label,
                originalObject: item.originalObject
              }
            }))
          }
        })
      },
      minLength: 0,
      select: function(event, ui) {
        idField.data('selectedId',ui.item.originalObject.id);
      }
    })
    .click(function() {
      jQuery(this).autocomplete('search', $(this).val());
    });

    var overlay = clone.find('.widget-overlay');
    clone.find('.saveNewWidget').click(function() {
      overlay.show();
      var newObjectId = idField.data('selectedId');
      var newObjectType = typeField.val();
      $.ajax({
        type: 'POST',
        dataType: 'html',
        url: 'ajax/widgets/createWidget.action',
        data: { type: newObjectType, objectId: newObjectId, collectionId: ${contents.id}, position: 0, listNumber: 0 },
        success: function(data, status) {
          MessageDisplay.Ok('Metric added');
          
          var newWidget = $('<li />');
          clone.replaceWith(newWidget);

          newWidget.html(data);
          
          var newWidgetId = newWidget.find('input[type=hidden][name=widgetId]').val();

          newWidget.aefWidget({
            widgetId: newWidgetId,
            objectId: newObjectId,
            realWidget: true,
            ajaxWidget: true,
            initialReload: false,
            url: 'ajax/widgets/' + newObjectType + '.action' 
          });
        },
        error: function(xhr,status,error) {
          overlay.hide();
          MessageDisplay.Error("An error occurred when creating the metric",xhr);
        }
      });
    });
    
    clone.prependTo($('.widgetList:eq(0)'));
    return false;
  });

  /*
   * Properties widget
   */
  $('.propertiesWidgetLink').click(function() {
    if ($('#portfolioPropertiesWidget').length === 0) {
      var clone = $('#templates > #propertiesTemplate').clone();
      clone.attr('id','portfolioPropertiesWidget').prependTo($('.widgetList:eq(0)'));
    }
  });
  
  /*
   * Load the widget contents
   */

  var widgetCounter = 0;
  <c:forEach items="${contents.widgets}" var="widget">
  
  widgetCounter++;
  
  $('#widget_${widget.id}').aefWidget({
    widgetId: ${widget.id},
    objectId: ${widget.objectId},
    url: 'ajax/widgets/${widget.type}.action'
  });
  </c:forEach>

  if (widgetCounter === 0) {
    $('.newWidgetLink').click();
  }

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


var deletePortfolio = function deletePortfolio(link) {
  var dialog = new DynamicsConfirmationDialog(
      "Really delete the dashboard?",
      "This action can't be reversed",
      function() {
        window.location.href = "deletePortfolio.action?collectionId=${collectionId}"
      }
  );
  return false;
};

var cancelProperties = function cancelProperties(link) {
  $(link).parents('.widget').remove();
  return false;
};

function clickDropdownMenu() {
  var input = $("#objectIdInput");
  wasOpen = $(".ui-autocomplete").is(':visible');
  // Close if already visible
  if ( wasOpen ) {
    input.autocomplete('close');
    return;
  }
  input.click();
  // Pass empty string as value to search for, displaying all results
  input.autocomplete( "search", "" );
};

function createNewDashboard() {
  window.location.href = "createPortfolio.action";
};

</script>
</jsp:attribute>
<jsp:body>


<div class="structure-main-block">

<h2>Dashboard: ${contents.name}</h2>

<p>

Change dashboard to
<select id="changeToSelection">
  <option selected="selected" style="color: #666;">Select...</option>

  <optgroup label="General">
    <option value="portfolio">Ongoing projects</option>
  </optgroup>
  
  <optgroup label="Custom Dashboards">
    <c:forEach items="${collections}" var="collection">
      <option value="${collection.id}">${collection.name}</option>
    </c:forEach>
  </optgroup>
  
</select>

<button class="dynamics-button" onclick="createNewDashboard()" style="width: 130px; margin-left: 15px;">Create new dashboard</button>

</p>

<div style="margin-right: 2.5%; min-width: 750px;">
  <a href="#" class="controlLink newWidgetLink" style="float: left;"><span>Add metric</span> <span class="plusSign">+</span></a>
  <a href="#" class="controlLink propertiesWidgetLink" style="float: left;"><span>Properties</span> <span class="plusSign">?</span></a>
</div>

<br>

<div style="margin-top: 2em; min-width: 750px; background: #def;">
  <c:set var="listCount" value="0"/>
  <c:forEach items="${widgetGrid}" var="widgetList">
    <div class="widgetContainer">
      <ul class="widgetList" listNumber="${listCount}">
        <c:forEach items="${widgetList}" var="widget">
          <li id="widget_${widget.id}"><div style="text-align:center;"><img src="static/img/pleasewait.gif" style="display:inline-block;vertical-align:middle;"/><span style="font-size:100%;color:#666;vertical-align: middle;">Please wait...</span></div></li>
        </c:forEach>
      </ul>
    </div>
    <c:set var="listCount" value="${listCount + 1}"/>
  </c:forEach>
</div>


<!-- Hidden templates -->
<ul id="templates" style="display: none;">
  <!-- Create new widget -->
  <li class="widget createNewWidget staticWidget" id="newWidget" style="position:relative;">
    
    <div class="widgetHeader"><span>Create a new metric</span></div>
    <div class="widgetContent">
      <table>
        <tr>
          <td>Type</td>
          <td><ww:select name="type" list="#{'story':'Story','iterationMetrics':'Iteration Metrics', 'projectMetrics':'Project Metrics', 'userLoad': 'User Workload'}" cssClass="objectType"/></td>
        </tr>
        <tr>
          <td>Object</td>
          
          <td>
            <span class="autocomplete-custom-combobox">
              <input id="objectIdInput" name="object" class="objectId" style="width: 95%;"/>
              <a onclick="clickDropdownMenu()" title="Show all items" class="ui-button ui-widget ui-state-default ui-button-icon-only autocomplete-custom-combobox-toggle ui-corner-right">
                <span class="ui-button-icon-primary ui-icon ui-icon-triangle-1-s"></span>
                <span class="ui-button-text"></span>
              </a>
            </span>
          </td>
        </tr>
      </table>
      <div style="clear: left; float: right;">
        <button class="dynamics-button saveNewWidget">Save</button>
        <button class="dynamics-button cancelNewWidget">Cancel</button>
      </div>
    </div>
    
    <div class="widget-overlay">
      <div>
        <img src="static/img/pleasewait.gif" alt="" /> Please wait...
      </div>
    </div>
  </li>
  
  <li class="widget staticWidget" id="staticWidget">
    <div style="text-align:center;"><img src="static/img/pleasewait.gif" style="display:inline-block;vertical-align:middle;"/><span style="font-size:100%;color:#666;vertical-align: middle;">Please wait...</span></div>
  </li>
  
  <li class="widget staticWidget" id="propertiesTemplate">
    <div class="widgetHeader">
      Dashboard properties
    </div>
    <div class="widgetContent">
      <ww:form action="storePortfolio">
        <input type="hidden" name="collectionId" value="${contents.id}"/>
        <input type="hidden" name="collection.user" value="null" />
        <table id="portfolioPropertiesTable">
          <tr>
            <td>
              Name
            </td>
            <td>
              <input name="collection.name" value="${contents.name}"/>
            </td>
          </tr>
        </table>
        
        <div style="clear: left;">
          <button class="dynamics-button saveProperties" style="float: left;">Save</button>
          <button class="dynamics-button cancelProperties" style="float: left;" onclick="cancelProperties(this); return false;">Cancel</button>
          <c:if test="${deleteAccess}">
          <button class="dynamics-button deletePortfolio" style="width: 20ex; float: right;" onclick="deletePortfolio(this); return false;">Delete dashboard</button>
          </c:if>
        </div>
      </ww:form>
    </div>
  </li>
</ul>
<!-- /Hidden templates -->

</div>
</jsp:body>
</struct:htmlWrapper>