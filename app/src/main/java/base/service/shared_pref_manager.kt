package base.service
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

// Singleton to manage token using shared preference
// object is used to create a singleton in kotlin

object SharedPreferenceManager {
    private var sharedPreferences: SharedPreferences? = null
    // get context at the start of the application.
    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("Practice.sharedpref", MODE_PRIVATE)
    }
// easy access to get and set token
var token: String?
    get() = Key.TOKEN.getString()
    set(value) = Key.TOKEN.setString(value)

    var theme: String?
        get() = Key.THEME.getString()
        set(value) = Key.THEME.setString(value)

    private enum class Key {
        TOKEN, THEME;
        fun getString(): String? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(name, "") else null
        fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()
}
    fun remove() = sharedPreferences!!.edit { remove("TOKEN") }

}
