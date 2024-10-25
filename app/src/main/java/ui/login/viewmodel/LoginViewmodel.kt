package ui.login.viewmodel

import android.content.Context

import android.content.Intent
import android.text.InputType
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text
import retrofit2.Response
import ui.home.Home
import ui.login.model.LoginResponse
import ui.login.repository.LoginRepositoryImplementation

class LoginViewModel (): ViewModel() {

    fun login(emailText: String, passwordText: String, context: Context) {
        viewModelScope.launch {
            try {
                val result : Response<LoginResponse> = LoginRepositoryImplementation(context).login(emailText, passwordText)
                if (result.code()==200){
val intent = Intent(context, Home:: class.java)
                startActivity(context, intent, null)}
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    fun checkAllFields(email: EditText, emailBox:TextInputLayout, password: EditText, passwordBox: TextInputLayout): Boolean {
        if (email.length() == 0) {
            emailBox.isErrorEnabled = true;
            emailBox.setError("This field is required");
            return false
        }

        if (password.length() == 0) {
            passwordBox.isErrorEnabled = true;
            passwordBox.setError("Password is required");
            return false
        } else if (password.length() < 8) {
            passwordBox.isErrorEnabled = true;
            passwordBox.setError("Password must be minimum 8 characters");
            return false
        }

        // after all validation return true.
        return true
    }
}
