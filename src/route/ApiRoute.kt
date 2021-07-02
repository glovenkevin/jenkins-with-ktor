package com.jenjen.ktor.route

import io.ktor.application.*
import io.ktor.routing.*

fun Route.apiRouteV1() {
    route("/api/v1") {
        customerRouting()
    }
}

fun Application.registerRoute() {
    routing {
        apiRouteV1()
        healthCheck()
    }
}