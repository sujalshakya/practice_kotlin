package ui.users.view

import ui.users.model.User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice.R

class UserAdapter(private var users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder class to hold each item view
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userAvatar: ImageView = view.findViewById(R.id.userAvatar)
        val userName: TextView = view.findViewById(R.id.userName)
        val userEmail: TextView = view.findViewById(R.id.userEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.userName.text = "${user.first_name} ${user.last_name}"
        holder.userEmail.text = user.email

        // Load avatar image using Glide
        Glide.with(holder.userAvatar.context)
            .load(user.avatar)
            .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image in drawable
            .into(holder.userAvatar)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // Function to update data and notify the adapter
    fun updateData(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }
}
