package com.jenjen.ktor.models

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class Customer (
    var id: String ,
    var firstName: String,
    var lastName: String,
    var userName: String
)