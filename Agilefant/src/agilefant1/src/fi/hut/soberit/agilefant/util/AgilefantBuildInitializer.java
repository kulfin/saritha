package fi.hut.soberit.agilefant.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AgilefantBuildInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            AgilefantBuild.initialize(sce.getServletContext().getResource("META-INF/MANIFEST.MF"));
        } catch (Exception e) {
            sce.getServletContext().log("Failed to fetch agilefant build information");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
