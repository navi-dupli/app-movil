package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val duration: String,
    val id: Int,
    val name: String
)