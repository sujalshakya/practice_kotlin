package base.network

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import org.json.JSONObject

class  CustomInterceptor(context: Context){
val customInterceptor = Interceptor { chain ->
    val request = chain.request()
    val response = chain.proceed(request)
        val responseBody = response.body()?.string()
        responseBody?.let {
            val jsonObject = JSONObject(it)
            if (jsonObject.has("token") ){
            val token: String? = jsonObject.getString("token")
            val sharedPref = context.getSharedPreferences("Practice",
              Context.MODE_PRIVATE)
            val myEdit: SharedPreferences.Editor? = sharedPref.edit()
            myEdit?.putString("token", token)
            myEdit?.commit() ?: "";
        }}
    chain.proceed(chain.request())
}}




