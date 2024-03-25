package com.example.inkstonedemo1.view

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inkstonedemo1.component.ScrollableAppBar
import com.example.inkstonedemo1.component.VerticalText
import com.example.inkstonedemo1.model.ARShowDestination
import com.example.inkstonedemo1.model.MainInformationDestination
import com.example.inkstonedemo1.viewmodel.DetailInformationScreenViewModel

@Composable
fun DetailInformationScreen(
    mainNavController: NavController,
    currentColor: Color,
    currentImage: Int,
    detailInformationScreenViewModel: DetailInformationScreenViewModel = viewModel()
){
    val detailInformationScreenUiState by detailInformationScreenViewModel.uiState.collectAsState()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainInformationDestination.route,
        builder = {
            composable(route = MainInformationDestination.route){
                MainInformationScreen(
                    currentNavController = navController,
                    mainNavController = mainNavController,
                    currentColor = currentColor,
                    currentImage = currentImage,
                    onCollectedChanged = { detailInformationScreenViewModel.updateIsCollected(it) },
                    isCollected = detailInformationScreenUiState.isCollected
                )
            }
            composable(
                route = ARShowDestination.route,
                enterTransition = {
                    expandIn (
                        animationSpec = tween(1000),
                        expandFrom = Alignment.Center
                    ) {
                        IntSize(0,0)
                    }
                },
                exitTransition = {
                    shrinkOut (
                        animationSpec = tween(1000),
                        shrinkTowards = Alignment.Center
                    ){
                        IntSize(0,0)
                    }
                }
            ){
                ARShowScreen()
            }
        }
    )
}

@Composable
fun MainInformationScreen(
    mainNavController: NavController,
    currentNavController: NavController,
    currentColor: Color,
    @DrawableRes currentImage: Int,
    onCollectedChanged : (Boolean) -> Unit,
    isCollected : Boolean ,
){

    val toolbarHeight = 500.dp
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
                TextContent()
            }
        }
        ScrollableAppBar(
            mainNavController = mainNavController,
            backgroundImageId = currentImage,
            scrollableAppBarHeight = toolbarHeight,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx,
            color = currentColor,
            navController = currentNavController,
            onCollectedChanged = onCollectedChanged,
            isCollected = isCollected,
        )
    }
}

@Composable
fun TextContent(
    
){
    Row (
        modifier = Modifier.padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        VerticalText(
            text = "描述",
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "这里是正文啊暗杀阿斯蒂芬卡拉来得及发士大夫啊快递费杰克萨利啊的撒开了房间啊是",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        )
    }
}

