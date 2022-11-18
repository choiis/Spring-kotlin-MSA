package org.api.config

import com.github.springtestdbunit.bean.DatabaseConfigBean
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@TestConfiguration
class TestDBUnitConfig {

    @Bean
    fun dbUnitDatabaseConfig() : DatabaseConfigBean? {
        var config = DatabaseConfigBean()
        config.allowEmptyFields = true
        config.datatypeFactory = HsqldbDataTypeFactory()
        return config;
    }

    @Bean
    fun dbUnitDatabaseConnection(dataSource: DataSource?) : DatabaseDataSourceConnectionFactoryBean? {
        var dbUnitDatabaseConnection = DatabaseDataSourceConnectionFactoryBean()
        dbUnitDatabaseConnection.setDataSource(dataSource)
        dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig())
        return dbUnitDatabaseConnection
    }
}