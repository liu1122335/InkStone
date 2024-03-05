package com.example.inkstonedemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.compose.InkStoneDemo1Theme
import com.example.inkstonedemo1.view.MainScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    ThisApp(modifier = Modifier.fillMaxSize());
                }
            }
        }
    }

    @Composable
    fun ThisApp(
        modifier: Modifier
    ){
        MainScreen()
    }
}
