package ui.main

import base.service.TokenManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import ui.home.Home
import ui.login.view.Login

// Navigate to different activity according to the token state.
class MainActivity : AppCompatActivity() {
    // state of activity when it is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        // give context to token manger singleton which can be used till end of app.
        TokenManager.setup(applicationContext)
        // get token
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
