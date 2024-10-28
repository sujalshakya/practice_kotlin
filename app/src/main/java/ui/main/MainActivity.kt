package ui.main

import TokenManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import ui.home.Home
import ui.login.view.Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        TokenManager.setup(applicationContext)
        val token = TokenManager.token

        if (token != null) {
            val intent = Intent(this@MainActivity, Home::class.java)
            startActivity(intent)

        } else {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)

        }
    }
}
