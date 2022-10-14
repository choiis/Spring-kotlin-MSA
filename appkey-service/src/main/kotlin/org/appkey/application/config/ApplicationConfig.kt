package org.appkey.application.config

import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCassandraRepositories
class ApplicationConfig : AbstractCassandraConfiguration() {
    //@Value("${spring.data.cassandra.port}")
    //public int port;
    @Value("\${spring.data.cassandra.contact-points}")
    private lateinit var  contactPoints: String

    @Value("\${spring.data.cassandra.local-datacenter}")
    private lateinit var  datacenter: String

    @Value("\${spring.data.cassandra.keyspace-name}")
    private lateinit var  keyspace: String

    public override fun getPort(): Int {
        return 9042
    }

    public override fun getContactPoints(): String {
        return contactPoints
    }

    public override fun getLocalDataCenter(): String {
        return datacenter
    }

    override fun getKeyspaceName(): String {
        return keyspace
    }

    override fun getEntityBasePackages(): Array<String> {
        return arrayOf("org.appkey")
    }
}