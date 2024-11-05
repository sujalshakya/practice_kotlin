package ui.home.viewmodel

import android.content.DialogInterface
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import base.service.SharedPreferenceManager
import base.service.ThemeManager

class HomeViewmodel : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin

    fun openCloseDrawer(drawerLayout: DrawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    val toggleTheme = {
        // Check theme in shared pref and change it
        val currentTheme = SharedPreferenceManager.theme
        if (currentTheme == "Light") {
            ThemeManager.saveTheme("Dark")
        } else {
            ThemeManager.saveTheme("Light")
        }
    }

    fun logout(){
        // delete token
        SharedPreferenceManager.remove()
        _navigateToLogin.value = true
    }
}