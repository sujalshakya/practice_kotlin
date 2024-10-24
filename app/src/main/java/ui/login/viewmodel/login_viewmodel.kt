package ui.login.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import ui.login.model.LoginRequest
import ui.login.model.LoginResponse
import ui.login.service.ApiService
import androidx.lifecycle.viewModelScope


class LoginViewmodel : ViewModel(){
    private val retrofitHelper: ApiService = RetrofitHelper.getInstance().create(ApiService :: class.java)

 fun  login(emailText: String, passwordText: String){

     viewModelScope.launch {
        try {
        val result: Response<LoginResponse> = retrofitHelper.login(LoginRequest(emailText, passwordText))
        println("login try: $result")
        if (result.code()==200) {
            println("Login passed")

        } else {
            println("Login failed")
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }}
}
}