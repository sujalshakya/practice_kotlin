package ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import ui.login.view.Login

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val logout: Button = findViewById(R.id.logout_button)

        logout.setOnClickListener {
            val sharedPref = this.getSharedPreferences("Practice",
                Context.MODE_PRIVATE)
            val myEdit: SharedPreferences.Editor? = sharedPref.edit()
            myEdit?.clear()
            myEdit?.apply()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)


        }
    }
}
