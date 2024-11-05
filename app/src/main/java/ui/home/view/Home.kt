package ui.home.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
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
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Select the xml file view using id.
        setContentView(R.layout.home)
        val logout: Button = findViewById(R.id.logout_button)
        val drawer: ImageButton = findViewById(R.id.drawerButton)
        val themeSwitch: Button = findViewById(R.id.themeSwitch)
        drawerLayout = findViewById(R.id.myDrawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up observers
        setupObservers()

        logout.setOnClickListener {
            viewModel.logout()
        }

        drawer.setOnClickListener {
            viewModel.openCloseDrawer(drawerLayout)
        }

        themeSwitch.setOnClickListener {
            basicAlert()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun basicAlert() {
        AlertDialog.Builder(this).apply {
            setTitle("Theme")
            setMessage("Change theme mode?")
            setPositiveButton("OK") { _:DialogInterface, _:Int ->
                viewModel.toggleTheme()
                recreate()
            }
            setNegativeButton("Cancel", null)
            show()
            }
        }

        private fun setupObservers() {
            viewModel.navigateToLogin.observe(this, Observer { shouldNavigate ->
                if (shouldNavigate) {
                    // Navigate to the Login activity
                    startActivity(Intent(this, Login::class.java))
                    finish() // Close activity
                }
            })
        }
    }
