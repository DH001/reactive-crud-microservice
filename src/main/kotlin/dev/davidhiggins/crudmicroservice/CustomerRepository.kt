package dev.davidhiggins.crudmicroservice

import org.springframework.data.annotation.Id
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface CustomerRepository: R2dbcRepository<Customer, String>

data class Customer(
    // UUID doesn't work with Spring Data reactive save() method - https://stackoverflow.com/questions/70877916/spring-data-r2dbc-postgresql-not-saving-new-record-with-uuid-id
    @Id val id: Int? = null,
    val name: String
    )