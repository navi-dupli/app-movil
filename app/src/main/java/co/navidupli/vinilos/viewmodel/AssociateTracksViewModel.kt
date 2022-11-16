package co.navidupli.vinilos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.model.TrackAsociate
import co.navidupli.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.launch

class AssociateTracksViewModel : ViewModel() {
    private val _albumSelected = MutableLiveData<Album>()
    val albumSelected: LiveData<Album> = _albumSelected
    private val _duration = MutableLiveData<String>()
    val duration: LiveData<String> = _duration
    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> = _name
    private val _statusAssociate = MutableLiveData<Boolean>()
    val statusAssociate: LiveData<Boolean> = _statusAssociate
    private val _loadAssociateTrack = MutableLiveData<Boolean>()
    val loadAssociateTrack: LiveData<Boolean> = _loadAssociateTrack

    private var _albums = MutableLiveData<List<Album>>(listOf())
    var albums: LiveData<List<Album>> = _albums

    init {
        viewModelScope.launch {
            val albumList = AlbumRepository.getAlbums().sortedByDescending { it.id }
            albumList.forEach { println(it) }
            _albums.value = albumList
        }
    }


    fun associateTrack() {
        val track = TrackAsociate(
            duration = _duration.value,
            name = _name.value
        )
        val idAlbum =  _albumSelected.value?.id

        AlbumRepository.postAssociateTrackAlbum(track,idAlbum,
            onResponse = {
                _statusAssociate.value = true
                _loadAssociateTrack.value = true
                clearState()
            }, onFailure = {
                _statusAssociate.value = false
                _loadAssociateTrack.value = true
            })

    }

    fun setAlbumSelected(value: Album){
        _albumSelected.value = value
    }

    fun setDurationTrack(value: String){
        _duration.value = value
    }

    fun setTrackName(value: String){
        _name.value = value
    }


    fun setloadAssociateTrack(value: Boolean) {
        _loadAssociateTrack.value = value
    }

    fun setStatusAssociateTrackAlbum() {
        _statusAssociate.value = null
    }




    fun clearState() {
        _albumSelected.value = null
        _duration.value = ""
        _name.value = ""
    }
}