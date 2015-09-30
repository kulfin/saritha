
var AutocompleteSelectAllItems = function AutocompleteSelectAllItems(element, dataType, parent, opts) {
  this.element = element;
  this.parent = parent;
  this.options = {
      caption: "Add all"
  };
};

AutocompleteSelectAllItems.prototype.initialize = function() {
};

AutocompleteSelectAllItems.prototype.render = function() {
  var me = this;
  
  this.caption = $('<span/>').text(this.options.caption).appendTo(this.element);
  this.caption.addClass(AutocompleteVars.cssClasses.selectAllItemsElement);
  
  var select = function() {
    var allItems = me.parent.getAllItems();
    $.each(allItems, function(k,v) {
      me.parent.selectItem(v);
    });
  };
  
  this.caption.click(select);
};


