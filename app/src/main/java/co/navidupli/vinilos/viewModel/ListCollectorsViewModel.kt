
package co.navidupli.vinilos.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.navidupli.vinilos.model.Collector
import co.navidupli.vinilos.model.Comment
import co.navidupli.vinilos.repositories.CollectorRepository
import kotlinx.coroutines.launch

class ListCollectorsViewModel: ViewModel() {


    private var _collectors = MutableLiveData<List<Collector>>(listOf())
    var collectors: LiveData<List<Collector>> = _collectors

    init {
        viewModelScope.launch {
            _collectors.value=CollectorRepository.getCollectors()
        }
    }

}
