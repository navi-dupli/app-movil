
package co.navidupli.vinilos.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.navidupli.vinilos.model.Collector
import co.navidupli.vinilos.model.Comment
import co.navidupli.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.launch

class ListCollectorsViewModel: ViewModel() {


    private var _collectors = MutableLiveData<List<Collector>>(listOf())
    var collectors: LiveData<List<Collector>> = _collectors

    init {
        viewModelScope.launch {
            var collector = Collector(1,"Duvan Jamid Vrgas","234523452345","mi13dj22@gmail.com",
                Comment("3244245",1,1)
            );
            val albumList = listOf<Collector>(collector)
            albumList.forEach { println(it) }
            _collectors.value=albumList
        }
    }

}
