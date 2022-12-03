package co.navidupli.vinilos.repositories

import co.navidupli.vinilos.broker.CollectorApiService
import co.navidupli.vinilos.broker.RetrofitClient
import co.navidupli.vinilos.model.Collector
import co.navidupli.vinilos.model.Performer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectorRepository {
    companion object {
        private var collectorWebApi: CollectorApiService =
            RetrofitClient.createRetrofitClient().create(CollectorApiService::class.java)


        private var collectors: List<Collector> = listOf()
        private var performersCollector: List<Performer> = listOf()

        suspend fun getCollectors(): List<Collector> {
            collectors = collectorWebApi.getCollectors()
            return collectors
        }

        suspend fun getPerformersCollector(idCollector: Int): List<Performer> {
            performersCollector = collectorWebApi.getAllPerformersCollector(idCollector)
            return performersCollector
        }

        fun getCollectorDetail(
            collectorId: Int,
            onResponse: (resp: Collector) -> Unit,
            onFailure: (resp: String) -> Unit
        ): String? {
            val resp: String? = null
            collectorWebApi.getCollectorDetail(collectorId).enqueue(
                object : Callback<Collector> {
                    override fun onFailure(call: Call<Collector>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<Collector>, response: Response<Collector>) {
                        onResponse(response.body()!!)
                    }
                })
            return resp
        }

    }

}