package com.jenjen.ktor.customer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jenjen.ktor.Response.Response
import com.jenjen.ktor.models.Customer
import com.jenjen.ktor.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class CustomerTest {

    @Test
    fun testInsert() {
        withTestApplication({ module(testing = true) })  {
            handleRequest(HttpMethod.Post, "/api/v1/customer/insert") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())

                val param = Customer(
                    id = 2,
                    userName = "jenjen",
                    lastName = "chandra",
                    firstName = "kevin"
                )
                val mapper = jacksonObjectMapper()
                val json = mapper.writeValueAsString(param)
                setBody(json)
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val resultString = response.content.toString()
                val mapper = jacksonObjectMapper()
                val resp: Response = mapper.readValue(resultString)
                assertEquals(200, resp.code)
            }
        }
    }

    @Test
    fun testDelete() {
        withTestApplication({ module(testing = true) }) {
            val id = 2
            handleRequest(HttpMethod.Delete, "/api/v1/customer/$id").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val resultString = response.content.toString()
                val mapper = jacksonObjectMapper()
                val resp: Response = mapper.readValue(resultString)
                assertEquals(200, resp.code)
            }
        }
    }

}