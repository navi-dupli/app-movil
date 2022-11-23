package co.navidupli.vinilos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.model.Musician
import co.navidupli.vinilos.model.Performer

class DetailArtistViewModel : ViewModel() {

    private var _performer = MutableLiveData<Performer>()
    var performer: LiveData<Performer> = _performer

    fun getPerformerDetail(PerformerId: Int, isBand : Boolean?) {
        _performer.value = Performer(
            name ="Queen",
            id = 101,
            description = "Queen es una banda británica de rock formada en 1970 en Londres por el cantante Freddie Mercury, el guitarrista Brian May, el baterista Roger Taylor y el bajista John Deacon. Si bien el grupo ha presentado bajas de dos de sus miembros (Mercury, fallecido en 1991, y Deacon, retirado en 1997), los integrantes restantes, May y Taylor, continúan trabajando bajo el nombre Queen, por lo que la banda aún se considera activa.",
            birthDate =  null,
            creationDate ="1970-01-01T00:00:00.000Z",
            image =  "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
            musicians = listOf(
                Musician(birthDate ="1946-09-05T05:00:00.000Z", description = "\"Fue un cantante, compositor, pianista, diseñador gráfico y músico británico de origen parsi e indio\u200B conocido por haber sido el vocalista principal de la banda de rock Queen.",id = 1,
                    image = "https://upload.wikimedia.org/wikipedia/commons/e/ef/Freddie_Mercury_performing_in_New_Haven%2C_CT%2C_November_1977.jpg",
                    name ="Freddie Mercury")
            )


        )
    }


}

