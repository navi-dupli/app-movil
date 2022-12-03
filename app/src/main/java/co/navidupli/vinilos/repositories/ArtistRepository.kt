package co.navidupli.vinilos.repositories

import co.navidupli.vinilos.broker.ArtistApiService
import co.navidupli.vinilos.broker.RetrofitClient
import co.navidupli.vinilos.model.Performer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistRepository {
    companion object {
        private var collectorWebApi: ArtistApiService =
            RetrofitClient.createRetrofitClient().create(ArtistApiService::class.java)

        fun getBandDetail(
            performerId: Int,
            onResponse: (resp: Performer) -> Unit,
            onFailure: (resp: String) -> Unit
        ): String? {
            val resp: String? = null
            collectorWebApi.getBandDetail(performerId).enqueue(
                object : Callback<Performer> {
                    override fun onFailure(call: Call<Performer>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<Performer>, response: Response<Performer>) {
                        onResponse(response.body()!!)
                    }
                })
            return resp
        }

        fun getMusicianDetail(
            performerId: Int,
            onResponse: (resp: Performer) -> Unit,
            onFailure: (resp: String) -> Unit
        ): String? {
            val resp: String? = null
            collectorWebApi.getMusicianDetail(performerId).enqueue(
                object : Callback<Performer> {
                    override fun onFailure(call: Call<Performer>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<Performer>, response: Response<Performer>) {
                        onResponse(response.body()!!)
                    }
                })
            return resp
        }

    }

}