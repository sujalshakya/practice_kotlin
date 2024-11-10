package base.network

import android.util.Log
import base.service.SharedPreferenceManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class CustomInterceptor {
    val tokenInterceptor = Interceptor { chain ->
        val request = chain.request()
        val savedToken = SharedPreferenceManager.token
        // Add token to header of API calls
        val newRequest = if (savedToken != null) {
            request.newBuilder()
                .header("Authorization", "Bearer $savedToken")
                .build()
        } else {
            request
        }

        val response = chain.proceed(newRequest)
        // Add token to shared preference
        val responseBody = response.body?.string()
        responseBody?.let {
            val jsonObject = JSONObject(it)
            if (jsonObject.has("token")) {
                val token: String? = jsonObject.getString("token")
                SharedPreferenceManager.token = token
            }
        }

        applyLog(newRequest)

        // Return the response
        response.newBuilder()
            .body((responseBody ?: "").toResponseBody(response.body?.contentType()))
            .build()
    }

    private fun applyLog(request: Request) {
        Log.d("Request", "Method: ${request.method} Body: ${request.body.toString()} Headers: ${request.headers}")
    }
}
