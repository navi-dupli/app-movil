package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class AlbumCreate(
    val cover: String?,
    val description: String?,
    val genre: String?,
    val name: String?,
    val recordLabel: String?,
    val releaseDate: String?
)


