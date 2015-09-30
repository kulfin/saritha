package fi.hut.soberit.agilefant.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.typesafe.config.Config;

public class ConfigFactoryBean implements FactoryBean<Config>, InitializingBean {

    static final String CONFIG_ATTR = Config.class.getName();

    @Autowired
    private ServletContext servletContext;

    private Config object;

    @Override
    public Config getObject() throws Exception {
        return object;
    }

    @Override
    public Class<?> getObjectType() {
        return Config.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        object = Preconditions.checkNotNull((Config) servletContext.getAttribute(CONFIG_ATTR), "Config could not be found in the servlet context");
    }

}
