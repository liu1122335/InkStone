package com.example.inkstonedemo1.view.knowledge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_light_primaryContainer
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.MainTabRow
import com.example.inkstonedemo1.model.AppreciateKnowledgeDestination
import com.example.inkstonedemo1.model.HistoryKnowledgeDestination
import com.example.inkstonedemo1.model.VisitTilesDestination
import com.example.inkstonedemo1.model.knowledgeDestinations
import com.example.inkstonedemo1.view.navigateSingleTopTo

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
            .paint(painter = painterResource(id = R.drawable.bg_element_knowledge_screen))
    ) {
        MainTabRow(
            allScreen = knowledgeDestinations,
            onTabSelected = {destination ->
                navController.navigateSingleTopTo(destination.route)
            },
            currentDestination = currentScreen,
            backgroundColor = md_theme_light_primaryContainer
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
            }
        )
    }
}
