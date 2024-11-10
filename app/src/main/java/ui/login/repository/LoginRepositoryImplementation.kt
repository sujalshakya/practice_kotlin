package ui.login.repository

import retrofit2.Response
import ui.login.model.LoginRequest
import ui.login.model.LoginResponse
import base.service.ApiService
import javax.inject.Inject

class LoginRepositoryImplementation @Inject constructor(
    private val apiService: ApiService
)  : LoginRepository {

    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(username = email, password = password)

        return apiService.login(loginRequest)
    }
}
