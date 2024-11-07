package ui.users.viewmodel

import User
import androidx.lifecycle.ViewModel
import retrofit2.Response
import ui.users.repository.UserRepositoryImp
import UserResponse
import androidx.lifecycle.MutableLiveData

class UserViewModel : ViewModel() {

    private val userRepository = UserRepositoryImp()
    var userList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()
    suspend fun getUsers() {
        try {
            val response: Response<UserResponse> = userRepository.getUsers()
            if (response.isSuccessful) {
                userList.value = response.body()?.data
            } else {
                errorMessage.value = "Error"
            }
        } catch (e: Exception) {
        }
    }
}
