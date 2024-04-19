package com.example.inkstonedemo1.view.funny

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inkstonedemo1.ui.theme.md_theme_light_onTertiaryContainer
import com.example.inkstonedemo1.ui.theme.md_theme_light_tertiaryContainer
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.Destination
import com.example.inkstonedemo1.model.IdentifyDestination
import com.example.inkstonedemo1.model.VisitTilesDestination
import com.example.inkstonedemo1.model.WritingDestination
import com.example.inkstonedemo1.ui.theme.main_surface_color
import com.example.inkstonedemo1.ui.theme.md_theme_light_tertiary

@Composable
fun FunnyScreen(
    onDetailClicked: (String) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        MainFunnyScreen(onDetailClicked = onDetailClicked)
    }
}

@Composable
fun MainFunnyScreen(
    onDetailClicked: (String) -> Unit
){
    val backColors = listOf(
        md_theme_light_tertiaryContainer,
        md_theme_light_tertiary,
        md_theme_light_onTertiaryContainer
    )
    val contentColors = listOf(
        Color.Black,
        main_surface_color,
        Color.White
    )
    val titles = listOf(
        "砚坑探访",
        "识砚获知",
        "临池而书"
    )
    val destinations = listOf(VisitTilesDestination,IdentifyDestination,WritingDestination)
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
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(3){
                FunnyChildrenDestinationsCard(
                    onDetailClicked = onDetailClicked,
                    destination = destinations[it],
                    backColor = backColors[it],
                    title = titles[it],
                    contentColor = contentColors[it]
                )
            }
        }
    }
}

@Composable
fun FunnyChildrenDestinationsCard(
    onDetailClicked: (String) -> Unit,
    destination: Destination,
    backColor: Color,
    title: String,
    contentColor: Color
){
    Card(
        modifier = Modifier
            .size(width = 300.dp, height = 200.dp)
            .clickable { onDetailClicked(destination.route) },
        colors = CardDefaults.cardColors(containerColor = backColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = destination.icon),
                contentDescription = "",
                tint = contentColor,
            )

            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp),
                fontSize = 25.sp,
                color = contentColor
            )
        }
    }
}