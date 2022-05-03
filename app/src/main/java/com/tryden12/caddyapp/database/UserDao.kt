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

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserEmail(email : String) : User

    @Query("SELECT * FROM user WHERE password = :password")
    fun getUserPassword(password : String) : User

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUserCredentials(email : String, password: String) : User


}