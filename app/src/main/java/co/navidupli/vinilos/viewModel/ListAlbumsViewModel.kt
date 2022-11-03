package co.navidupli.vinilos.viewModel

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
        val albumStart = AlbumRepository.albums

        if (albumStart.isEmpty()) {
            viewModelScope.launch {
                val albumList = AlbumRepository.getAlbums()
                _albums.value = albumList
            }
        } else {
            _albums.value = albumStart
        }
    }
}

