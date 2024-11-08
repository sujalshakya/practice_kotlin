package ui.users.repository


import ui.users.model.UserResponse
import base.service.RetrofitHelper
import retrofit2.Response
import base.service.ApiService

class UserRepositoryImp() : UserRepository {
    private val retrofitHelper: ApiService = RetrofitHelper.getApiService()


    override suspend fun getUsers(): Response<UserResponse> {
        return retrofitHelper.getUsers()    }


}
