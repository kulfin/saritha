package fi.hut.soberit.agilefant.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "fi.hut.soberit.agilefant.business.impl")
public class BusinessConfiguration {

}
