package ui.login.view

import ui.home.Home
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import com.example.practice.R
import kotlinx.coroutines.launch
import ui.login.viewmodel.LoginViewModel

class Login : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val submitButton: Button = findViewById(R.id.button)
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)

        viewModel.navigate.observe(this, Observer { shouldNavigate ->
            if (shouldNavigate == true) {
                val intent = Intent(this@Login, Home::class.java)
                startActivity(intent)
                       }
        })

        submitButton.setOnClickListener {
            val emailText: String = email.text.toString()
            val passwordText: String = password.text.toString()

            lifecycleScope.launch {
                viewModel.login(emailText, passwordText)
            }
        }
    }
}
