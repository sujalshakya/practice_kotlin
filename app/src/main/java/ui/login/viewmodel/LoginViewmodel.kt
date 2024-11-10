package ui.login.viewmodel

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import ui.login.repository.LoginRepositoryImplementation
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepositoryImplementation
)  : ViewModel() {

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> get() = _navigateToHome

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun login(emailText: String, passwordText: String) {
        viewModelScope.launch {
            val result = loginRepository.login(emailText, passwordText)
            if (result.isSuccessful) {
                _navigateToHome.value = true
            } else {
                val errMsg = result.errorBody()?.string()?.let {
                    JSONObject(it).getJSONArray("non_field_errors")[0].toString()
                }
                _errorMessage.value = errMsg ?: "Unknown error"
            }
        }
    }

    fun checkAllFields(email: EditText, emailBox: TextInputLayout, password: EditText, passwordBox: TextInputLayout): Boolean {
        return when {
            email.text.isNullOrEmpty() -> {
                showError(emailBox, "This field is required")
                false
            }
            password.text.isNullOrEmpty() -> {
                showError(passwordBox, "Password is required")
                false
            }
            password.length() < 8 -> {
                showError(passwordBox, "Password must be minimum 8 letters")
                false
            }
            else -> {
                clearError(emailBox)
                clearError(passwordBox)
                true
            }
        }
    }

    private fun showError(textInputLayout: TextInputLayout, message: String) {
        textInputLayout.apply {
            isErrorEnabled = true
            error = message
        }
    }

    private fun clearError(textInputLayout: TextInputLayout) {
        textInputLayout.isErrorEnabled = false
        textInputLayout.error = null
    }
}
