package com.tryden12.caddyapp.database
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun addUser(user : User) : Long

    @Update
    fun updateUser(user : User)

    @Delete
    fun deleteUser(user : User)

    /**
     * Deletes ALL info in the table
     */
    @Query("DELETE FROM user")
    fun deleteAllUsers()

    @Query("SELECT * FROM user")
    fun getAllUsers() : List<User>


}