package co.navidupli.vinilos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.model.AlbumCreate
import co.navidupli.vinilos.repositories.AlbumRepository

class CreateAlbumViewModel : ViewModel() {
    private val _nameAlbum = MutableLiveData<String>()
    val nameAlbum: LiveData<String> = _nameAlbum
    private val _coverAlbum = MutableLiveData<String>()
    val coverAlbum: LiveData<String> = _coverAlbum
    private val _dateReleaseAlbum = MutableLiveData<String>()
    val dateReleaseAlbum: LiveData<String> = _dateReleaseAlbum
    private val _descriptionAlbum = MutableLiveData<String>()
    val descriptionAlbum: LiveData<String> = _descriptionAlbum
    private val _genreAlbum = MutableLiveData<String>()
    val genreAlbum: LiveData<String> = _genreAlbum
    private val _recordLabelAlbum = MutableLiveData<String>()
    val recordLabelAlbum: LiveData<String> = _recordLabelAlbum
    private var _statusCreateAlbum = MutableLiveData<Boolean>()
    var statusCreateAlbum: LiveData<Boolean> = _statusCreateAlbum
    private var _loadCreateAlbum = MutableLiveData<Boolean>()
    var loadCreateAlbum: LiveData<Boolean> = _loadCreateAlbum


    fun saveAlbum() {
        val album = AlbumCreate(
            name= _nameAlbum.value,
            description = _descriptionAlbum.value,
            genre = _genreAlbum.value,
            cover = _coverAlbum.value,
            recordLabel = _recordLabelAlbum.value,
            releaseDate = _dateReleaseAlbum.value
        )

        AlbumRepository.postAlbumRequest(album,
            onResponse = {
                _statusCreateAlbum.value = true
                _loadCreateAlbum.value = true
                clearState()
            }, onFailure = {
                _statusCreateAlbum.value = false
                _loadCreateAlbum.value = true
            })
    }

    fun setNameAlbum(value: String){
        _nameAlbum.value = value
    }

    fun setCoverAlbum(value: String){
        _coverAlbum.value = value
    }

    fun setDateReleaseAlbum(value: String){
        _dateReleaseAlbum.value = value
    }

    fun setDescriptionAlbum(value: String){
        _descriptionAlbum.value = value
    }

    fun setRecordLabelAlbum(value: String){
        _recordLabelAlbum.value = value
    }

    fun setGenreAlbum(value: String){
        _genreAlbum.value = value
    }

    fun setLoadCreate(value: Boolean) {
        _loadCreateAlbum.value = value
    }

    fun setStatusCreateAlbum() {
        _statusCreateAlbum.value = null
    }

    private fun clearState() {
        _nameAlbum.value = ""
        _descriptionAlbum.value = ""
        _genreAlbum.value = ""
        _coverAlbum.value = ""
        _recordLabelAlbum.value = ""
        _dateReleaseAlbum.value = ""
    }
}