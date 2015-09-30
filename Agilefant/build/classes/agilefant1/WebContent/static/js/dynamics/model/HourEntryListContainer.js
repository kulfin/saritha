/**
 * Transient container class for hour entries.
 * @constructor
 * @base CommonModel
 */
var HourEntryListContainer = function HourEntryListContainer() {
  this.initialize();
  this.persistedClassName = "non.existent.HourEntryList";
  this.relations = {
    hourEntry: [],
    parent: null
  };
  this.copiedFields = { };
  this.classNameToRelation = {
      "fi.hut.soberit.agilefant.model.HourEntry":  "hourEntry"
  };
};

HourEntryListContainer.initializeFor = function(parent, callback, limited) {
  var object = new HourEntryListContainer();
  object.setParent(parent);
  object.limitedEntries = limited;
  object.fillData(callback);
};

HourEntryListContainer.prototype = new CommonModel();

/**
 * Internal function to set data
 * @see CommonModel#setData
 */
HourEntryListContainer.prototype._setData = function(newData) {
  if (newData) {
    this._updateRelations(ModelFactory.types.hourEntry, newData);
    for (var i = 0; i < this.relations.hourEntry.length; i++) {
    	this.relations.hourEntry[i].setHourEntryList(this);
    }
  }
};

HourEntryListContainer.prototype.reload = function() {
  if(!this.relations.parent) {
    return;
  }
  this.fillData();
  this.relationChanged = true;
};

HourEntryListContainer.prototype.fillData = function(callback) {
  var me = this;
  var url = "";
  if (!this.limitedEntries) {
    this.limitedEntries = false;
  }
  if(this.relations.parent instanceof BacklogModel) {
    url = "ajax/retrieveBacklogHourEntries.action";
  } else if(this.relations.parent instanceof StoryModel) {
    url = "ajax/retrieveStoryHourEntries.action";
  } else if(this.relations.parent instanceof TaskModel) {
    url = "ajax/retrieveTaskHourEntries.action";
  }
  var params = {
    parentObjectId: this.relations.parent.getId(),
    limited: this.limitedEntries
  };
  jQuery.getJSON(
      url,
      params,
      function(data,status) {
        me.setData(data);
        if (callback) {
          callback(me);
        }
        me.callListeners(new DynamicsEvents.EditEvent(me));
      });
};

HourEntryListContainer.prototype.setLimitedEntries = function(val) {
  this.limitedEntries = val;
};

HourEntryListContainer.prototype.setParent = function(parent) {
  this.relations.parent = parent;
};

/* GETTERS */

HourEntryListContainer.prototype.getHourEntries = function(enabled) {
  return this.relations.hourEntry;
};
