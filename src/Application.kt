package com.jenjen.ktor

import com.fasterxml.jackson.databind.SerializationFeature
import com.jenjen.ktor.database.DatabaseFactory
import com.jenjen.ktor.route.registerRoute
import com.jenjen.ktor.service.bindServices
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.request.*
import org.kodein.di.ktor.di
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    registerRoute()
    DatabaseFactory.init()

    di {
        bindServices()
    }

}

