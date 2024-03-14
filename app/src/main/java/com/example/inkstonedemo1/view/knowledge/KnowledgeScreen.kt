package com.example.inkstonedemo1.view.knowledge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.knowledge_screen_bg_color
import com.example.compose.knowledge_screen_top_bar_bg_color
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.MainTabRow
import com.example.inkstonedemo1.data.allHistoryKnowledge
import com.example.inkstonedemo1.model.AppreciateKnowledgeDestination
import com.example.inkstonedemo1.model.HistoryDetailDestination
import com.example.inkstonedemo1.model.HistoryKnowledgeDestination
import com.example.inkstonedemo1.model.ProducedKnowledgeDestination
import com.example.inkstonedemo1.model.knowledgeDestinations
import com.example.inkstonedemo1.view.navigateSingleTopTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KnowledgeScreen(

){
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = knowledgeDestinations.find { it.route == currentDestination?.route }?: HistoryKnowledgeDestination

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = knowledge_screen_bg_color)
            .paint(painter = painterResource(id = R.drawable.bg_element_knowledge_screen))
    ) {
        MainTabRow(
            allScreen = knowledgeDestinations,
            onTabSelected = {destination ->
                navController.navigateSingleTopTo(destination.route)
            },
            currentDestination = currentScreen,
            backgroundColor = knowledge_screen_top_bar_bg_color
        )
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = HistoryKnowledgeDestination.route,
            builder = {
                composable(route = HistoryKnowledgeDestination.route){
                    HistoryKnowledgeScreen()
                }
                composable(route = AppreciateKnowledgeDestination.route){
                    AppreciateKnowledgeScreen()
                }
                composable(route = ProducedKnowledgeDestination.route){
                    ProducedKnowledgeScreen()
                }
            }
        )
    }
}
