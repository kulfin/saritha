package fi.hut.soberit.agilefant.db;


import java.util.List;

import fi.hut.soberit.agilefant.model.WidgetCollection;

public interface WidgetCollectionDAO extends GenericDAO<WidgetCollection> {

    /**
     * Get all <code>WidgetCollection</code>s.
     * <p>
     * Will retrieve all public collections
     * @return
     */
    public List<WidgetCollection> getCollections();
}