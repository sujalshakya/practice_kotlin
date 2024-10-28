package base.service
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

// Singleton to manage token using shared preference
// object is used to create a singleton in kotlin
object TokenManager {
    private var sharedPreferences: SharedPreferences? = null
    // get context at the start of the application.
    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("Practice.sharedpref", MODE_PRIVATE)
    }
// easy access to get and set token
    var token: String?
        get() = getString()
        set(value) = setString(value)

    private fun getString(): String? = if (sharedPreferences!!.contains("TOKEN")) sharedPreferences!!.getString("TOKEN", "") else null
    private fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString("TOKEN", value) } } ?: remove()
    fun remove() = sharedPreferences!!.edit { remove("TOKEN") }

}
