package co.navidupli.vinilos.model

import androidx.lifecycle.LiveData
import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val duration: String?,
    val id: Int?,
    val name: String?,
    val album: Album?
)