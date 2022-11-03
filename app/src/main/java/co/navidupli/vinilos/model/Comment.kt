package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val description: String,
    val id: Int,
    val rating: Int
)