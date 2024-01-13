package dev.davidhiggins.crudmicroservice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.adapter.rxjava.toFlowable

@Component
class CustomerService(val customerRepository: CustomerRepository) {

    fun findAll(): Flow<Customer> = customerRepository.findAll().asFlow()

    suspend fun findById(id: String): Customer? = customerRepository.findById(id).awaitFirst()

    suspend fun create(request: Mono<CreateCustomerRequest>): Customer =
        request.awaitFirst()
            .let {  customerRepository.save(Customer( name = it.name)) }.awaitFirst()

}