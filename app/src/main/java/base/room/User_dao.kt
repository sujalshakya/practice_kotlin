package base.room

import ui.users.model.User
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}