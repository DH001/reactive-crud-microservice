package dev.davidhiggins.crudmicroservice

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator


@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Configuration
@EnableR2dbcRepositories
class CustomConnectionFactoryInitializer  {
	@Bean
	fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
		val initializer = ConnectionFactoryInitializer()
		initializer.setConnectionFactory(connectionFactory)
		initializer.setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))

		return initializer
	}
}
