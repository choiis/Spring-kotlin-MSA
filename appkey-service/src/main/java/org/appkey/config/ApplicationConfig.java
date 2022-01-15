package org.appkey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
class ApplicationConfig extends AbstractCassandraConfiguration {

    //@Value("${spring.data.cassandra.port}")
    //public int port;

    @Value("${spring.data.cassandra.contact-points}")
    public String contactPoints;

    @Value("${spring.data.cassandra.local-datacenter}")
    public String datacenter;

    @Value("${spring.data.cassandra.keyspace-name}")
    public String keyspace;

    @Override
    public int getPort() {
        return 9042;
    }

    @Override
    public String getContactPoints() {
        return contactPoints;
    }


    @Override
    public String getLocalDataCenter() {
        return datacenter;
    }

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    public String[] getEntityBasePackages() {
        return new String[] { "org.appkey" };
    }

}