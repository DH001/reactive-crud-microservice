package dev.davidhiggins.crudmicroservice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.adapter.rxjava.toFlowable
import java.util.UUID

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