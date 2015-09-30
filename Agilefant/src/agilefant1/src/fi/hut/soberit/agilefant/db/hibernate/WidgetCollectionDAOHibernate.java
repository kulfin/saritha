package fi.hut.soberit.agilefant.db.hibernate;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import fi.hut.soberit.agilefant.db.WidgetCollectionDAO;
import fi.hut.soberit.agilefant.model.WidgetCollection;

@Repository("widgetCollectionDAO")
public class WidgetCollectionDAOHibernate extends
        GenericDAOHibernate<WidgetCollection> implements WidgetCollectionDAO {
    
    public WidgetCollectionDAOHibernate() {
        super(WidgetCollection.class);
    }
    
    /** {@inheritDoc} */
    public List<WidgetCollection> getCollections() {
        Criteria collectionCriteria = this.createCriteria(WidgetCollection.class);
        collectionCriteria.addOrder(Order.asc("name"));
        return asList(collectionCriteria);
    }
}
