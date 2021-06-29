package com.jenjen.ktor.entity

import org.jetbrains.exposed.sql.Table

object CustomerEntity: Table("customer") {
    val id = integer("id")
    val firstName = varchar("first_name", 30)
    val lastName = varchar("last_name", 30)
    val userName = varchar("username", 10)
    val email = varchar("email", 10)
    override val primaryKey = PrimaryKey(id)
}