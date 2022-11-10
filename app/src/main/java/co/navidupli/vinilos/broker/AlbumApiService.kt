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

    @POST("/albums/{id_album}/tracks")
    fun postAssociateTrackAlbum( @Body params: TrackAsociate, @Path("id_album") idAlbum: Int?): Call<Track>
}