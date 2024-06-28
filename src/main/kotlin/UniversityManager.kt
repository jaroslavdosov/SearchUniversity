import bd.tables.UniversityTable
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import models.University
import models.UniversityForSerialization
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


class UniversityManager {

    fun getUniversityListByCountry(countryName: String): List<UniversityForSerialization> {
        return runBlocking {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json()
                }
            }

            val response = async <List<UniversityForSerialization>> {
                client.get("http://universities.hipolabs.com/search?country=$countryName").body()
            }
            val university = response.await()

            return@runBlocking university
        }
    }

    fun getUniversityByPredicate(predicate: SqlExpressionBuilder.() -> Op<Boolean>) = transaction {
        UniversityTable
            .selectAll()
            .where { predicate() }
            .map {
                University(
                    id = it[UniversityTable.id].value,
                    name = it[UniversityTable.name],
                    alphaTwoCode = it[UniversityTable.alphaTwoCode],
                    country = it[UniversityTable.country],
                    webPages = it[UniversityTable.webPages],
                    stateProvince = it[UniversityTable.stateProvince],
                    domains = it[UniversityTable.domains]
                )
            }
    }

    fun searchUniversityByName(universityName: String){
        val results = getUniversityByPredicate {
            UniversityTable.name regexp  universityName
        }

        if(results.isNotEmpty()){
            println("Найденные университеты: ")
            for (result in results) {
                println("name: " + result.name)
                println("country: " + result.country)
                println("webPages: " + result.webPages)
                println("alphaTwoCode: " + result.alphaTwoCode)
                println("stateProvince: " + result.stateProvince)
                println("domains: " + result.domains)
                println(" ")
            }
        } else {
            println("Университеты не найдены")
        }
    }

    fun insertUniversitiesInBd(universities: List<UniversityForSerialization>){
        transaction {
            for (university in universities) {
                UniversityTable.insert {
                    it[name] = university.name
                    it[alphaTwoCode] = university.alpha_two_code
                    it[stateProvince] = university.state_province
                    it[country] = university.country
                    it[webPages] = university.web_pages
                    it[domains] = university.domains
                }
            }
        }
    }
}