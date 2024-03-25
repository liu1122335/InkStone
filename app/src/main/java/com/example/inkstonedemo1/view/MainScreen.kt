package com.example.inkstonedemo1.view

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inkstonedemo1.component.DotIndicators
import com.example.inkstonedemo1.component.DraggableTab
import com.example.inkstonedemo1.data.allColors
import com.example.inkstonedemo1.data.allInkStoneImages
import com.example.inkstonedemo1.data.allPatterns
import com.example.inkstonedemo1.model.DetailInformationDestination
import com.example.inkstonedemo1.model.IdentifyDestination
import com.example.inkstonedemo1.model.KnowledgeDestination
import com.example.inkstonedemo1.model.MainShowDestination
import com.example.inkstonedemo1.model.UserDestination
import com.example.inkstonedemo1.model.FunnyDestination
import com.example.inkstonedemo1.view.funny.FunnyScreen
import com.example.inkstonedemo1.view.knowledge.KnowledgeScreen
import com.example.inkstonedemo1.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = viewModel()
){
    val mainScreenUiState by mainScreenViewModel.uiState.collectAsState()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainShowDestination.route,
        builder = {
            composable(route = MainShowDestination.route){
                MainShowScreen(
                    navController = navController,
                    onPageChanged = { mainScreenViewModel.updateCurrentPageId(it) }
                )
            }
            composable(route = KnowledgeDestination.route){
                KnowledgeScreen()
            }
            composable(route = FunnyDestination.route){
                FunnyScreen()
            }
            composable(route = DetailInformationDestination.route){
                DetailInformationScreen(
                    mainNavController = navController,
                    currentImage = allInkStoneImages[mainScreenUiState.currentInkStoneId],
                    currentColor = allColors[mainScreenUiState.currentInkStoneId]
                )
            }
            composable(route = UserDestination.route){
                UserScreen()
            }
            composable(
                route = IdentifyDestination.route,
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
                IdentifyScreen()
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainShowScreen(
    navController: NavController,
    onPageChanged : (Int) -> Unit
){
    val pagerCount = allInkStoneImages.size
    val pagerState = rememberPagerState {
        pagerCount
    }

    var currentColor by remember { mutableStateOf(Color.Transparent) }
    var currentPage by remember { mutableStateOf(0) }
    val backgroundColor by animateColorAsState(
        targetValue = currentColor,
        animationSpec = tween(durationMillis = 1200, delayMillis = 2)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {

        //展示图片主页面
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .align(Alignment.Center)
                .height(550.dp),
        ) {index ->
            Log.d("currentPage",pagerState.currentPage.toString())
            Log.d("index",index.toString())
            currentPage = pagerState.currentPage
            currentColor = allColors[currentPage]
            onPageChanged(currentPage)

            InkStonePage(navController = navController, patternId = allPatterns[index], inkStoneId = allInkStoneImages[index])
        }

        //底部指示器
        DotIndicators(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            pagerCount = pagerCount,
            pagerState = pagerState
        )
        //跳转至识别界面
        DraggableTab(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 25.dp, start = 20.dp),
            imageId = IdentifyDestination.icon,
            onClick = { navController.navigateSingleTopTo(IdentifyDestination.route) },
        )

        //跳转趣玩按钮
        DraggableTab(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 30.dp, start = 20.dp),
            imageId = FunnyDestination.icon,
            onClick = { navController.navigateSingleTopTo(FunnyDestination.route) },
        )

        //跳转知识库按钮
        DraggableTab(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 30.dp, end = 20.dp),
            imageId = KnowledgeDestination.icon,
            onClick = { navController.navigateSingleTopTo(KnowledgeDestination.route) },
        )

        //跳转用户界面按钮
        DraggableTab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 25.dp, end = 20.dp),
            imageId = UserDestination.icon,
            onClick = { navController.navigateSingleTopTo(UserDestination.route) },
        )
    }

}

@Composable
fun InkStonePage(
    navController : NavController,
    patternId : Int,
    inkStoneId : Int
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        contentAlignment = Alignment.BottomCenter
    ){
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(300.dp),
            painter = painterResource(id = patternId),
            contentDescription = ""
        )

        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(400.dp)
                .clickable(
                    onClick = {
                        navController.navigateSingleTopTo(DetailInformationDestination.route)
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            painter = painterResource(id = inkStoneId),
            contentDescription = "",
        )
    }
}

fun NavController.navigateSingleTopTo(route : String)=
    this.navigate(route){
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }