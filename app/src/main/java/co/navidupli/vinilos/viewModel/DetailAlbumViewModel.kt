package co.navidupli.vinilos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.repositories.AlbumRepository

class DetailAlbumViewModel : ViewModel() {

    private var _album = MutableLiveData<Album>()
    var album: LiveData<Album> = _album

    fun getAlbumDetail(albumId: Int) {
        AlbumRepository.getAlbumDetail(albumId, onResponse = {
            _album.value = it
        }, onFailure = {

        })
    }
}

