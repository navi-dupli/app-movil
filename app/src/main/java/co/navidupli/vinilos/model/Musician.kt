package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Performer(
    val birthDate: String?,
    val creationDate: String,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val musicians: List<Comment>?,
)