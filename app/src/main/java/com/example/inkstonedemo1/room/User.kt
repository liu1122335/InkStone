package com.example.inkstonedemo1.room

import androidx.room.Entity

@Entity
data class User(
    var name : String = "游客12138",
    var label : String = "这个人很懒，什么都没留下。",

){

}
