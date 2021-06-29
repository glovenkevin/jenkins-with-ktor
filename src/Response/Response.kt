package com.jenjen.ktor.Response

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class Response (
    var code: Int,
    var data: Any?
)