package com.example.inkstonedemo1.room.calligraphy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CalligraphyDatabaseHelper(
    val context: Context,
    name : String,
    version : Int

) : SQLiteOpenHelper(
    context,name,null,version
) {
    private val createCalligraphyDatabase = "create table calligraphy_table (" +
            "id integer primary key autoincrement," +
            "image BLOB )"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createCalligraphyDatabase)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}