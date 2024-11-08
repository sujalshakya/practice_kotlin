package ui.users.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import base.room.UserDatabase
import com.example.practice.R
import kotlinx.coroutines.launch
import ui.baseActivity.BaseActivity
import ui.users.viewmodel.UserViewModel

class UserView : BaseActivity() {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val db = UserDatabase.getDatabase(this@UserView)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_view) // Ensure this is the correct layout file

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with an empty list and set it to the RecyclerView
        userAdapter = UserAdapter(emptyList())
        recyclerView.adapter = userAdapter

        // Observe user list and update the adapter's data when it changes
        viewModel.userList.observe(this, Observer { users ->
            users?.let {
                userAdapter.updateData(it)
            }
        })

        // Observe error messages and display them if any
        viewModel.errorMessage.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })



        // Fetch users when the activity is created
        lifecycleScope.launch {
            viewModel.getUsers(db)
        }    }
}