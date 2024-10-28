package base.service

import base.network.CustomInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.login.service.ApiService

object RetrofitHelper {
    fun getApiService(): ApiService {
        val customInterceptor = CustomInterceptor()

        val client = OkHttpClient.Builder().apply {
            addInterceptor(customInterceptor.tokenInterceptor)
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