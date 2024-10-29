package ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import base.service.SharedPreferenceManager
import base.service.ThemeManager
import com.example.practice.R
import ui.baseActivity.BaseActivity
import ui.home.viewmodel.HomeViewmodel
import ui.login.view.Login

// Has logout button which deletes token using token manager singleton.
class Home : BaseActivity() {
    private val viewModel: HomeViewmodel by viewModels()
    private lateinit var drawerLayout: DrawerLayout
    lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Select the xml file view using id.
        setContentView(R.layout.home)
        val logout: Button = findViewById(R.id.logout_button)
        drawerLayout = findViewById(R.id.myDrawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            if (currentTheme=="Light"){
                ThemeManager.saveTheme("Dark")
            } else {
                ThemeManager.saveTheme("Light")
            }
            recreate()
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }else{
            super.onOptionsItemSelected(item)
        }

    }
}
