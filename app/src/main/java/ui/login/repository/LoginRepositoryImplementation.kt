package ui.login.repository

import base.service.RetrofitHelper
import retrofit2.Response
import ui.login.model.LoginRequest
import ui.login.model.LoginResponse
import ui.login.service.ApiService

class LoginRepositoryImplementation : LoginRepository {
    private val retrofitHelper: ApiService = RetrofitHelper.getApiService()

    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(username = email, password = password)

        return retrofitHelper.login(loginRequest)
    }
}
