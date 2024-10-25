package com.example.inkstonedemo1.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.inkstonedemo1.data.InitInkStoneData
import com.example.inkstonedemo1.model.uistate.UserScreenUiState
import com.example.inkstonedemo1.ui.theme.InkStoneDemo1Theme
import com.example.inkstonedemo1.view.MainScreen
import com.example.inkstonedemo1.intent.viewmodel.MainScreenViewModel
import com.example.inkstonedemo1.intent.viewmodel.UserScreenViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainScreenViewModel = MainScreenViewModel()
        val userScreenViewModel = UserScreenViewModel()
        val prefs = getSharedPreferences("isFirstUseApp",Context.MODE_PRIVATE)
        val isFirstUseApp = prefs.getBoolean("isFirst",false)
        if (!isFirstUseApp){
            val editor = getSharedPreferences("isFirstUseApp",Context.MODE_PRIVATE).edit()
            editor.putBoolean("isFirst",true)
            editor.apply()
            for (inkStone in InitInkStoneData){
                mainScreenViewModel.insertInkStone(inkStone = inkStone)
            }
            Log.d("mainviewmodel","insertInkStone")
            userScreenViewModel.insertUser(UserScreenUiState().user)
            Log.d("userviewmodel","insertUser")
            userScreenViewModel.insertUserImage(
                background = UserScreenUiState().background,
                avatar = UserScreenUiState().avatar
            )
            Log.d("userviewmodel","insertuserimage")
        }
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkTheme = !isSystemInDarkTheme()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkTheme,
                    isNavigationBarContrastEnforced = false
                )
            }
            InkStoneDemo1Theme {
                Surface(
                    tonalElevation = 5.dp
                ) {
                    ThisApp(mainScreenViewModel = mainScreenViewModel)
                }
            }
        }
    }

    @Composable
    fun ThisApp(
        mainScreenViewModel: MainScreenViewModel
    ){
        MainScreen(mainScreenViewModel = mainScreenViewModel)
    }
}
