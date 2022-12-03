package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Musician(
    val birthDate: String?,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
)