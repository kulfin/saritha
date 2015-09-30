<%@ include file="../../jsp/inc/_taglibs.jsp" %>
<%@tag description="Agilefant backlog menu" %>

<%@attribute name="navi" fragment="false" required="false"%>

<div id="menuAccordion">
    <h3 id="menuAccordion-products"><a href="#">Backlogs</a></h3>
    <div id="backlogMenuTree">&nbsp;</div>
    <h3 id="menuAccordion-administration"><a href="#">Administration</a></h3>
    <div id="administrationMenu">&nbsp;</div>
</div>


<script type="text/javascript">
$(document).ready(function() {
  var navi = "${navi}";

  $("#menuAccordion").accordion({
    active: false,
    autoHeight: false,
    change: function(event, ui) {
      var selectedId = ui.newHeader[0].id;
      if (typeof(selectedId) === 'string' && selectedId !== "") {
        $.cookie('agilefant-menu-accordion', '#' + selectedId);
      }
      if (selectedId === 'menuAccordion-products') {
        if (window.menuController == null) {
          window.menuController = new BacklogMenuController($('#backlogMenuTree'), $('#menuControl'));
        }
        else {
          window.menuController.reload();
        }
    	} else if (selectedId === 'menuAccordion-administration') {
        if (window.administrationMenuController == null) {
          window.administrationMenuController = new AdministrationMenuController($('#administrationMenu'), $('#menuControl'));
        }
    	}
  	}
  });

  var activatedSection = $.cookie('agilefant-menu-accordion');
  if (navi === "settings") {
    $('#menuAccordion').accordion('activate', '#menuAccordion-administration');
  } else if (typeof(activatedSection) === 'string' && activatedSection !== "") {
    $("#menuAccordion").accordion('activate', activatedSection);
  } else {
    window.menuController = new BacklogMenuController($('#backlogMenuTree'), $('#menuControl'));
    $('#menuAccordion').accordion('activate', "#menuAccordion-products");
  }
});
</script>

