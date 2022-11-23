package co.navidupli.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Collector(
    val id: Int,
    val name : String,
    val telephone :String,
    val email : String,
    val comment: Comment,
    val favoritePerformers: List<Performer>
)