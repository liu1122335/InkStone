package com.example.inkstonedemo1.model.uistate

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.platform.LocalContext
import com.example.inkstonedemo1.MyApplication
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.room.user.User

data class UserScreenUiState(
    val user: User = User(
        id = 0,
        name = "游客12138",
        label = "这个人很懒，什么都没留下。",
    ),
    val background : Bitmap = BitmapFactory.decodeResource(MyApplication.context.resources, R.drawable.user_temp_bg),
    val avatar : Bitmap = BitmapFactory.decodeResource(MyApplication.context.resources, R.drawable.user_temp_avatar)
)