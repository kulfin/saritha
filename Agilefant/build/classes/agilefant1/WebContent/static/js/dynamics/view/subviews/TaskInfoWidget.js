var TaskInfoWidget = function TaskInfoWidget(model, controller, parentView) {
  this.parentView = parentView;
  this.element = parentView.getElement();
  this.model = model;
  this.controller = controller;
};

TaskInfoWidget.prototype = new CommonSubView();

TaskInfoWidget.prototype.taskContextFactory = function(cellView, taskModel) {
  return new CellBubble({
    title: 'Context',
    url: 'ajax/getTaskHierarchy.action?taskId=' + taskModel.getId(),
    text: '<a href="javascript:void(0);"> Show hierarchy</a>'
  }, cellView);
};



TaskInfoWidget.prototype.renderAlways = function() {
  return true;
};

TaskInfoWidget.prototype.getElement = function() {
  return this.container;
};

TaskInfoWidget.prototype._draw = function() {
  this.container = $('<div />');
  
  this.hr = $('<div class="ruler">&nbsp;</div>').appendTo(this.container);

  var config = new DynamicTableConfiguration({
    leftWidth: '10%',
    rightWidth: '88%',
    closeRowCallback: null
  });
  config.addColumnConfiguration(0, {
    title: "Reference ID",
    get: TaskModel.prototype.getId,
    decorator: DynamicsDecorators.quickReference
  });
  config.addColumnConfiguration(1, {
    title: 'Description',
    get : TaskModel.prototype.getDescription,
    decorator: DynamicsDecorators.emptyDescriptionDecorator,
    editable : true,
    visualizedEditable: true,
    edit : {
      editor : "Wysiwyg",
      set : TaskModel.prototype.setDescription
    }
  });
  this.taskInfo = new DynamicVerticalTable(this, this.model, config, this.container);
  
  this.container.appendTo(this.parentView.getElement());
};

TaskInfoWidget.prototype.onEdit = function() {
  this.render();
};

TaskInfoWidget.prototype.render = function() {
  this.taskInfo.render();
};
