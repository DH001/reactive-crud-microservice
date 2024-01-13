package dev.davidhiggins.crudmicroservice

import org.springframework.data.annotation.Id
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface CustomerRepository: R2dbcRepository<Customer, String>

data class Customer(
    @Id val id: Int? = null,
    val name: String
)