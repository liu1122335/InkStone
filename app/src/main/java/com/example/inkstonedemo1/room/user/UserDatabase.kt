package com.example.inkstonedemo1.room.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

    companion object{
        private var Instance : UserDatabase ?= null
        fun getDatabase(context: Context):UserDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"user_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}