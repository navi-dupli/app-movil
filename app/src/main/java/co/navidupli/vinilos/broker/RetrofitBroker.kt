package co.navidupli.vinilos.broker

import android.util.Log
import co.navidupli.vinilos.model.AlbumCreate
import co.navidupli.vinilos.model.AlbumCreated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitBroker {
    companion object{
    fun postAlbumRequest(body: AlbumCreate, onResponse:(resp: AlbumCreated)->Unit, onFailure:(resp:String)->Unit) : String? {
            var resp: String? = null

            RetrofitApi.retrofitService.postAlbum(body).enqueue(

                object : Callback<AlbumCreated> {
                    override fun onFailure(call: Call<AlbumCreated>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<AlbumCreated>, response: Response<AlbumCreated>) {
                        if (response.code() == 400) {
                            onFailure("Bad request")
                        } else {
                            onResponse(response.body()!!)
                        }
                    }
                })
            return resp
        }
    }
}