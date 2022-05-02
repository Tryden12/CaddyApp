package com.tryden12.caddyapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class User(
    @PrimaryKey(autoGenerate = true) val userID : Long,
    @ColumnInfo() val email : String,
    @ColumnInfo() val password : String,
    @ColumnInfo() val phoneNumber : String,
) {
    override fun toString(): String {
        return "$email $password $phoneNumber $userID"
    }
}