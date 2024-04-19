package com.example.inkstonedemo1.room.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User) : Long

    @Update
    suspend fun updateUser(user: User)

    @Query("select * from user_table where user_id == 0")
    fun getAllUser() : Flow<User>

}