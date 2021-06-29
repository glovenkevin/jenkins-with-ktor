package com.jenjen.ktor.route

import io.ktor.application.*
import io.ktor.routing.*

fun Route.apiRoute() {
    route("/api/v1") {
        customerRouting()
    }
}

fun Application.registerRoute() {
    routing {
        apiRoute()
    }
}