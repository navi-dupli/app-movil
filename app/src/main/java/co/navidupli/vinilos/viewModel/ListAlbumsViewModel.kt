package co.navidupli.vinilos.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.repositories.AlbumRepository

class ListAlbumsViewModel : ViewModel() {


    private var _albums = MutableLiveData<List<Album>>(listOf())
    var albums: LiveData<List<Album>> = _albums

    init {
        viewModelScope.launch {
            val albumList = AlbumRepository.getAlbums().sortedByDescending { it.id }
            albumList.forEach { println(it) }
            _albums.value = albumList
        }
    }
}

