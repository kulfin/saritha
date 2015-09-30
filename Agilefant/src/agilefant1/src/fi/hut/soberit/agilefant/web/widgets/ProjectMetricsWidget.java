package fi.hut.soberit.agilefant.web.widgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fi.hut.soberit.agilefant.business.AuthorizationBusiness;
import fi.hut.soberit.agilefant.business.ProjectBusiness;
import fi.hut.soberit.agilefant.model.Project;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;
import fi.hut.soberit.agilefant.transfer.ProjectMetrics;

@Scope("prototype")
@Component("projectMetricsWidget")
public class ProjectMetricsWidget extends CommonWidget {

    private static final long serialVersionUID = 8742527799375741424L;
    
    @Autowired
    private ProjectBusiness projectBusiness;
    
    private Project project;
    private ProjectMetrics projectMetrics;
    
    @Autowired
    private AuthorizationBusiness authorizationBusiness;
    
    @Override
    public String execute() {
        project = projectBusiness.retrieve(getObjectId());
        projectMetrics = projectBusiness.getProjectMetrics(project);
        return SUCCESS;
    }
    
    public boolean getAccess() {
        User user = SecurityUtil.getLoggedUser();
        return this.authorizationBusiness.isBacklogAccessible(project.getId(), user);
    }
    
    public Project getProject() {
        return project;
    }

    public ProjectMetrics getProjectMetrics() {
        return projectMetrics;
    }

}
