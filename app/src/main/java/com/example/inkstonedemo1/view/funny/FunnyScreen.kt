package com.example.inkstonedemo1.view.funny

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_light_onTertiaryContainer
import com.example.compose.md_theme_light_tertiaryContainer
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.MainFunnyDestination
import com.example.inkstonedemo1.model.VisitTilesDestination
import com.example.inkstonedemo1.model.WritingDestination
import com.example.inkstonedemo1.view.navigateSingleTopTo

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FunnyScreen(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    var isPreview by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = MainFunnyDestination.route,
        builder = {
            composable(route = MainFunnyDestination.route){
                MainFunnyScreen(navController = navController)
            }
            composable(route = VisitTilesDestination.route){
                VisitTilesScreen()
            }
            composable(route = WritingDestination.route){
                if (isPreview){
                    WritingPreviewScreen { isPreview = false }
                }else{
                    WritingScreen(navController = navController) { isPreview = true }
                }
            }
        }
    )
}

@Composable
fun MainFunnyScreen(
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .paint(
                painter = painterResource(id = R.drawable.bg_funny_frame),
                contentScale = ContentScale.FillBounds
            ),
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .size(300.dp)
                    .clickable { navController.navigateSingleTopTo(VisitTilesDestination.route) },
                colors = CardDefaults.cardColors(containerColor = md_theme_light_tertiaryContainer)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(150.dp),
                        painter = painterResource(id = VisitTilesDestination.icon),
                        contentDescription = "",
                        tint = Color.Black,
                    )

                    Text(
                        text = "砚坑探访",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp),
                        fontSize = 25.sp
                    )
                }
            }

            Card(
                modifier = Modifier
                    .size(300.dp)
                    .clickable { navController.navigateSingleTopTo(WritingDestination.route) },
                colors = CardDefaults.cardColors(containerColor = md_theme_light_onTertiaryContainer)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(150.dp),
                        painter = painterResource(id = WritingDestination.icon),
                        contentDescription = "",
                        tint = Color.White
                    )
                    Text(
                        text = "临池而书",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp),
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}