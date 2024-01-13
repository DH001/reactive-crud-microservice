package dev.davidhiggins.crudmicroservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

class ControllerConfig {

    @Configuration
    class ControllerConfig {

        @Bean
        fun http(customerService: CustomerService) = coRouter {
            GET("/customers") {
                ServerResponse.ok().bodyAndAwait(customerService.findAll())
            }

            GET("/customers/{id}") {
                val id = it.pathVariable("id")
                customerService.findById(id)?.let { customer ->
                    ServerResponse.ok().bodyValueAndAwait(customer)
                } ?: ServerResponse.notFound().buildAndAwait()
            }

            POST("/customers") {
                val request = it.bodyToMono(CreateCustomerRequest::class.java)
                customerService.create(request).let { customer ->
                    ServerResponse.ok().bodyValueAndAwait(customer)
                }
            }
        }
    }
}

data class CreateCustomerRequest(
    val name: String
)