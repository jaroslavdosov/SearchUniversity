package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityForSerialization(
    val name: String,
    val alpha_two_code: String,
    val country: String,
    val web_pages:  List<String>,
    @SerialName("state-province") val state_province: String?,
    val domains:  List<String>
)

@Serializable
data class University(
    val id: Int,
    val name: String,
    val alphaTwoCode: String,
    val country: String,
    val webPages: List<String>,
    val stateProvince: String?,
    val domains: List<String>
)


