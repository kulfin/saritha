
/**
 * Controller for the left hand my assignments hierarchy menu.
 * 
 * @param element the root element of the tree.
 * @constructor
 */
var AdministrationMenuController = function AdministrationMenuController(element, toggleElement) {
  this.init(element, toggleElement);
};

AdministrationMenuController.prototype.init = function(element, toggleElement) {
  this.element = element;
  this.element.html("");
  this.toggleControl = toggleElement;
  this.tree = null;
  this.initTree();
};

AdministrationMenuController.prototype.initTree = function() {   
  this.element.dynatree({
    keyboard: false,
    autoFocus: false,
    onClick: function(dtnode, event) {
      if ($(event.target).hasClass("ui-dynatree-title")) {
        window.location.href = event.target.href;
      }
    },
    debugLevel: 0,
    initAjax: null
  });

  this.tree = this.element.dynatree("getTree");
  
  var rootNode = this.element.dynatree("getRoot");
  var currentUser = PageController.getInstance().getCurrentUser();
  
  /* Both admin and non-admin can edit his/her personal account. */
  
  rootNode.addChild({
    title: "My Settings",
    icon: false,
    key: "editUser.action"
  });
  
  rootNode.addChild({
	    title: "Users",
	    icon: false,
	    key: "listUsers.action"
	  });
	  
rootNode.addChild({
	    title: "Teams",
	    icon: false,
	    key: "listTeams.action"
	  });

  /* Only admin has access to these. */
  if (currentUser.getAdmin()) {
		  
	  rootNode.addChild({
	    title: "Access rights",
	    icon: false,
	    key: "accessRights.action"
	  });
  
	  rootNode.addChild({
	    title: "Account settings",
	    icon: false,
	    key: "systemSettings.action"
	  });
  
	  rootNode.addChild({
		  title: "Database export",
		  icon:false,
		  key: "databaseExport.action"
	  });  
  }
  
  this.element.find("a.ui-dynatree-title").each(function(key, item) {
    item.href = $(item.parentNode).attr("dtnode").data.key;
  });
  
  var currentLastSegment = getCurrentLastSegment();
  // Setting background color is done in a separate loop,
  // so that the links work even if calling purl does not work properly
  this.element.find("a.ui-dynatree-title").each(function(key, item) {
    var itemLastSegment = getUrlLastSegment(item);
    if (currentLastSegment != null && itemLastSegment != null && currentLastSegment == itemLastSegment) {
      item.setAttribute('style', 'background-color: #D9DDE7 !important; font-weight: bold;');
    }
  });
};

function getUrlLastSegment(url) {
  url = $.url(url);
  var lastSegment = url.segment().pop();
  return lastSegment;
}

function getCurrentLastSegment() {
  var url = $(location).attr('href');
  return (getUrlLastSegment(url));
}

AdministrationMenuController.prototype.reload = function() {};
