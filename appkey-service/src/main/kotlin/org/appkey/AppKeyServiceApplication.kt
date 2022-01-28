package org.appkey

import org.springframework.data.cassandra.repository.CassandraRepository
import lombok.NoArgsConstructor
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.ToString
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.appkey.dao.AppkeyRepository
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.http.ResponseEntity
import java.util.NoSuchElementException
import org.appkey.service.AppKeyService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.appkey.AppKeyServiceApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus

@SpringBootApplication
@RestController
open class AppKeyServiceApplication

fun main(args: Array<String>) {
    runApplication<AppKeyServiceApplication>(*args)
}
