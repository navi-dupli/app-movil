package co.navidupli.vinilos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.model.Performer
import co.navidupli.vinilos.repositories.ArtistRepository

class DetailArtistViewModel : ViewModel() {

    private var _performer = MutableLiveData<Performer>()
    var performer: LiveData<Performer> = _performer

    fun getPerformerDetail(performerId: Int, isBand : Boolean?) {
        if (isBand == true) {
            ArtistRepository.getBandDetail(performerId, onResponse = {
                _performer.value = it
            }, onFailure = {
            })
        } else {
            ArtistRepository.getMusicianDetail(performerId, onResponse = {
                _performer.value = it
            }, onFailure = {
            })
        }
    }

}

