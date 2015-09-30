package fi.hut.soberit.agilefant.business;

import java.util.List;

import fi.hut.soberit.agilefant.model.NamedObject;
import fi.hut.soberit.agilefant.transfer.SearchResultRow;

public interface SearchBusiness {
	// Limit search results. Without this limitation, listing all results in user interface is slow if there are hundreds of results,
	// e.g. in dashboard page when listing all stories.
    public static final int MAX_RESULTS_PER_TYPE = 500;
    
    public NamedObject searchByReference(String searchTerm);
    
    public List<SearchResultRow> searchStoriesAndBacklog(String searchTerm);
    public List<SearchResultRow> searchStories(String searchTerm);
    public List<SearchResultRow> searchIterations(String searchTerm);
    public List<SearchResultRow> searchProjects(String searchTerm);
    public List<SearchResultRow> searchUsers(String searchTerm);
    public List<SearchResultRow> searchTasks(String searchTerm);
    
}
