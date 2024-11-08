package ui.baseActivity
import base.room.UserDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import base.service.ThemeManager

open class BaseActivity : AppCompatActivity() {

    override  fun onCreate(savedInstanceState: Bundle?) {
        // Apply the saved theme before calling super.onCreate()
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        val myRoomDatabase = UserDatabase.getDatabase(this)
    }
}