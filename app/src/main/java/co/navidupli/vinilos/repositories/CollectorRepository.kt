package co.navidupli.vinilos.repositories

import co.navidupli.vinilos.broker.CollectorApiService
import co.navidupli.vinilos.broker.RetrofitClient
import co.navidupli.vinilos.model.Collector
import co.navidupli.vinilos.model.Performer

class CollectorRepository {
    companion object {
        private var collectorWebApi: CollectorApiService =
            RetrofitClient.createRetrofitClient().create(CollectorApiService::class.java)


        var collectors: List<Collector> = listOf()
        var performersCollector: List<Performer> = listOf()

        suspend fun getCollectors(): List<Collector> {
            collectors = collectorWebApi.getCollectors()
            return collectors
        }

        suspend fun getPerformersCollector(idCollector : Int): List<Performer> {
            performersCollector = collectorWebApi.getAllPerformersCollector(idCollector)
            return performersCollector
        }

    }

}