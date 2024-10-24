package ui.login.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ui.login.model.LoginRequest
import ui.login.model.LoginResponse

interface ApiService {
    @POST("v1/api/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

