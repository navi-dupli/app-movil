package co.navidupli.vinilos.broker

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL ="https://back-vynils-c5.herokuapp.com"

class RetrofitClient{
    companion object {


        fun createRetrofitClient(): Retrofit {

            var mHttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val mOkHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(mHttpLoggingInterceptor)
                .build()


            val retrofit: Retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
            return retrofit
        }


    }
}