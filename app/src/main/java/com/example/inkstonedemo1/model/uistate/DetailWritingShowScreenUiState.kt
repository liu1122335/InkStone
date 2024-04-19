package com.example.inkstonedemo1.model.uistate

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.inkstonedemo1.MyApplication
import com.example.inkstonedemo1.R

data class DetailWritingShowScreenUiState(
    val currentBitmap : Bitmap = BitmapFactory.decodeResource(MyApplication.context.resources, R.drawable.bg_writing_preview),
    val id : Long = 0
)
