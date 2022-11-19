package co.navidupli.vinilos.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.model.Performer
import co.navidupli.vinilos.repositories.CollectorRepository

class ListPerformerViewModel : ViewModel() {


    private var _performer = MutableLiveData<List<Performer>>(listOf())
    var performers: LiveData<List<Performer>> = _performer


    init {
        viewModelScope.launch {
            val collectorList = CollectorRepository.getCollectors().sortedByDescending { it.id }
            collectorList.forEach {

                val performerList = CollectorRepository.getPerformersCollector(it.id)
                val value = _performer.value ?: emptyList()
                _performer.value = value + performerList

            }
        }
    }
}



