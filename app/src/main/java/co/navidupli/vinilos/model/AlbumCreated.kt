package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class AlbumCreated(
    val cover: String,
    val description: String,
    val genre: String,
    val id: Int,
    val name: String,
    val recordLabel: String,
    val releaseDate: String
)