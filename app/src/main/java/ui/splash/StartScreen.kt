package ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import ui.home.Home
import ui.login.view.Login

class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val sharedPreferences = this.getSharedPreferences("Practice", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            val intent = Intent(this@StartScreen, Home::class.java)
            startActivity(intent)


        } else {
            val intent = Intent(this@StartScreen, Login::class.java)
            startActivity(intent)

        }
    }
}
