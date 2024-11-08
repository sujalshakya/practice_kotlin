package ui.users.repository

import ui.users.model.UserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(): Response<UserResponse>
}