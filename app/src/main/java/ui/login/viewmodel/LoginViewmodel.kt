package ui.login.viewmodel

import android.content.Context

import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import retrofit2.Response
import ui.home.Home
import ui.login.model.LoginResponse
import ui.login.repository.LoginRepositoryImplementation

class LoginViewModel : ViewModel() {

    fun login(emailText: String, passwordText: String, context: Context) {
        viewModelScope.launch {

                val result : Response<LoginResponse> = LoginRepositoryImplementation().login(emailText, passwordText)
                if (result.isSuccessful){
val intent = Intent(context, Home:: class.java)
                startActivity(context, intent, null)}
            else{
                    val errMsg = result.errorBody()?.string()?.let {
                        JSONObject(it).getJSONArray("non_field_errors")[0]
                    }
                    Toast.makeText(context, errMsg.toString(), Toast.LENGTH_SHORT).show()
                }

        }
    }

    fun checkAllFields(email: EditText, emailBox:TextInputLayout, password: EditText, passwordBox: TextInputLayout): Boolean {
        if (email.length() == 0) {
            emailBox.isErrorEnabled = true
            emailBox.setError("This field is required")
            return false
        }

        if (password.length() == 0) {
            passwordBox.isErrorEnabled = true
            passwordBox.setError("Password is required")
            return false
        } else if (password.length() < 8) {
            passwordBox.isErrorEnabled = true
            passwordBox.setError("Password must be minimum 8 characters")
            return false
        }

        return true
    }
}
