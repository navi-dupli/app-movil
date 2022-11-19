package co.navidupli.vinilos.broker

import co.navidupli.vinilos.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface AlbumApiService {

    @GET("/albums")
    suspend fun getAlbums(): List<Album>

    @POST("albums")
    fun postAlbum(@Body params: AlbumCreate): Call<AlbumCreated>

    @POST("/albums/{idAlbum}/tracks")
    fun postAssociateTrackAlbum( @Body params: TrackAsociate, @Path("idAlbum") idAlbum: Long?): Call<Track>

    @GET("/albums/{idAlbum}")
    fun getAlbumDetail(@Path("idAlbum") idAlbum: Int): Call<Album>
}