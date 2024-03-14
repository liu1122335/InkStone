package com.example.inkstonedemo1.view.knowledge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.knowledge_screen_top_bar_bg_color
import com.example.inkstonedemo1.data.allHistoryKnowledge
import com.example.inkstonedemo1.model.HistoryDetailDestination
import com.example.inkstonedemo1.model.HistoryKnowledge
import com.example.inkstonedemo1.model.MainHistoryKnowledgeDestination
import com.example.inkstonedemo1.view.navigateSingleTopTo
import com.example.inkstonedemo1.viewmodel.HistoryKnowledgeScreenViewModel

@Composable
fun HistoryKnowledgeScreen(
    historyKnowledgeScreenViewModel: HistoryKnowledgeScreenViewModel = viewModel()
){
    val navController = rememberNavController()
    val historyKnowledgeScreenUiState by historyKnowledgeScreenViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = MainHistoryKnowledgeDestination.route,
    ){
        composable(route = MainHistoryKnowledgeDestination.route){
            MainHistoryKnowledgeScreen(onClick = { it ->
                historyKnowledgeScreenViewModel.updateCurrentHistoryId(it)
                navController.navigateSingleTopTo(HistoryDetailDestination.route)
            })
        }
        composable(route = HistoryDetailDestination.route){
            HistoryDetailScreen(historyKnowledge = allHistoryKnowledge[historyKnowledgeScreenUiState.currentHistoryId])
        }
    }
}
@Composable
fun MainHistoryKnowledgeScreen(
    onClick: (Int) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ){
            items(allHistoryKnowledge.size){
                HistoryKnowledgeCard(historyKnowledge = allHistoryKnowledge[it], index = it , onClick = onClick)
            }
        }
    }
}

@Composable
fun HistoryKnowledgeCard(
    index : Int ,
    historyKnowledge: HistoryKnowledge,
    onClick : (Int) -> Unit
){
    Card(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    onClick(index)
                }
            ),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = knowledge_screen_top_bar_bg_color.copy(alpha = 0.25f))
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(id = historyKnowledge.relevancyImage[0]),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = historyKnowledge.title,
                        color = Color.White,
                    )
                }

                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 5.dp),
                    tint = Color.White
                )
            }
        }
    }
}