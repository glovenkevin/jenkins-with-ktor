package com.jenjen.ktor.route

import com.jenjen.ktor.Response.Response
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.healthCheck() {

    route("/healthCheck") {
        get {
           call.respond(Response(200, "OK"))
        }
    }

}