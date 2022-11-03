package co.navidupli.vinilos.broker

import android.util.Log
import co.navidupli.vinilos.model.AlbumCreate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitBroker {
    companion object{
    fun postAlbumRequest(body: AlbumCreate, onResponse:(resp:String)->Unit, onFailure:(resp:String)->Unit) : String? {
            var resp: String? = null

            RetrofitApi.retrofitService.postAlbum(body).enqueue(

                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        onResponse(response.body()!!)
                    }
                })
            return resp
        }
    }
}