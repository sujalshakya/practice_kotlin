package base.service

import android.content.Context
import com.example.practice.R

object ThemeManager {
    fun applyTheme(context: Context) {
        val theme = SharedPreferenceManager.theme ?: "Dark"

        when (theme) {
            "Light" -> context.setTheme(R.style.Light)
            "Dark" -> context.setTheme(R.style.DarkChange)
        }
    }
    fun saveTheme( theme: String) {
SharedPreferenceManager.theme = theme
    }
}