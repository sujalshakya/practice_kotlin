package ui.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.practice.R
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import ui.baseActivity.BaseActivity
import ui.home.view.Home
import ui.login.viewmodel.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class Login : BaseActivity() {
    // Connect to ViewModel
    @Inject
    lateinit var viewModel: LoginViewModel
    // Variable for validation check
    private var isAllFieldsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val submitButton: Button = findViewById(R.id.button)
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val passwordBox: TextInputLayout = findViewById(R.id.password_box)
        val emailBox: TextInputLayout = findViewById(R.id.email_box)

        // Set up observers
        setupObservers()

        // Handle submit button click
        submitButton.setOnClickListener {
            isAllFieldsChecked = viewModel.checkAllFields(email, emailBox, password, passwordBox)
            if (isAllFieldsChecked) {
                val emailText = email.text.toString()
                val passwordText = password.text.toString()
                viewModel.login(emailText, passwordText)
            }
        }
    }

    private fun setupObservers() {
        viewModel.navigateToHome.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                // Navigate to the Home activity
                startActivity(Intent(this, Home::class.java))
                finish() // Close activity
            }
        }

        // Observe error messages to display as Toasts
        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }


}
