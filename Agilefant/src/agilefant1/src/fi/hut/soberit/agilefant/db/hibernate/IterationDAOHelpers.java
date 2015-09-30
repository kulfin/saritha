package fi.hut.soberit.agilefant.db.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Interval;

public class IterationDAOHelpers {
    static void addIterationIntervalLimit(Criteria crit, Interval interval) {
        //search only from iterations
        crit.add(Restrictions.eq("class", "Iteration"));
        DateTime startDate = interval.getStart();
        DateTime endDate = interval.getEnd();
        //iteration may start during the interval
        Criterion startDateLimit = Restrictions.between("startDate", startDate,
                endDate);
        //iteration end during the interval
        Criterion endDateLimit = Restrictions.between("endDate", startDate,
                endDate);
        //interval may be within the iteration
        Criterion overlaps = Restrictions.or(startDateLimit, endDateLimit);
        Criterion withinIteration = Restrictions.and(Restrictions.le(
                "startDate", startDate), Restrictions.ge("endDate", endDate));
        crit.add(Restrictions.or(overlaps, withinIteration));
    }
    
    static void addBacklogIntervalLimit(Criteria crit, Interval interval) {
        DateTime startDate = interval.getStart();
        DateTime endDate = interval.getEnd();
        //backlog may start during the interval
        Criterion startDateLimit = Restrictions.between("startDate", startDate,
                endDate);
        //backlog end during the interval
        Criterion endDateLimit = Restrictions.between("endDate", startDate,
                endDate);
        //interval may be within the backlog
        Criterion overlaps = Restrictions.or(startDateLimit, endDateLimit);
        Criterion withinIteration = Restrictions.and(Restrictions.le(
                "startDate", startDate), Restrictions.ge("endDate", endDate));
        
        // If backlog is product then ignore the start and end date (because product doesnt have enddate startdate)
        Criterion timeConstraint = Restrictions.or(overlaps, withinIteration);
        Criterion backlogProduct = Restrictions.isNull("startDate");
        crit.add(Restrictions.or(timeConstraint, backlogProduct));
    }
    
    static void addIterationIntervalStartLimit(Criteria crit, Interval interval) {
        //search only from iterations
        crit.add(Restrictions.eq("class", "Iteration"));
        DateTime startDate = interval.getStart();
        DateTime endDate = interval.getEnd();
        //iteration may start during the interval
        Criterion startDateLimit = Restrictions.between("startDate", startDate,
                endDate);
        //iteration end during the interval
        Criterion endDateLimit = Restrictions.between("endDate", startDate,
                endDate);
        //interval may be within the iteration or after the iteration start date
        Criterion overlaps = Restrictions.or(startDateLimit, endDateLimit);
        Criterion iterationStarted = Restrictions.le("startDate", startDate);
        crit.add(Restrictions.or(overlaps, iterationStarted));
    }
    
    static void addBacklogIntervalStartLimit(Criteria crit, Interval interval) {
        DateTime startDate = interval.getStart();
        DateTime endDate = interval.getEnd();
        //backlog may start during the interval
        Criterion startDateLimit = Restrictions.between("startDate", startDate,
                endDate);
        //backlog end during the interval
        Criterion endDateLimit = Restrictions.between("endDate", startDate,
                endDate);
        //interval may be within the backlog or after the backlog start date
        Criterion overlaps = Restrictions.or(startDateLimit, endDateLimit);
        Criterion withinIteration = Restrictions.le("startDate", startDate);
        
        // If backlog is product then ignore the start and end date (because product doesnt have enddate startdate)
        Criterion timeConstraint = Restrictions.or(overlaps, withinIteration);
        Criterion backlogProduct = Restrictions.isNull("startDate");
        crit.add(Restrictions.or(timeConstraint, backlogProduct));
    }
}
