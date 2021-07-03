package com.jenjen.ktor.route

import com.jenjen.ktor.Response.Response
import com.jenjen.ktor.entity.CustomerEntity
import com.jenjen.ktor.models.Customer
import com.jenjen.ktor.service.CustomerService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.customerRouting() {

    val customerService by di().instance<CustomerService>()

    route("/customer") {
        get {
            val listCustomer = customerService.getAllCustomer()
            val rtn = Response(200, listCustomer)
            call.respond(rtn)
        }
        get("/{id}") {
            val id = call.parameters["id"]
            if (id.isNullOrEmpty()) {
                return@get call.respond(Response(200, "Malform id"))
            }
            val rtn = customerService.getCustomer(id)
            call.respond(rtn)
        }
        post("/insert") {
            val customer = call.receive<Customer>()
            customerService.insertCustomer(customer)
            val rtn = Response(200, "Customer added successfully")
            call.respond(rtn)
        }
        delete ("/{id}") {
            val id = call.parameters["id"]
            if (id.isNullOrEmpty()) {
                return@delete call.respond(Response(200, "Malform id"))
            }
            customerService.deleteCustomer(id)
            val rtn = Response(200, "Customer removed successfully")
            call.respond(rtn)
        }
    }
}