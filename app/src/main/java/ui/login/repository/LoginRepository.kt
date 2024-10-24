package ui.login.repository

import retrofit2.Response
import ui.login.model.LoginResponse

 interface LoginRepository {
    suspend fun login(email: String, password : String): Response<LoginResponse>
}