package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Long,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val tracks: List<Track>?,
    val comments: List<Comment>?,
    val performers: List<Performer>?,
)