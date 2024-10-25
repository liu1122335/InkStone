package com.example.inkstonedemo1.view.user

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.CollectedAnimatedCircle
import com.example.inkstonedemo1.component.SmallTabRow
import com.example.inkstonedemo1.component.extractProportions
import com.example.inkstonedemo1.model.CollectedShowDestination
import com.example.inkstonedemo1.model.DetailWritingShowDestination
import com.example.inkstonedemo1.model.EditUserDestination
import com.example.inkstonedemo1.model.WritingShowDestination
import com.example.inkstonedemo1.model.userDestinations
import com.example.inkstonedemo1.room.inkstone.InkStone
import com.example.inkstonedemo1.ui.theme.main_surface_color
import com.example.inkstonedemo1.ui.theme.md_theme_light_primaryContainer
import com.example.inkstonedemo1.view.navigateSingleTopTo
import com.example.inkstonedemo1.intent.viewmodel.UserScreenViewModel


private val indexTypeList = listOf("端砚","歙砚","陶砚","洮砚","澄泥砚","瓷砚")
@Composable
fun UserScreen(
    onDetailClicked: (String) -> Unit,
    onDetailWritingClicked: (String, Bitmap, Long) -> Unit,
    collectedList: List<InkStone>,
    userScreenViewModel : UserScreenViewModel
){
    val userScreenUiState by userScreenViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .fillMaxWidth(),
        ) {
            //背景图片
            Image(
                bitmap = userScreenUiState.background.asImageBitmap(),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier.fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                Pair(0.4f, Color.Transparent),
                                Pair(1f, main_surface_color)
                            )
                        )
                    )
            )

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd)
                    .padding(30.dp)
                    .clip(CircleShape)
                    .border(width = 3.dp, color = main_surface_color, shape = CircleShape)
                ,
                onClick = {
                    onDetailClicked(EditUserDestination.route)
                }
            ){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 15.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    //头像图片
                    Image(
                        bitmap = userScreenUiState.avatar.asImageBitmap(),
                        contentDescription = "avatar",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(width = 3.dp, color = main_surface_color, shape = CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = userScreenUiState.user.name,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 10.dp),
                        fontFamily = FontFamily(Font(R.font.font_1))
                    )
                }
                Text(
                    text = userScreenUiState.user.label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.font_1))
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStack?.destination
            val currentScreen = userDestinations.find { it.route == currentDestination?.route }?: CollectedShowDestination
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SmallTabRow(
                    allScreen = userDestinations,
                    onTabSelected = {destination ->
                        navController.navigateSingleTopTo(destination.route)
                    },
                    currentDestination = currentScreen,
                    backgroundColor = md_theme_light_primaryContainer,
                )
                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = CollectedShowDestination.route,
                    builder = {
                        composable(route = CollectedShowDestination.route){
                            CollectedShowScreen(collectedList = collectedList)
                        }
                        composable(route = WritingShowDestination.route){
                            WritingShowScreen(userScreenViewModel = userScreenViewModel, onDetailWritingClicked = onDetailWritingClicked)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CollectedShowScreen(
    collectedList: List<InkStone>
){
    val allTypeCountList = getAllTypeCount(collectedList)
    val collectedProportions = allTypeCountList.extractProportions { it }
    val circleColors = listOf(
        Color(0xFFD6D5B7),
        Color(0xFFD1BA74),
        Color(0xFFE6CEAC),
        Color(0xFFC7EDCC),
        Color(0xFFd2d97a),
        Color(0xFFf0d695)
    )
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()){
            CollectedAnimatedCircle(
                proportions = collectedProportions,
                colors = circleColors,
                modifier = Modifier.size(250.dp)
                    .align(Alignment.Center)
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "收藏数",
                    fontFamily = FontFamily(Font(R.font.font_1))
                )
                Text(
                    text = collectedList.size.toString(),
                    fontFamily = FontFamily(Font(R.font.font_1))
                )
            }
        }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 15.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(indexTypeList.size){
                Card(
                    colors = CardDefaults.cardColors(containerColor = main_surface_color),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = indexTypeList[it],
                                fontFamily = FontFamily(Font(R.font.font_1))
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Box(modifier = Modifier.size(width = 60.dp, height = 5.dp).background(color = circleColors[it]))
                        }
                        Text(
                            text = allTypeCountList[it].toInt().toString(),
                            fontFamily = FontFamily(Font(R.font.font_1))
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun WritingShowScreen(
    userScreenViewModel: UserScreenViewModel,
    onDetailWritingClicked : (String, Bitmap, Long) -> Unit
){
    val calligraphyList by remember { mutableStateOf(userScreenViewModel.getCalligraphy()) }
    val calligraphyIdList by remember { mutableStateOf(userScreenViewModel.getCalligraphyId()) }
    Box(
        modifier = Modifier.fillMaxSize().padding(all = 20.dp)
    ){
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(calligraphyList.size){
                Image(
                    bitmap = calligraphyList[it].asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier.size(width = 200.dp, height = 400.dp)
                        .clickable { onDetailWritingClicked(DetailWritingShowDestination.route,calligraphyList[it],calligraphyIdList[it]) },
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

fun getAllTypeCount(collectedList: List<InkStone>) : List<Float>{
    var allTypeCountList = arrayListOf(0f,0f,0f,0f,0f,0f)
    collectedList.forEach {
        if (it.inkStoneType == "端砚"){
            allTypeCountList[0]++
        }else if(it.inkStoneType  == "歙砚"){
            allTypeCountList[1]++
        }else if(it.inkStoneType  == "陶砚"){
            allTypeCountList[2]++
        }else if(it.inkStoneType  == "洮砚"){
            allTypeCountList[3]++
        }else if(it.inkStoneType  == "澄泥砚"){
            allTypeCountList[4]++
        }else if(it.inkStoneType  == "瓷砚"){
            allTypeCountList[5]++
        }
    }
    return allTypeCountList
}
