
/**
 * Controller for the left hand backlog hierarchy menu.
 * 
 * @param element the root element of the tree.
 * @constructor
 */
var BacklogMenuController = function BacklogMenuController(element, toggleElement) {
  this.init(element, toggleElement);
};

BacklogMenuController.prototype.init = function(element, toggleElement) {
  this.parentElement = element;
  this.parentElement.html("");
  this.emptyListNote = $('<div style="margin: 0.5em">There are no backlogs yet</span>').appendTo(this.parentElement).hide();
  this.element = $('<div/>').appendTo(this.parentElement);
  this.toggleControl = toggleElement;
  this.tree = null;
  this.initTree();
};

BacklogMenuController.prototype.initTree = function() {
  var me = this;
  this.element.dynatree({
    keyboard: false,
    autoFocus: false,
    onClick: function(dtnode, event) {
      if ($(event.target).hasClass("ui-dynatree-title")) {
        window.location.href = event.target.href;
      }
    },
    onExpand: function(flag, dtnode) {
      var currentPageBacklogId = getCurrentPageBacklogId();
      me.element.find("a.ui-dynatree-title").each(function(key, item) {
        setLinkProperties(item, currentPageBacklogId);
      });
    },
    onPostInit: function(isReloading, isError) {
      var currentPageBacklogId = getCurrentPageBacklogId();
      //hack to get clicking the backlog name properly working
      me.element.find("a.ui-dynatree-title").each(function(key, item) {
        setLinkProperties(item, currentPageBacklogId);
      });

      // If some element (project, iteration) has just been created, make it visible in the dynatree
      var elementId = window.newElementId;
      if (elementId != null) {
        me.element.dynatree("getRoot").visit(function(node) {
          if (node.data.id == elementId) {
            node.makeVisible();
          }
        });
        window.newElementId = null;
      }
      
      var rootNode = me.element.dynatree("getRoot");
      if (rootNode.childList === null) {
        me.emptyListNote.show();
      }
      else {
        me.emptyListNote.hide();
      }
    },
    initAjax: {
      url: "ajax/menuData.action"
    },
    persist: true,
    clickFolderMode: 1,
    debugLevel: 0,
    cookieId: "agilefant-menu-dynatree"
  });

  this.tree = this.element.dynatree("getTree");
};

function setLinkProperties(item, currentPageBacklogId) {
  var itemId = $(item.parentNode).attr("dtnode").data.id;
  // hack to set properties for [Standalone iterations] which is not a real product
  if (itemId == -1) {
    item.href = "javascript:void(0);";
    item.setAttribute('style', 'color: #000 !important; cursor: default !important;');
  } else {
    item.href = "editBacklog.action?backlogId=" + itemId;
    if (currentPageBacklogId != null && itemId != null && currentPageBacklogId == itemId) {
      item.setAttribute('style', 'background-color: #D9DDE7 !important; font-weight: bold;');
    }
  }
}

function getCurrentPageBacklogId() {
  var iterationId = getUrlParameter('iterationId');
  if (iterationId != null) {
    return iterationId;
  }
  var projectId = getUrlParameter('projectId');
  if (projectId != null) {
    return projectId;
  }
  var productId = getUrlParameter('productId');
  if (productId != null) {
    return productId;
  }
  return null;
}

function getUrlParameter(name) {
  var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec($(location).attr('href'));
  if (results && results[1]) {
    return results[1];
  } else {
    return null;
  }
}

/**
 * Reload the backlog menu tree.
 * <p>
 * <strong>Note!</strong> Currently (version 0.5.1) dynatree plugin
 * has a bug with persistence and reloading. Should be fixed in version
 * 0.5.2.
 */
BacklogMenuController.prototype.reload = function() {
  this.tree.reload();
};


