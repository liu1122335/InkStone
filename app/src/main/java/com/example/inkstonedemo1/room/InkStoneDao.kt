package com.example.inkstonedemo1.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InkStoneDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInkStone(inkStone: InkStone)

    @Update
    suspend fun updateInkStone(inkStone: InkStone)

    @Query("select * from ink_stone_table")
    fun getAllInkStone() : Flow<List<InkStone>>

    @Query("select * from ink_stone_table where ink_stone_type = :type")
    fun getAllRelevancyInkStone(type : String) : Flow<List<InkStone>>

    @Query("select * from ink_stone_table where ink_stone_collect = 1")
    fun getCollectedInkStone() : Flow<List<InkStone>>
}