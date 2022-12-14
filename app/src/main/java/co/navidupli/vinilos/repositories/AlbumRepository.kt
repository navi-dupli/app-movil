package co.navidupli.vinilos.repositories

import co.navidupli.vinilos.broker.AlbumApiService
import co.navidupli.vinilos.broker.RetrofitClient
import co.navidupli.vinilos.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository {
    companion object {
        private var albumWebApi: AlbumApiService =
            RetrofitClient.createRetrofitClient().create(AlbumApiService::class.java)


        private var albums: List<Album> = listOf()

        suspend fun getAlbums(): List<Album> {
            albums = albumWebApi.getAlbums()
            return albums
        }

        fun postAlbumRequest(
            body: AlbumCreate,
            onResponse: (resp: AlbumCreated) -> Unit,
            onFailure: (resp: String) -> Unit
        ): String? {
            val resp: String? = null

            albumWebApi.postAlbum(body).enqueue(

                object : Callback<AlbumCreated> {
                    override fun onFailure(call: Call<AlbumCreated>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(
                        call: Call<AlbumCreated>,
                        response: Response<AlbumCreated>
                    ) {
                        if (response.code() == 400) {
                            onFailure("Bad request")
                        } else {
                            onResponse(response.body()!!)
                        }
                    }
                })
            return resp
        }

        fun postAssociateTrackAlbum(
            body: TrackAsociate,
            idAlbum: Long?,
            onResponse: (resp: Track) -> Unit,
            onFailure: (resp: String) -> Unit
        ): String? {
            val resp: String? = null

            albumWebApi.postAssociateTrackAlbum(body, idAlbum).enqueue(

                object : Callback<Track> {
                    override fun onFailure(call: Call<Track>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<Track>, response: Response<Track>) {
                        if (response.code() == 400) {
                            onFailure("Bad request")
                        } else {
                            onResponse(response.body()!!)
                        }
                    }
                })
            return resp
        }

        fun getAlbumDetail(
            albumId: Int,
            onResponse: (resp: Album) -> Unit,
            onFailure: (resp: String) -> Unit
        ): String? {
            val resp: String? = null
            albumWebApi.getAlbumDetail(albumId).enqueue(
                object : Callback<Album> {
                    override fun onFailure(call: Call<Album>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<Album>, response: Response<Album>) {
                        onResponse(response.body()!!)
                    }
                })
            return resp
        }
    }

}