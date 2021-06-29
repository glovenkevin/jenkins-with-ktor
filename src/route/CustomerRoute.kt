package com.jenjen.ktor.route

import com.jenjen.ktor.Response.Response
import com.jenjen.ktor.models.Customer
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

val customerStorage = mutableListOf<Customer>()

fun Route.customerRouting() {
    route("customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                val rtn = Response(200, customerStorage)
                call.respond(rtn)
            } else {
                val rtn = Response(200, "No Customer Found")
                call.respond(rtn)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )

            val rtn = Response(200, customer)
            call.respond(rtn)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            val rtn = Response(200, "Customer added successfully")
            call.respond(rtn)
        }
        delete ("{id}") {

        }
    }
}

fun Application.registerCustomerRoute() {
    routing {
        customerRouting()
    }
}