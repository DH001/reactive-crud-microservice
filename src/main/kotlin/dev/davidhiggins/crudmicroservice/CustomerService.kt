package dev.davidhiggins.crudmicroservice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomerService(val customerRepository: CustomerRepository) {

    fun findAll(): Flow<Customer> = customerRepository
        .findAll()
        .asFlow()

    suspend fun findById(id: String): Customer? = customerRepository
        .findById(id)
        .awaitFirstOrNull()

    suspend fun create(request: Mono<CreateCustomerRequest>): Customer =
        request
            .awaitFirst()
            .let {  customerRepository.save(Customer(name = it.name)) }
            .awaitFirst()

    suspend fun delete(id: String): Void? =
         customerRepository.deleteById(id)
             .awaitFirstOrNull()

}