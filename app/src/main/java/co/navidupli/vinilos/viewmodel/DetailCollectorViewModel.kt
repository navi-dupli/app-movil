package co.navidupli.vinilos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.model.Collector
import co.navidupli.vinilos.repositories.CollectorRepository

class DetailCollectorViewModel : ViewModel() {

    private var _collector = MutableLiveData<Collector>()
    var collector: LiveData<Collector> = _collector

    fun getCollectorDetail(albumId: Int) {
         CollectorRepository.getCollectorDetail(albumId, onResponse = {
            _collector.value = it
        }, onFailure = {
        })
    }

}

