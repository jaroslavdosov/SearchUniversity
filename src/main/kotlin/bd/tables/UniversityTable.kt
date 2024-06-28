package bd.tables

import kotlinx.serialization.json.Json
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.json.jsonb


object UniversityTable: IdTable<Int>( "universities") {
    override val id = integer("id").entityId()
    val name = varchar("name", 255)
    val alphaTwoCode = varchar("alpha_two_code", 255)
    val country = varchar("country", 255)
    val webPages = jsonb<List<String>>("web_pages", Json { ignoreUnknownKeys = true })
    val stateProvince = varchar("state_province", 255).nullable()
    val domains = jsonb<List<String>>("domains", Json { ignoreUnknownKeys = true })
}
