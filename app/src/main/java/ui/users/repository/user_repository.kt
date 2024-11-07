package ui.users.repository

import UserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(): Response<UserResponse>
}