package bd

import org.jetbrains.exposed.sql.Database

object DbSettings {
    val db by lazy {
        Database.connect(
            url = "jdbc:postgresql://localhost:5442/otus",
            driver = "org.postgresql.Driver",
            user = "otus",
            password = "otus"
        )
    }
}