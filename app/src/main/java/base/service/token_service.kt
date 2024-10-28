
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object TokenManager {
    private var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("Practice.sharedpref", MODE_PRIVATE)
    }

    var token: String?
        get() = getString()
        set(value) =setString(value)


    private fun getString(): String? = if (sharedPreferences!!.contains("TOKEN")) sharedPreferences!!.getString("TOKEN", "") else null
    private fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString("TOKEN", value) } } ?: remove()
    fun remove() = sharedPreferences!!.edit { remove("TOKEN") }

}
