package co.navidupli.vinilos.broker

import co.navidupli.vinilos.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ArtistApiService {

    @GET("/bands/{id_band}")
    fun getBandDetail(@Path("id_band") idBand: Int): Call<Performer>

    @GET("/musicians/{id_musician}")
    fun getMusicianDetail(@Path("id_musician") idMusician: Int): Call<Performer>
}