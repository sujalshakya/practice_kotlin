package ui.home

import base.service.TokenManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import ui.login.view.Login

// Has logout button which deletes token using token manager singleton.
class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Select the xml file view using id.
        setContentView(R.layout.home)
        val logout: Button = findViewById(R.id.logout_button)
        logout.setOnClickListener {
            // delete token
            TokenManager.remove()
            // navigate to login activity and then start it.
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
