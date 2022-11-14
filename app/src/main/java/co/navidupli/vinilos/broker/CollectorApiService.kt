package co.navidupli.vinilos.broker

import co.navidupli.vinilos.model.*
import retrofit2.http.GET
import retrofit2.http.Path


interface CollectorApiService {

    @GET("/collectors")
    suspend fun getCollectors(): List<Collector>

    @GET("/collectors/{id_collector}/performers")
    suspend fun getAllPerformersCollector(@Path("id_collector") idCollector: Int): List<Performer>
}