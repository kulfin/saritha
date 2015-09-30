package fi.hut.soberit.agilefant.transfer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Interval;

import flexjson.JSON;


public class IntervalLoadContainer {
    private Interval interval;
    private List<BacklogLoadContainer> detailedLoad = new ArrayList<BacklogLoadContainer>();
    private long assignedLoad = 0L;
    private long baselineLoad = 0L;
    private long unassignedLoad = 0L;
    private long workHours = 0L;
    private long futureLoad = 0L;

    public long getAssignedLoad() {
        return assignedLoad;
    }
    public void setAssignedLoad(long assignedLoad) {
        this.assignedLoad = assignedLoad;
    }
    public long getTotalLoad() {
        return assignedLoad + baselineLoad + unassignedLoad + futureLoad;
    }
    public long getWorkHours() {
        return workHours;
    }
    public void setWorkHours(long workHours) {
        this.workHours = workHours;
    }
    @JSON(include=false)
    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
    public long getBaselineLoad() {
        return baselineLoad;
    }
    public void setBaselineLoad(long basellineLoad) {
        this.baselineLoad = basellineLoad;
    }
    public long getUnassignedLoad() {
        return unassignedLoad;
    }
    public void setUnassignedLoad(long unassignedLoad) {
        this.unassignedLoad = unassignedLoad;
    }
    public Date getStart() {
        return this.interval.getStart().toDate();
    }
    public Date getEnd() {
        return this.interval.getEnd().toDate();
    }
    public long getFutureLoad() {
        return futureLoad;
    }
    public void setFutureLoad(long futureLoad) {
        this.futureLoad = futureLoad;
    }
    @JSON
    public List<BacklogLoadContainer> getDetailedLoad() {
        return detailedLoad;
    }
    public void setDetailedLoad(List<BacklogLoadContainer> detailedLoad) {
        this.detailedLoad = detailedLoad;
    }
}
