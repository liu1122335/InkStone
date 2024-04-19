package com.example.inkstonedemo1.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    var id : Long = 0,
    @ColumnInfo(name = "user_name")
    var name : String,
    @ColumnInfo(name = "user_label")
    var label : String
)
