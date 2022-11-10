package co.navidupli.vinilos.model

import androidx.lifecycle.LiveData
import kotlinx.serialization.Serializable

@Serializable
data class TrackAsociate(
    val duration: String?,
    val name: String?
)