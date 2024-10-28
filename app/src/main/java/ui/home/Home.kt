package ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import base.service.SharedPreferenceManager
import base.service.ThemeManager
import com.example.practice.R
import ui.baseActivity.BaseActivity
import ui.login.view.Login

// Has logout button which deletes token using token manager singleton.
class Home : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Select the xml file view using id.
        setContentView(R.layout.home)
        val logout: Button = findViewById(R.id.logout_button)


        logout.setOnClickListener {
            // delete token
            SharedPreferenceManager.remove()
            // navigate to login activity and then start it.
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val themeSwitch : Button = findViewById(R.id.themeSwitch)

        themeSwitch.setOnClickListener{
            val currentTheme = SharedPreferenceManager.theme
            println("theme $currentTheme")
            if (currentTheme=="Light"){
                ThemeManager.saveTheme("Dark")

            } else {
                ThemeManager.saveTheme("Light")
            }
            recreate()
        }
    }
}
