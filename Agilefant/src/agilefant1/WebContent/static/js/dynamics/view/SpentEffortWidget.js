var SpentEffortWidget = function SpentEffortWidget(model, onClose) {
  this.model = model;
  this.onClose = onClose;
  this.initDialog();
};

SpentEffortWidget.prototype.initDialog = function() {
  var me = this;
  this.element = $('<div/>');
  $('<h2>Effort logger on this element</h2>').appendTo(this.element);
  this.objectEffortEl = $('<div />').appendTo(this.element);
  $('<h2>My spent effort</h2>').appendTo(this.element);
  this.userEffortEl = $('<div />').addClass("ui-widget-content").addClass("ui-corner-all")
    .css({padding: "10px", "margin-bottom": "2em"}).appendTo(this.element);
 
  
  this.element.appendTo(document.body);
  this.element.dialog( {
    width : 800,
    position : 'top',
    modal : true,
    draggable : true,
    resizable : true,
    title : "Spent effort",
    close : function() {
      me.close();
    },
    buttons: {Close: function() { me.close(); }}
  });
  
  this.hourEntryListController = new HourEntryListController( {
    parentModel : this.model,
    hourEntryListElement : this.objectEffortEl,
    onUpdate: function() { me.entriesChanged(); }
  });
  this.userSpentEffort = new UserSpentEffortWidget(this.userEffortEl,
      window.pageController.getCurrentUser().getId());
  this.firstUpdateDone = false;
};

SpentEffortWidget.prototype.entriesChanged = function() {
  this.userSpentEffort.reload();
  // Focus on close button only after the initial update where the focus is set on the effort spent box
  if (this.firstUpdateDone == true) {
    jQuery(this.element[0]).parent().find('Button').focus();
  } else {	
    this.firstUpdateDone = true;
  }
};

/**
 * Close and destroy the dialog.
 */
SpentEffortWidget.prototype.close = function() {
  this.element.dialog('destroy').remove();
  if(this.model instanceof TaskModel) {
    this.model.reload();
    this.reloadParent();
  } else if(this.model instanceof StoryModel) {
    this.model.reloadMetrics();
    this.reloadParent();
  } else if(this.model instanceof IterationModel) {
    this.model.reloadMetrics();
    this.reloadParent();
  }
  else if(this.model instanceof ProjectModel) {
    this.model.reloadTotalSpentEffort();
  }
  if(this.onClose) {
    this.onClose();
  }
};

// Reloads the project total hours in editProject.action page
SpentEffortWidget.prototype.reloadParent = function() {
  var parent = this.model.getParent();
  var currentLastSegment = getCurrentLastSegment();
  if (currentLastSegment == "editProject.action") {
    if (parent instanceof ProjectModel) {
      parent.reloadTotalSpentEffort();
    } else if (parent instanceof StoryModel) {
      var parentParent = parent.getParent();
      if (parentParent instanceof ProjectModel) {
        parentParent.reloadTotalSpentEffort();
      }
    }
  }
};

function getCurrentLastSegment() {
  var url = $(location).attr('href');
  return (getUrlLastSegment(url));
}

