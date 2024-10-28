package base.network

import base.service.TokenManager
import okhttp3.Interceptor
import org.json.JSONObject

class CustomInterceptor() {
    val tokenInterceptor = Interceptor { chain ->
        val request = chain.request()
        val savedToken = TokenManager.token
        // add token to header of api calls
        val newRequest = if (savedToken != null) {
            request.newBuilder()
                .header("Authorization", "Bearer $savedToken")
                .build()
        } else {
            request
        }
val response = chain.proceed(request)
        // add token to shared preference
        val responseBody = response.body?.string()
        responseBody?.let {
            val jsonObject = JSONObject(it)
            if (jsonObject.has("token")) {
                val token: String? = jsonObject.getString("token")
                TokenManager.token = token
            }
        }
        chain.proceed(newRequest)

    }
}
