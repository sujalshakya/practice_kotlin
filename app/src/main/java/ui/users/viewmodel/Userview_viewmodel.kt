package ui.users.viewmodel

import ui.users.model.User
import base.room.UserDatabase
import androidx.lifecycle.ViewModel
import retrofit2.Response
import ui.users.repository.UserRepositoryImp
import ui.users.model.UserResponse
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserViewModel
@Inject constructor(
    private val userRepository : UserRepositoryImp
)
    : ViewModel() {

    var userList = MutableLiveData<List<User>?>()
    val errorMessage = MutableLiveData<String>()

    fun getUsers(db: UserDatabase) {
        viewModelScope.launch {
            try {
                val usersFromDb = db.userDao().getAllUsers()

                // Check if users are in the database
                if (usersFromDb.isEmpty()) {
                    // If users are not available in the DB, make api call
                    val response: Response<UserResponse> = userRepository.getUsers()
                    if (response.isSuccessful) {
                        val users = response.body()?.data
                        userList.value = users

                        // If users are fetched, insert them into Room
                        users?.let {
                            withContext(Dispatchers.IO) {
                                db.userDao().insertAll(it)
                            }
                        }
                    } else {
                        errorMessage.value = "Error: ${response.message()}"
                    }
                } else {
                    userList.value = usersFromDb
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error occurred: ", e)
                errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }

}
