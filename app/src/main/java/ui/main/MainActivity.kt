package ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import base.service.SharedPreferenceManager
import com.example.practice.R
import ui.home.view.Home
import ui.login.view.Login

// Navigate to different activity according to the token state.
class MainActivity : AppCompatActivity() {
    // state of activity when it is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        // give context to token manger singleton which can be used till end of app.
        SharedPreferenceManager.setup(applicationContext)
        // get token
        val token = SharedPreferenceManager.token

        if (token != null) {
            val intent = Intent(this@MainActivity, Home::class.java)
            startActivity(intent)
            finish()

        } else {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
            finish()

        }
    }
}
