package com.jenjen.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jenjen.ktor.Response.Response
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/healthCheck").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val resultString = response.content.toString()
                val mapper = jacksonObjectMapper()
                val resp: Response = mapper.readValue(resultString)
                assertEquals("OK", resp.data)
            }
        }
    }
}
