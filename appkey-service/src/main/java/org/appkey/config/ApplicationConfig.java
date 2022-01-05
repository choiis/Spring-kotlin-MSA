package org.appkey.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
class ApplicationConfig extends AbstractCassandraConfiguration {

    @Override
    public int getPort() {
        return 9042;
    }

    @Override
    public String getContactPoints() {
        return "192.168.35.204";
    }


    @Override
    public String getLocalDataCenter() {
        return "dc1";
    }

    @Override
    protected String getKeyspaceName() {
        return "keys";
    }

    public String[] getEntityBasePackages() {
        return new String[] { "org.appkey" };
    }

}