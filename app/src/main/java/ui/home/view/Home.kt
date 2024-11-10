package ui.home.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import base.fragments.CustomDialog
import com.example.practice.R
import ui.baseActivity.BaseActivity
import ui.home.viewmodel.HomeViewmodel
import ui.login.view.Login
import ui.users.view.UserView

// Has logout button which deletes token using token manager singleton.
// Has drawer image button which opens drawer.
// Has toggle theme button which changes theme of whole app.
class Home : BaseActivity(), CustomDialog.DialogListener{
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
        val userList: Button = findViewById(R.id.userList)

        drawerLayout = findViewById(R.id.myDrawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up observers
        setupObservers()

        userList.setOnClickListener {
            startActivity(Intent(this, UserView::class.java))
        }

        logout.setOnClickListener {

            val dialog = CustomDialog.newInstance("Do you want to logout?")
            dialog.show(supportFragmentManager, CustomDialog.TAG)
        }

        drawer.setOnClickListener {
            viewModel.openCloseDrawer(drawerLayout)
        }

        themeSwitch.setOnClickListener {
            val dialog = CustomDialog.newInstance("Do you want to change theme?")
            dialog.show(supportFragmentManager, CustomDialog.TAG)        }

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

        private fun setupObservers() {
            viewModel.navigateToLogin.observe(this) { shouldNavigate ->
                if (shouldNavigate) {
                    // Navigate to the Login activity
                    startActivity(Intent(this, Login::class.java))
                    finish() // Close activity
                }
            }
        }
    override fun onPositiveButtonClick(message: String) {
        when (message) {
            "Do you want to logout?" -> {
                viewModel.logout()
            }
            "Do you want to change theme?" -> {
                viewModel.toggleTheme()
                recreate()
            }
        }    }
    }

