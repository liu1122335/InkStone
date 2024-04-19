package com.example.inkstonedemo1.room.inkstone

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ink_stone_table")
data class InkStone(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ink_stone_id")
    var id : Long = 0,
    @ColumnInfo(name = "ink_stone_dynasty")
    val inkStoneDynasty : String ,
    @ColumnInfo(name = "ink_stone_name")
    val inkStoneName : String,
    @ColumnInfo(name = "ink_stone_type")
    val inkStoneType : String,
    @ColumnInfo(name = "ink_stone_height")
    val inkStoneHeight : String,
    @ColumnInfo(name = "ink_stone_width")
    val inkStoneWidth : String,
    @ColumnInfo(name = "ink_stone_length")
    val inkStoneLength : String,
    @ColumnInfo(name = "ink_stone_description")
    val inkStoneDescription : String,
    @ColumnInfo(name = "ink_stone_ar")
    val isARShow : Boolean,
    @ColumnInfo(name = "ink_stone_collect")
    var isCollected : Boolean,
    @ColumnInfo(name = "ink_stone_image")
    val imageId : Int,
    @ColumnInfo(name = "ink_stone_intact_image")
    val intactImageId : Int,
    @ColumnInfo(name = "ink_stone_ar_path")
    val arPath : String = "3Dmodels/fish.glb"
)