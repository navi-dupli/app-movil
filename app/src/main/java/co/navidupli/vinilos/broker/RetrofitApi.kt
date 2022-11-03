package co.navidupli.vinilos.broker

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import co.navidupli.vinilos.model.AlbumCreate
import co.navidupli.vinilos.model.AlbumCreated
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL =
    "https://back-vynils-c5.herokuapp.com"

val client = OkHttpClient.Builder()

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .client(client.build())
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface VinilosApiService {

    @POST("albums")
    fun postAlbum(@Body params: AlbumCreate): Call<AlbumCreated>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object RetrofitApi {
    val retrofitService: VinilosApiService by lazy {
        retrofit.create(VinilosApiService::class.java)
    }
}