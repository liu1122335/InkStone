package com.example.inkstonedemo1.view

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.dark_golden
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.DotIndicators
import com.example.inkstonedemo1.data.allColors
import com.example.inkstonedemo1.data.allInkStoneImages
import com.example.inkstonedemo1.data.allPatterns
import com.example.inkstonedemo1.model.DetailInformationDestination
import com.example.inkstonedemo1.model.HistoryDestination
import com.example.inkstonedemo1.model.IdentifyDestination
import com.example.inkstonedemo1.model.KnowledgeDestination
import com.example.inkstonedemo1.model.MainShowDestination
import com.example.inkstonedemo1.model.UserDestination
import com.example.inkstonedemo1.model.allDestinations

@Composable
fun MainScreen(
    modifier: Modifier = Modifier.fillMaxSize()
){

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = allDestinations.find { it.route == currentDestination?.route }?:MainShowDestination
    NavHost(
        navController = navController,
        startDestination = MainShowDestination.route,
        builder = {
            composable(route = MainShowDestination.route){
                MainShowScreen(navController = navController)
            }
            composable(route = KnowledgeDestination.route){
                KnowledgeScreen()
            }
            composable(route = HistoryDestination.route){
                HistoryScreen()
            }
            composable(route = DetailInformationDestination.route){
                DetailInformationScreen(mainNavController = navController)
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
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    navController: NavController
){
    val pagerCount = 3
    val pagerState = rememberPagerState {
        pagerCount
    }

    var currentColor by remember { mutableStateOf(Color.Transparent) }
    var currentPage by remember { mutableStateOf(0) }
    val backgroundColor by animateColorAsState(
        targetValue = currentColor,
        animationSpec = tween(durationMillis = 1500, delayMillis = 2)
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
            currentPage = pagerState.currentPage
            currentColor = allColors[currentPage]

            InkStonePage(navController = navController, patternId = allPatterns[index], inkStoneId = allInkStoneImages[index])
        }

        //跳转知识库按钮
        FloatingActionButton(
            onClick = {
                 navController.navigateSingleTopTo(KnowledgeDestination.route)
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp, end = 20.dp)
                .border(width = 3.dp, color = dark_golden, shape = CircleShape)
                .size(55.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_knowledge), contentDescription = "")
        }

        //跳转历史线按钮
        FloatingActionButton(
            onClick = {
                navController.navigateSingleTopTo(HistoryDestination.route)
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 40.dp, start = 20.dp)
                .border(width = 3.dp, color = dark_golden, shape = CircleShape)
                .size(55.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_history), contentDescription = "")
        }

        //跳转用户界面按钮
        FloatingActionButton(
            onClick = {
                navController.navigateSingleTopTo(UserDestination.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 30.dp)
                .size(55.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_user), contentDescription = "", modifier = Modifier.fillMaxSize())
        }

        //跳转至识别界面
        FloatingActionButton(
            onClick = {
                navController.navigateSingleTopTo(IdentifyDestination.route)
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 30.dp, bottom = 30.dp)
                .size(55.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_identify), contentDescription = "", modifier = Modifier.fillMaxSize())
        }

        //底部指示器
        DotIndicators(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            pagerCount = pagerCount,
            pagerState = pagerState
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