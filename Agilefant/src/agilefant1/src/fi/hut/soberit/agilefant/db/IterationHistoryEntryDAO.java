package fi.hut.soberit.agilefant.db;


import java.util.List;

import org.joda.time.LocalDate;

import fi.hut.soberit.agilefant.model.ExactEstimate;
import fi.hut.soberit.agilefant.model.IterationHistoryEntry;
import fi.hut.soberit.agilefant.util.Pair;

public interface IterationHistoryEntryDAO extends
        GenericDAO<IterationHistoryEntry> {

    IterationHistoryEntry retrieveLatest(int iterationId);

    Pair<ExactEstimate, ExactEstimate> calculateCurrentHistoryData(int iterationId);

    public List<IterationHistoryEntry> getHistoryEntriesForIteration(
            int iterationId);

    IterationHistoryEntry retrieveByDate(int iterationId, LocalDate timestamp);
    
}
