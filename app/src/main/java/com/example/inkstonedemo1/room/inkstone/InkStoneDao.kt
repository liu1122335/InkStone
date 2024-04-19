package com.example.inkstonedemo1.room.inkstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InkStoneDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInkStone(inkStone: InkStone) : Long

    @Update
    suspend fun updateInkStone(inkStone: InkStone)

    @Query("select * from ink_stone_table")
    fun getAllInkStone() : Flow<List<InkStone>>

    @Query("select * from ink_stone_table where ink_stone_type = :type and ink_stone_id <> :id")
    fun getAllRelevancyInkStone(type : String,id : Long) : Flow<List<InkStone>>

    @Query("select * from ink_stone_table where ink_stone_collect = 1")
    fun getCollectedInkStone() : Flow<List<InkStone>>

    @Query("select * from ink_stone_table where ink_stone_ar = 1")
    fun getARInkStone() : Flow<List<InkStone>>
}