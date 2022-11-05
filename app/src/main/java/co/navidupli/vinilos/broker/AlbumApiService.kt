package co.navidupli.vinilos.broker

import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.model.AlbumCreate
import co.navidupli.vinilos.model.AlbumCreated
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AlbumApiService {

    @GET("/albums")
    suspend fun getAlbums(): List<Album>

    @POST("albums")
    fun postAlbum(@Body params: AlbumCreate): Call<AlbumCreated>
}