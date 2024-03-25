package com.example.inkstonedemo1.utils

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore


object BitmapUtils {
    /**
     * 保存图片到图库
     */
    fun saveBitmapToGallery(context: Context, bmp: Bitmap?, bitName: String?): String {
        //插入到系统图库
        return MediaStore.Images.Media.insertImage(context.contentResolver, bmp, "菜单", bitName)
    }
}