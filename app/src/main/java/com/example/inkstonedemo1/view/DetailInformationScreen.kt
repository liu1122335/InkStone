package com.example.inkstonedemo1.view

import android.content.ContentValues.TAG
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.dark_golden
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.ScrollableAppBar
import com.example.inkstonedemo1.model.ARShowDestination
import com.example.inkstonedemo1.model.MainInformationDestination
import com.example.inkstonedemo1.model.MainShowDestination
import com.example.inkstonedemo1.model.allDestinations
import java.util.Locale

@Composable
fun DetailInformationScreen(
    mainNavController: NavController
){

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = allDestinations.find { it.route == currentDestination?.route }?: MainShowDestination

    NavHost(
        navController = navController,
        startDestination = MainInformationDestination.route,
        builder = {
            composable(route = MainInformationDestination.route){
                MainInformationScreen(navController = navController, mainNavController = mainNavController)
            }
            composable(route = ARShowDestination.route){
                //ARShowScreen()
            }
        }
    )
}

@Composable
fun MainInformationScreen(
    mainNavController: NavController,
    navController: NavController
){

    val toolbarHeight = 300.dp
    val maxUpPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() - 56.dp.roundToPx().toFloat() }
    // ToolBar 最小向上位移量
    val minUpPx = 0f
    // 偏移折叠工具栏上移高度
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    // 现在，让我们创建与嵌套滚动系统的连接并聆听子 LazyColumn 中发生的滚动
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-maxUpPx, minUpPx)
                return Offset.Zero
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = toolbarHeight)
        ){
            item {
                FunctionButton(modifier = Modifier, navController = navController)
            }

            items(100) { index ->
                Text("I'm item $index", modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
            }
        }
        ScrollableAppBar(
            mainNavController = mainNavController,
            backgroundImageId = R.drawable.test,
            scrollableAppBarHeight = toolbarHeight,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx
        )
    }
}

@Composable
fun FunctionButton(
    modifier: Modifier,
    navController: NavController
){

    var isCollected by remember { mutableStateOf(false) }
    var isSpeak by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        //跳转AR展示画面
        FloatingActionButton(
            onClick = { 
                navController.navigateSingleTopTo(ARShowDestination.route)
            },
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .border(width = 3.dp, color = dark_golden, shape = CircleShape)
                .size(55.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_ar), contentDescription = "")
        }

        //收藏按钮
        FloatingActionButton(
            onClick = {
                isCollected = !isCollected
            },
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .border(width = 3.dp, color = dark_golden, shape = CircleShape)
                .size(55.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
        ) {
            Image(painter = painterResource(id = if (isCollected) R.drawable.ic_collected else R.drawable.ic_uncollected ), contentDescription = "")
        }

        //朗读功能
        FloatingActionButton(
            onClick = {
                isSpeak = !isSpeak
            },
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .border(width = 3.dp, color = dark_golden, shape = CircleShape)
                .size(55.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_ar), contentDescription = "")
        }
    }

}
