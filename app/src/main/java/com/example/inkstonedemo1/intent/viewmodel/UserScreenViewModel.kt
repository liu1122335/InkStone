package com.example.inkstonedemo1.intent.viewmodel

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inkstonedemo1.MyApplication
import com.example.inkstonedemo1.model.uistate.UserScreenUiState
import com.example.inkstonedemo1.room.calligraphy.CalligraphyDatabaseHelper
import com.example.inkstonedemo1.room.user.UserImageDatabaseHelper
import com.example.inkstonedemo1.room.user.User
import com.example.inkstonedemo1.room.user.UserDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class UserScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserScreenUiState())
    val uiState : StateFlow<UserScreenUiState> = _uiState.asStateFlow()

    private val userDao = UserDatabase.getDatabase(MyApplication.context).userDao()
    private val userImageDBHelper = UserImageDatabaseHelper(MyApplication.context,"user_image_database",1)
    private val calligraphyDBHelper = CalligraphyDatabaseHelper(MyApplication.context,"calligraphy_database",1)

    val allUser : Flow<User> =  userDao.getAllUser()
    fun insertUser(user: User){
        viewModelScope.launch{
            user.id = userDao.insertUser(user)
        }
    }

    private fun updateUser(user: User){
        viewModelScope.launch {
            userDao.updateUser(user)
        }
    }

    fun insertCalligraphy(calligraphy : Bitmap){
        val db = calligraphyDBHelper.writableDatabase
        val os = ByteArrayOutputStream()
        calligraphy.compress(Bitmap.CompressFormat.JPEG,50,os)
        val cv = ContentValues()
        cv.put("image",os.toByteArray())
        db.insert("calligraphy_table",null,cv)
        db.close()
    }

    @SuppressLint("Range")
    fun getCalligraphy() : List<Bitmap> {
        val db = calligraphyDBHelper.writableDatabase
        val calligraphyList = arrayListOf<Bitmap>()
        val cursor = db.query("calligraphy_table",null,null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            do {
                val calligraphy = cursor.getBlob(cursor.getColumnIndex("image"));
                val calligraphyBitmap = BitmapFactory.decodeByteArray(calligraphy,0,calligraphy.size)
                calligraphyList.add(calligraphyBitmap)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return calligraphyList
    }
    @SuppressLint("Range")
    fun getCalligraphyId() : List<Long> {
        val db = calligraphyDBHelper.writableDatabase
        val calligraphyList = arrayListOf<Long>()
        val cursor = db.query("calligraphy_table",null,null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            do {
                val calligraphyId = cursor.getLong(cursor.getColumnIndex("id"));
                calligraphyList.add(calligraphyId)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return calligraphyList
    }

    fun deleteCalligraphy(id : Long){
        val db = calligraphyDBHelper.writableDatabase
        db.delete("calligraphy_table","id = ? ", arrayOf("$id"))
        db.close()
    }

    fun insertUserImage(background: Bitmap,avatar: Bitmap){
        val db = userImageDBHelper.writableDatabase
        val os1 = ByteArrayOutputStream()
        val os2 = ByteArrayOutputStream()
        background.compress(Bitmap.CompressFormat.JPEG, 50, os1)
        avatar.compress(Bitmap.CompressFormat.JPEG,50,os2)
        val cv=ContentValues()
        cv.put("background",os1.toByteArray())
        cv.put("avatar",os2.toByteArray())
        db.insert("user_image",null,cv)
        db.close()
    }

    @SuppressLint("Range")
    fun getUserImage() : List<Bitmap>{
        val db = userImageDBHelper.writableDatabase
        val userImageList = arrayListOf<Bitmap>()
        val cursor=db.query("user_image",null,null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            do {
                val background = cursor.getBlob(cursor.getColumnIndex("background"))
                val avatar = cursor.getBlob(cursor.getColumnIndex("avatar"))
                val backgroundBitmap = BitmapFactory.decodeByteArray(background, 0, background.size)
                val avatarBitmap = BitmapFactory.decodeByteArray(avatar,0,avatar.size)
                userImageList.add(backgroundBitmap)
                userImageList.add(avatarBitmap)
            }while(cursor.moveToNext());
        }
        cursor.close()
        db.close()
        return userImageList
    }

    private fun updateUserImage(background: Bitmap, avatar: Bitmap){
        val db = userImageDBHelper.writableDatabase
        val os1 = ByteArrayOutputStream()
        val os2 = ByteArrayOutputStream()
        background.compress(Bitmap.CompressFormat.JPEG, 50, os1)
        avatar.compress(Bitmap.CompressFormat.JPEG,50,os2)
        val cv=ContentValues()
        cv.put("background",os1.toByteArray())
        cv.put("avatar",os2.toByteArray())
        db.update("user_image",cv,"id = ?", arrayOf("1"))
        db.close()
    }

    fun updateUiState(name: String , label : String ,background: Bitmap,avatar: Bitmap){
        _uiState.update {currentUiState ->
            currentUiState.copy(
                user = currentUiState.user.copy(name = name, label = label),
                background = background,
                avatar = avatar
            )
        }
    }

    fun updateDatabaseData(user: User,background: Bitmap,avatar: Bitmap){
        updateUser(user = user)
        updateUserImage(background = background,avatar = avatar)
    }
}