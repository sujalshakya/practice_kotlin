package ui.login.service

import UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ui.login.model.LoginRequest
import ui.login.model.LoginResponse

interface ApiService {
    @POST("v1/api/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("https://reqres.in/api/users?page=2p")
    suspend fun getUsers(): Response<UserResponse>

}

