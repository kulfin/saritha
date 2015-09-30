/*
 * Autocomplete classes for creating dialoged and non dialoged
 * search boxes.
 */

var AutocompleteVars = {
    cssClasses: {
      autocompleteDialog: 'autocomplete-dialog',
      
      leftPanel: 'autocomplete-left',
      rightPanel: 'autocomplete-right',
  
      autocompleteElement: 'autocomplete',
      searchParent: 'autocomplete-searchBoxContainer',
      selectedParent: 'autocomplete-selectedBoxContainer',
      suggestionList: 'autocomplete-suggestionList',
      selectedLi: 'autocomplete-selected',
      selectedItemsList: 'autocomplete-selectedItemsList',
      selectedItemName: 'autocomplete-selectedName',
      selectedItemRemove: 'autocomplete-selectedRemove',
      
      recentElement: 'autocomplete-recentBox',
      recentList: 'autocomplete-recentList',
      
      selectAllItemsElement: 'autocomplete-selectAllItemsBox',
      
      removeAllItemsElement: 'autocomplete-removeAllItemsBox',
      
      suggestionIcon: 'autocomplete-suggestionIcon',
      suggestionUserIcon: 'autocomplete-userIcon',
      suggestionTeamIcon: 'autocomplete-teamIcon',
      suggestionBacklogIcon: 'autocomplete-backlogIcon',
      noResultsIcon: 'autocomplete-noResultsIcon'
    },
    keyCodes: {
      enter: 13,
      esc:   27,
      down:  40,
      up:    38
    },
    inputWaitTime: 500
};

/**
 * Constructor for the class Autocomplete.
 * 
 * @param element the element in DOM to construct the Autocomplete in
 * @param options
 * @constructor
 */
var Autocomplete = function Autocomplete(element, options) {
  this._init(element, options);
};

/**
 * Internal init function
 */
Autocomplete.prototype._init = function(element, options) {
  this.parent = element;
  this.items = [];
  this.leftPanel = $('<div/>').addClass(AutocompleteVars.cssClasses.leftPanel);
  this.rightPanel = $('<div/>').addClass(AutocompleteVars.cssClasses.rightPanel);
  this.searchBoxContainer = $('<div/>');
  this.selectedBoxContainer = $('<div/>');
  this.selectAllItemsContainer = $('<div/>');
  this.removeAllItemsContainer = $('<div/>');
  this.recentContainer = $('<div/>');
  this.options = {
      multiSelect: true,
      dataType: "",
      params: {},
      preSelected: [],
      visibleSuggestions: 5,
      showRecent: true,
      showSelectAllItems: false,
      showRemoveAllItems: false
  };
  jQuery.extend(this.options, options);

  this.dataProvider = null;
  this.selectedBox = new AutocompleteSelected(this);
  this.searchBox = new AutocompleteSearch(this);
  this.recentBox = new AutocompleteRecent(this.recentContainer, this.options.dataType, this, {});  
  this.selectAllItemsBox = new AutocompleteSelectAllItems(this.selectAllItemsContainer, this.options.dataType, this, {});
  this.removeAllItemsBox = new AutocompleteRemoveAllItems(this.removeAllItemsContainer, this.options.dataType, this, {});
};


/**
 * Initialize the <code>Autocomplete</code> selector.
 * <p>
 * Will check whether single or multi select should be used.
 */
Autocomplete.prototype.initialize = function() {
  this.element = $('<div/>').addClass(AutocompleteVars.cssClasses.autocompleteElement)
    .appendTo(this.parent);
  
  if (this.options.showRecent) {
    this.rightPanel.appendTo(this.element);
  }
  this.leftPanel.appendTo(this.element);
  this.dataProvider = AutocompleteDataProvider.getInstance();
  
  if (this.options.showSelectAllItems) {
    this.element.addClass('autocomplete-selectallitems-visible');
    this._initializeSelectAllItems();
  }
  
  if (this.options.showRemoveAllItems) {
    this.element.addClass('autocomplete-removeallitems-visible');
    this._initializeRemoveAllItems();
  }
  
  this.searchBoxContainer.appendTo(this.leftPanel);
  
  if (this.options.multiSelect) {
    this._initializeMultiSelect();
  }
  else {
    this._initializeSingleSelect();
  }
  
  if (this.options.showRecent) {
    this.element.addClass('autocomplete-recent-visible');
    this._initializeRecent();
  }
  
  this.searchBox.focus();
};

