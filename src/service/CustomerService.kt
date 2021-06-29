package com.jenjen.ktor.service

import com.jenjen.ktor.Response.Response
import com.jenjen.ktor.entity.CustomerEntity
import com.jenjen.ktor.models.Customer
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CustomerService {

    fun getAllCustomer(): List<Customer> {
        val rtn = arrayListOf<Customer>()
        transaction {

            CustomerEntity.selectAll().forEach {
                rtn.add(
                    Customer(
                        id = it[CustomerEntity.id],
                        firstName = it[CustomerEntity.firstName],
                        lastName = it[CustomerEntity.lastName],
                        userName = it[CustomerEntity.userName]
                    )
                )
            }

        }
        return rtn
    }

    fun getCustomer(id: String): Response {
        var rtn = Response(200, "Customer not found")
        transaction {
            val resultSet = CustomerEntity.select { CustomerEntity.id eq id.toInt() }.firstOrNull()
            if (resultSet != null) {
                rtn = Response(200,
                    Customer(
                        id = resultSet[CustomerEntity.id],
                        userName = resultSet[CustomerEntity.userName],
                        firstName = resultSet[CustomerEntity.firstName],
                        lastName = resultSet[CustomerEntity.lastName]
                    )
                )
            }
        }
        return rtn
    }

    fun insertCustomer(customer: Customer) {
        transaction {
            CustomerEntity.insert {
                it[id] = customer.id
                it[firstName] = customer.firstName
                it[lastName] = customer.lastName
                it[userName] = customer.userName
            }

            commit()
        }
    }

    fun deleteCustomer(id: String) {
        transaction {
            CustomerEntity.deleteWhere { CustomerEntity.id eq id.toInt() }
            commit()
        }
    }

}