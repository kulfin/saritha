
var AutocompleteRemoveAllItems = function AutocompleteRemoveAllItems(element, dataType, parent, opts) {
  this.element = element;
  this.parent = parent;
  this.options = {
      caption: "Remove all"
  };
};

AutocompleteRemoveAllItems.prototype.initialize = function() {
};

AutocompleteRemoveAllItems.prototype.render = function() {
  var me = this;
  
  this.caption = $('<span/>').text(this.options.caption).appendTo(this.element);
  this.caption.addClass(AutocompleteVars.cssClasses.removeAllItemsElement);
  
  var remove = function() {
    me.parent.removeAllSelected();
  };
  
  this.caption.click(remove);
};


