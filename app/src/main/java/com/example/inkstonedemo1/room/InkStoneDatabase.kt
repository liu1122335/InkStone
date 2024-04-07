package com.example.inkstonedemo1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [InkStone::class], version = 1, exportSchema = false)
abstract class InkStoneDatabase : RoomDatabase(){
    abstract fun inkStoneDao() : InkStoneDao
    companion object{
        @Volatile
        private var Instance : InkStoneDatabase ?= null
        fun getDatabase(context: Context): InkStoneDatabase{
            return Instance?: synchronized(this){
                Room.databaseBuilder(context.applicationContext,InkStoneDatabase::class.java,"ink_stone_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}