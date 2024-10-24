package ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import retrofit2.Response
import ui.login.model.LoginResponse
import ui.login.repository.LoginRepositoryImplementation

class LoginViewModel : ViewModel() {


    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean> get() = _navigate

    fun login(emailText: String, passwordText: String) {
        viewModelScope.launch {
            try {
                val result : Response<LoginResponse> = LoginRepositoryImplementation().login(emailText, passwordText)
                println("login try: $result")
                _navigate.value = result.code()== 200
            } catch (e: Exception) {
                println("Error: ${e.message}")
                _navigate.value = false
            }
        }
    }
}
