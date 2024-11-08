package ui.users.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class UserResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
