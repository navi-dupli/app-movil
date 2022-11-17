package co.navidupli.vinilos.broker

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL ="https://back-vynils-c5.herokuapp.com"

class RetrofitClient{
    companion object {


        fun createRetrofitClient(): Retrofit {

            val mHttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)

            val mOkHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(mHttpLoggingInterceptor)
                .build()


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
        }


    }
}