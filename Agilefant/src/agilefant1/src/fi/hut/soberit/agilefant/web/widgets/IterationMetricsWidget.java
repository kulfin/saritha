package fi.hut.soberit.agilefant.web.widgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fi.hut.soberit.agilefant.business.AuthorizationBusiness;
import fi.hut.soberit.agilefant.business.IterationBusiness;
import fi.hut.soberit.agilefant.model.Iteration;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;
import fi.hut.soberit.agilefant.transfer.IterationMetrics;


@Component("iterationMetricsWidget")
@Scope("prototype")
public class IterationMetricsWidget extends CommonWidget {
    private static final long serialVersionUID = 4029492283643549647L;
    private Iteration iteration;
    private IterationMetrics iterationMetrics;

    @Autowired
    private IterationBusiness iterationBusiness;
    
    @Autowired
    private AuthorizationBusiness authorizationBusiness;
    
    @Override
    public String execute() {
        iteration = iterationBusiness.retrieve(getObjectId());
        iterationMetrics = iterationBusiness.getIterationMetrics(iteration);
        return SUCCESS;
    }
    
    public boolean getAccess() {
        User user = SecurityUtil.getLoggedUser();
        return this.authorizationBusiness.isBacklogAccessible(iteration.getId(), user);
    }

    public Iteration getIteration() {
        return iteration;
    }

    public IterationMetrics getIterationMetrics() {
        return iterationMetrics;
    }

}
