package fi.hut.soberit.agilefant.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

/**
 * Class will creates a dummy charts object so that JFreeCharts work with new EC2 AMIs
 * 
 * @author jkorri
 */
public class InitJFreeChartsListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Init JFreeChart so that compatible with Amazon m3 amis
    	JFreeChart chart = ChartFactory.createTimeSeriesChart("",
                "",
                "",
                null, true, true, false);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
