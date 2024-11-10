package ui.users.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import base.room.UserDatabase
import com.example.practice.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ui.baseActivity.BaseActivity
import ui.users.viewmodel.UserViewModel
import javax.inject.Inject
@AndroidEntryPoint
class UserView : BaseActivity() {
    @Inject
    lateinit var viewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var db: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_view)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(emptyList())
        recyclerView.adapter = userAdapter

        db = UserDatabase.getDatabase(applicationContext)

        viewModel.userList.observe(this) { users ->
            users?.let {
                userAdapter.updateData(it)
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            viewModel.getUsers(db)
        }
    }
}
