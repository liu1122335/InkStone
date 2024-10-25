package com.example.inkstonedemo1.view.funny

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inkstonedemo1.ui.theme.md_theme_light_onTertiaryContainer
import com.example.inkstonedemo1.ui.theme.md_theme_light_tertiaryContainer
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.data.allTiles
import com.example.inkstonedemo1.model.MainVisitDestination
import com.example.inkstonedemo1.model.Tile
import com.example.inkstonedemo1.model.VRShowDestination
import com.example.inkstonedemo1.view.navigateSingleTopTo

@Composable
fun VisitTilesScreen(

){
    val navController = rememberNavController()
    var currentVrPanoramaImage by remember { mutableStateOf(0) }
    NavHost(
        navController = navController,
        startDestination = MainVisitDestination.route,
        builder = {
            composable(route = MainVisitDestination.route){
                MainVisitScreen(onClick = {
                    currentVrPanoramaImage = it
                    navController.navigateSingleTopTo(VRShowDestination.route)
                })
            }
            composable(
                route = VRShowDestination.route,
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
                VRShowScreen(vrPanoramaImage = currentVrPanoramaImage)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainVisitScreen(
    onClick: (Int) -> Unit
){
    Box(modifier = Modifier.background(color = md_theme_light_tertiaryContainer)){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .paint(
                    painter = painterResource(id = R.drawable.bg_visit_frame),
                    contentScale = ContentScale.FillBounds,
                ),
        ){
            val pagerCount = allTiles.size
            val pagerState = rememberPagerState {
                pagerCount
            }
            var currentPager by remember { mutableStateOf(0) }
            var isCoverPage by remember { mutableStateOf(true) }

            VerticalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
            ) {index ->
                Log.d("visit","当前"+pagerState.currentPage)
                isCoverPage = pagerState.currentPage == 0
                currentPager = pagerState.currentPage
                AnimatedVisibility(visible = isCoverPage) {
                    VisitCoverPage()
                }
                AnimatedVisibility(visible = !isCoverPage) {
                    VisitPage(tile = allTiles[currentPager],onClick = onClick)
                }
            }
        }
    }
}

@Composable
fun VisitCoverPage(

){

    Column {
        Text(
            text = "砚坑",
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 60.dp, end = 30.dp, bottom = 30.dp),
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.font_1))
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Image(
                painter = painterResource(id = R.drawable.visit_first_page_cover),
                contentDescription = "",
                modifier = Modifier
                    .size(360.dp)
                    .clip(CutCornerShape(100.dp))
                    .border(
                        width = 6.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                md_theme_light_tertiaryContainer,
                                md_theme_light_onTertiaryContainer
                            )
                        ),
                        shape = CutCornerShape(100.dp)
                    ),
                contentScale = ContentScale.FillBounds
            )

        }
        Text(
            text = "紫云谷",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 30.dp, start = 30.dp, bottom = 15.dp),
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.font_1))
        )
        val infiniteTransition = rememberInfiniteTransition()
        val offsetY by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 25f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1000
                },
                repeatMode = RepeatMode.Reverse
            ),
            label = "offsetY"
        )

        Image(
            painter = painterResource(id = R.drawable.ic_up),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .size(60.dp)
                .offset(x = 0.dp, y = offsetY.toInt().dp)
        )
    }
}
@Composable
fun VisitPage(
    tile : Tile,
    onClick : (Int) -> Unit
){
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp), contentAlignment = Alignment.Center){
            Image(
                painter = painterResource(id = tile.cover),
                contentDescription = "",
                modifier = Modifier
                    .size(360.dp)
                    .clip(CircleShape)
                    .border(
                        width = 6.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                md_theme_light_tertiaryContainer,
                                md_theme_light_onTertiaryContainer
                            )
                        ),
                        shape = CircleShape
                    ),
                contentScale = ContentScale.FillBounds
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tile.name,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.font_1))
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_vr),
                contentDescription = "",
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onClick(tile.vrPanoramaImage)
                        }
                    )
            )
        }
        Text(
            text = tile.introduce,
            modifier = Modifier.padding(horizontal = 15.dp),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.font_1))
        )
    }
}