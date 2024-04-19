package com.example.inkstonedemo1.room.user

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserImageDatabaseHelper(
    val context: Context,
    name : String,
    version : Int
) : SQLiteOpenHelper(
    context,name,null,version
) {

    private val createUserImageDatabase = "create table user_image (" +
        "id integer primary key autoincrement," +
        "background BLOB,"+
        "avatar BLOB )"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createUserImageDatabase)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}