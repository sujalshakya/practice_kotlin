package base.service

import android.content.Context
import base.network.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.login.service.ApiService

object RetrofitHelper {

    fun getApiService(context: Context): ApiService {
        val customInterceptor = Interceptor(context)

        val client = OkHttpClient.Builder().apply {
            addInterceptor(customInterceptor.customInterceptor)
        }.build()

        val baseUrl = "https://tbe.thuprai.com/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
