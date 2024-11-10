package ui.users.repository

import ui.users.model.UserResponse
import retrofit2.Response
import base.service.ApiService
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : UserRepository {

    override suspend fun getUsers(): Response<UserResponse> {
        return try {
            apiService.getUsers()
        } catch (e: Exception) {
            throw e
        }
    }
}
