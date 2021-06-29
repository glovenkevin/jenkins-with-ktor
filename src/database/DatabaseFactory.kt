package com.jenjen.ktor.database

import com.sun.org.slf4j.internal.LoggerFactory
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

object DatabaseFactory {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun init() {
        val hikariConfig = HikariConfig("/hikari.properties")
        val dataSource = HikariDataSource(hikariConfig)
        Database.connect(dataSource)

        val config = HoconApplicationConfig(ConfigFactory.load())
        val flywayEnabled = config.property("flyway.enabled").getString()
        if (flywayEnabled == "true") {
            this.migrate(dataSource)
        }
    }

    private fun migrate(dataSource: DataSource) {
        val flyway = Flyway.configure().dataSource(dataSource).load();
        try {
            flyway.info()
            flyway.migrate()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        log.debug("Migration Complete")
    }

}