Autocomplete.prototype._initializeMultiSelect = function() {
  this.selectedBoxContainer.appendTo(this.leftPanel);
  
  this.getData();
  
  this.searchBox.setItems(this.items);
  this.selectedBox.setItems(this.dataProvider.filterIdLists(this.items));
  
  this.searchBox.initialize(this.searchBoxContainer);
  this.selectedBox.initialize(this.selectedBoxContainer);
  
  this.setSelected(this.options.preSelected);
};

Autocomplete.prototype._initializeSingleSelect = function() {
  this.getData();
  this.searchBox.setItems(this.items);
  this.searchBox.initialize(this.searchBoxContainer);
};

Autocomplete.prototype._initializeRecent = function() {
  this.recentBox.initialize();
  
  if (this.options.showRecent) {
    this.recentContainer.addClass(AutocompleteVars.cssClasses.recentElement)
        .appendTo(this.rightPanel);
    this.recentBox.render();
  }
};

Autocomplete.prototype._initializeSelectAllItems = function() {
  this.selectAllItemsBox.initialize();
  
  if (this.options.showSelectAllItems) {
    this.selectAllItemsContainer.addClass(AutocompleteVars.cssClasses.selectAllItemsElement)
        .appendTo(this.leftPanel);
    this.selectAllItemsBox.render();
  }
};

Autocomplete.prototype._initializeRemoveAllItems = function() {
  this.removeAllItemsBox.initialize();
  
  if (this.options.showRemoveAllItems) {
    this.removeAllItemsContainer.addClass(AutocompleteVars.cssClasses.removeAllItemsElement)
        .appendTo(this.leftPanel);
    this.removeAllItemsBox.render();
  }
};

Autocomplete.prototype.setSelected = function(selected) {
  for(var i = 0; i < selected.length; i++) { 
    this.selectedBox.addItemById(selected[i]);
  }
};

Autocomplete.prototype.selectItem = function(item) {
  this.recentBox.pushToRecent(item);
  if (this.options.multiSelect) {
    this.selectedBox.addItem(item);
    this.searchBox.clearInput();
  }
  else {
    this.options.selectCallback(item);
  }
};

/**
 * Calls <code>selectCallback</code> if given and in multiselect mode. 
 */
Autocomplete.prototype.selectAndClose = function() {
  if (this.options.multiSelect && this.options.selectCallback) {
    this.options.selectCallback();
  }
};

Autocomplete.prototype.getData = function() {
  this.items = this.dataProvider.get(this.options.dataType, this.options.params);
};

Autocomplete.prototype.setSearchBoxValue = function(val) {
  this.searchBox.setSearchBoxValue(val);
};

Autocomplete.prototype.focusSearchField = function() {
  this.searchBox.focus();
};

Autocomplete.prototype.remove = function() {
  this.element.remove();
};

Autocomplete.prototype.getSelectedIds = function() {
  return this.selectedBox.getSelectedIds();
};

Autocomplete.prototype.isItemSelected = function(id) {
  return this.selectedBox.isItemSelected(id);
};

Autocomplete.prototype.getSelectedItems = function() {
  return this.selectedBox.getSelectedItems();
};

Autocomplete.prototype.getItemsByIdList = function(idList) {
  if (!idList) {
    return [];
  }
    
  var list = [];
  var items = this.dataProvider.filterIdLists(this.items);
  for (var i = 0; i < items.length; i++) {
    if (jQuery.inArray(items[i].id, idList) !== -1) {
      list.push(items[i]);
    }
  }
  return list;
};

Autocomplete.prototype.getAllItems = function() {
  return this.items;
};

Autocomplete.prototype.removeAllSelected = function() {
  this.selectedBox.removeAllItems();
};

