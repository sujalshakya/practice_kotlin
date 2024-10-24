package ui.login.view

import RetrofitHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import ui.login.service.ApiService
import ui.login.viewmodel.LoginViewmodel

class Login  : AppCompatActivity() {
    private val viewModel: LoginViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
val submitButton: Button = findViewById(R.id.button)
        val email: EditText =  findViewById(R.id.email)
val password: EditText = findViewById(R.id.password)
        submitButton.setOnClickListener {
val retrofitHelper = RetrofitHelper.getInstance().create(ApiService :: class.java)
            val emailText: String = email.text.toString()
            val passwordText: String = password.text.toString()
          viewModel.login( emailText, passwordText)


        }}}