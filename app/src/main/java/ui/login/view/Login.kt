package ui.login.view


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.practice.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ui.baseActivity.BaseActivity
import ui.login.viewmodel.LoginViewModel

class Login : BaseActivity() {
    // connect to viewmodel.
    private val viewModel: LoginViewModel by viewModels()
    // var for validation check
    private var isAllFieldsChecked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val submitButton: Button = findViewById(R.id.button)
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val passwordBox: TextInputLayout = findViewById(R.id.password_box)
        val emailBox: TextInputLayout = findViewById(R.id.email_box)

        submitButton.setOnClickListener {
            isAllFieldsChecked = viewModel.checkAllFields(email, emailBox, password, passwordBox)

            val emailText: String = email.text.toString()
            val passwordText: String = password.text.toString()
            if (isAllFieldsChecked) {
            lifecycleScope.launch {
                viewModel.login(emailText, passwordText, this@Login)
            }}
        }

    }


}
