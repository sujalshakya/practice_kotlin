package ui.home.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import base.service.SharedPreferenceManager
import base.service.ThemeManager
import com.example.practice.R
import ui.baseActivity.BaseActivity
import ui.home.viewmodel.HomeViewmodel
import ui.login.view.Login

// Has logout button which deletes token using token manager singleton.
// Has drawer image button which opens drawer.
// Has toggle theme button which changes theme of whole app.
class Home : BaseActivity() {
    private val viewModel: HomeViewmodel by viewModels()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle
    private val toggleTheme = { _: DialogInterface, _: Int ->
        val currentTheme = SharedPreferenceManager.theme
        if (currentTheme=="Light"){
            ThemeManager.saveTheme("Dark")
        } else {
            ThemeManager.saveTheme("Light")
        }
        recreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Select the xml file view using id.
        setContentView(R.layout.home)
        val logout: Button = findViewById(R.id.logout_button)
        val drawer : ImageButton = findViewById(R.id.drawerButton)
        val themeSwitch : Button = findViewById(R.id.themeSwitch)
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

        drawer.setOnClickListener{
            openCloseDrawer()
        }

        themeSwitch.setOnClickListener{
            basicAlert()
        }
    }
    private fun openCloseDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private  fun  basicAlert() {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Theme")
            setMessage("Change to dark mode?")
            setPositiveButton("OK", DialogInterface.OnClickListener(function = toggleTheme))
            show()
        }
    }

@Deprecated("This method has been deprecated in favor of using the" +
        "{@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}." +
        "The OnBackPressedDispatcher controls how back button events are dispatched" +
        "to one or more {@link OnBackPressedCallback} objects.")
override fun onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
        super.onBackPressed()
        }
    }
}
