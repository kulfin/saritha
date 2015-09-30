package fi.hut.soberit.agilefant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import fi.hut.soberit.agilefant.business.AuthorizationBusiness;
import fi.hut.soberit.agilefant.business.BacklogBusiness;
import fi.hut.soberit.agilefant.business.IterationBusiness;
import fi.hut.soberit.agilefant.exception.ObjectNotFoundException;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.security.SecurityUtil;

@Component("authorizationInterceptor")
public class AuthorizationInterceptor implements Interceptor {

	private static final long serialVersionUID = 540485098014612613L;

	@Autowired
    private BacklogBusiness backlogBusiness;
    
    @Autowired
    private IterationBusiness iterationBusiness;
   
    @Autowired
    private AuthorizationBusiness authorizationBusiness;
    
    @Override
    public void destroy() {}

    @Override
    public void init() {}
    
    private static int readonlyId = -1;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        
        boolean accessDenied = false;
        
        //check read only user permissions
        User currentUser = SecurityUtil.getLoggedUser();
        if(!(action instanceof ROIterationAction 
                || action instanceof ChartAction
                || action instanceof IterationAction
                || action instanceof IterationHistoryAction
                || action instanceof StoryAction) 
                && currentUser.getLoginName().equals("readonly")){
            
            return "login";
        } else if (action instanceof ROIterationAction && currentUser.getLoginName().equals("readonly")) {
            // Store readonly id when ROIterationAction is called.
            // Used for verification below.
            
            ROIterationAction readonlyAction = (ROIterationAction) action; 
            readonlyId = readonlyAction.getIteration().getId();
            
            return invocation.invoke();
        } else if((action instanceof ChartAction
                || action instanceof IterationAction
                || action instanceof IterationHistoryAction
                || action instanceof StoryAction)
                && currentUser.getLoginName().equals("readonly")){
            
            // Readonly user is calling other actions, make sure the id's match.
            
            int id = -1;
            
            if(action instanceof IterationAction){
                id = ((IterationAction) action).getIterationId();
            } else if(action instanceof IterationHistoryAction){
                id = ((IterationHistoryAction) action).getIterationId();
            } else if(action instanceof StoryAction){
                id = ((StoryAction) action).getIterationId();
            } else if(action instanceof ChartAction){
                //id is always 0, so not set properly, so just set to readonlyid instead
                ((ChartAction) action).setBacklogId(readonlyId);     
                id = ((ChartAction) action).getBacklogId();
            }
            
            if(id != readonlyId){
                return "noauth";
            } else {
                return invocation.invoke();
            }
        }
        try {
        	//matrix authorizations
            if (action instanceof BacklogAction){
                accessDenied = !checkAccess(((BacklogAction) action).getBacklogId()); 
            } else if (action instanceof ProductAction) {
                accessDenied = !checkAccess(((ProductAction) action).getProductId());  
            } else if (action instanceof ProjectAction) {
                accessDenied = !checkAccess(((ProjectAction) action).getProjectId());      
            } else if (action instanceof IterationAction) {
                accessDenied = !checkAccess(((IterationAction) action).getIterationId());
            } else if (action instanceof StoryAction) {
                accessDenied = !checkAccess(((StoryAction)action).getIterationId());
            } else if (action instanceof TaskAction) {
                if(((TaskAction)action).getTask().getIteration() != null){
                    accessDenied = !checkAccess(((TaskAction)action).getTask().getIteration().getId());
                } else {
                    accessDenied = !checkAccess(((TaskAction)action).getParentStory().getIteration().getId());
                }
            }
            
            else {
                //admin authorizations
                currentUser = SecurityUtil.getLoggedUser();
                boolean isAdmin = currentUser.isAdmin();
                
                if(isAdmin){
                    //admin has access to any actions
                    return invocation.invoke();
                } else {
                    if(action instanceof AccessAction || 
                       action instanceof DatabaseExportAction ||
                       action instanceof SettingAction){
                             return "notadmin";
                    } else {
                        return invocation.invoke();
                    }
                }
            }
        } catch (ObjectNotFoundException e) {
        	return "notfound";
        }
        
        
        if (accessDenied) return "noauth";
            return invocation.invoke();
    }

    // check from the backlogId if the associated product is accessible for the current user    
    private boolean checkAccess(int backlogId){
        User user = SecurityUtil.getLoggedUser();
        return this.authorizationBusiness.isBacklogAccessible(backlogId, user);
    }
}