package com.example.inkstonedemo1.view

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inkstonedemo1.component.MainTabRow
import com.example.inkstonedemo1.data.InitInkStoneData
import com.example.inkstonedemo1.data.allColors
import com.example.inkstonedemo1.model.AllAppDestination
import com.example.inkstonedemo1.model.AllInkStoneListDestination
import com.example.inkstonedemo1.model.AppreciateKnowledgeDestination
import com.example.inkstonedemo1.model.CraftsmanKnowledgeDestination
import com.example.inkstonedemo1.model.DetailInformationDestination
import com.example.inkstonedemo1.model.DetailWritingShowDestination
import com.example.inkstonedemo1.model.EditUserDestination
import com.example.inkstonedemo1.model.KnowledgeDestination
import com.example.inkstonedemo1.model.UserDestination
import com.example.inkstonedemo1.model.FunnyDestination
import com.example.inkstonedemo1.model.HistoryKnowledgeDestination
import com.example.inkstonedemo1.model.IdentifyDestination
import com.example.inkstonedemo1.model.MainMenuDestinations
import com.example.inkstonedemo1.model.VisitTilesDestination
import com.example.inkstonedemo1.model.WritingDestination
import com.example.inkstonedemo1.model.uistate.UserScreenUiState
import com.example.inkstonedemo1.room.inkstone.InkStone
import com.example.inkstonedemo1.room.user.User
import com.example.inkstonedemo1.view.detail.DetailInformationScreen
import com.example.inkstonedemo1.view.funny.FunnyScreen
import com.example.inkstonedemo1.view.funny.VisitTilesScreen
import com.example.inkstonedemo1.view.funny.WritingPreviewScreen
import com.example.inkstonedemo1.view.funny.WritingScreen
import com.example.inkstonedemo1.view.identify.IdentifyScreen
import com.example.inkstonedemo1.view.knowledge.AllInkStoneListScreen
import com.example.inkstonedemo1.view.knowledge.AppreciateKnowledgeScreen
import com.example.inkstonedemo1.view.knowledge.CraftsmanKnowledgeScreen
import com.example.inkstonedemo1.view.knowledge.HistoryKnowledgeScreen
import com.example.inkstonedemo1.view.knowledge.KnowledgeScreen
import com.example.inkstonedemo1.view.user.DetailWritingShowScreen
import com.example.inkstonedemo1.view.user.EditUserScreen
import com.example.inkstonedemo1.view.user.UserScreen
import com.example.inkstonedemo1.viewmodel.DetailWritingShowScreenViewModel
import com.example.inkstonedemo1.viewmodel.MainScreenViewModel
import com.example.inkstonedemo1.viewmodel.UserScreenViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = viewModel(),
    userScreenViewModel: UserScreenViewModel= viewModel(),
    detailWritingShowScreenViewModel: DetailWritingShowScreenViewModel = viewModel(),
){
    val allInkStone by mainScreenViewModel.allInkStone.collectAsState(initial = InitInkStoneData)
    val collectedList by mainScreenViewModel.allCollectedInkStone.collectAsState(initial = InitInkStoneData)
    val allARInkStone by mainScreenViewModel.allARInkStone.collectAsState(initial = InitInkStoneData)

    val user by userScreenViewModel.allUser.collectAsState(initial = UserScreenUiState().user)
    val userImageList = userScreenViewModel.getUserImage()
    userScreenViewModel.updateUiState(
        name = user.name,
        label = user.label,
        background = userImageList[0],
        avatar = userImageList[1]
    )

    val navController = rememberNavController()
    var showLandingScreen by remember { mutableStateOf(true) }
    var isPreview by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        NavHost(
            navController = navController,
            startDestination = AllAppDestination.route,
            builder ={
                composable(route = AllAppDestination.route){
                    AllAppScreen(
                        onDetailClicked = {
                            navController.navigateSingleTopTo(it)
                        },
                        collectedList = collectedList,
                        allARInkStone = allARInkStone,
                        onInkStoneCardClicked = {route,id ->
                            mainScreenViewModel.updateCurrentUiState(inkStone = allARInkStone[id])
                            navController.navigateSingleTopTo(route = route)
                        },
                        userScreenViewModel = userScreenViewModel,
                        onDetailWritingClicked = {route,bitmap,id->
                            navController.navigateSingleTopTo(route = route)
                            detailWritingShowScreenViewModel.updateUiState(bitmap = bitmap,id = id)
                        }
                    )
                }
                composable(route = HistoryKnowledgeDestination.route){
                    HistoryKnowledgeScreen(onBackButtonClicked = { navController.popBackStack() })
                }
                composable(route = CraftsmanKnowledgeDestination.route){
                    CraftsmanKnowledgeScreen(onBackButtonClicked = { navController.popBackStack() })
                }
                composable(route = AppreciateKnowledgeDestination.route){
                    AppreciateKnowledgeScreen(onBackButtonClicked = { navController.popBackStack() })
                }
                composable(route = AllInkStoneListDestination.route){
                    AllInkStoneListScreen(
                        onBackButtonClicked = { navController.popBackStack() },
                        allInkStoneList = allInkStone,
                        mainScreenViewModel = mainScreenViewModel,
                    )
                }
                composable(route = DetailInformationDestination.route,){
                    DetailInformationScreen(
                        currentColor = allColors[0],
                        onBackButtonClicked = { navController.popBackStack() },
                        mainScreenViewModel = mainScreenViewModel,
                        onRelevancyInkStoneClicked = {
                            mainScreenViewModel.updateCurrentUiState(inkStone = it)
                            navController.navigateTopTopTo(DetailInformationDestination.route)
                        }
                    )
                }
                composable(route = VisitTilesDestination.route){
                    VisitTilesScreen()
                }
                composable(route = WritingDestination.route){
                    if (isPreview){
                        WritingPreviewScreen(
                            onBack = { isPreview = false },
                            userScreenViewModel = userScreenViewModel
                        )
                    }else{
                        WritingScreen(navController = navController) { isPreview = true }
                    }
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
                composable(route = EditUserDestination.route){
                    EditUserScreen(
                        onBackButtonClicked = { navController.popBackStack() },
                        userScreenViewModel = userScreenViewModel
                    )
                }
                composable(route = DetailWritingShowDestination.route){
                    DetailWritingShowScreen(
                        detailWritingShowScreenViewModel = detailWritingShowScreenViewModel,
                        userScreenViewModel = userScreenViewModel,
                        onBackButtonClicked = { navController.popBackStack() }
                    )
                }
            }
        )
        AnimatedVisibility(
            visible = showLandingScreen,
            modifier = Modifier.fillMaxSize()
        ){
            LandingScreen(onTimeout = { showLandingScreen = false })
        }
    }
}

@Composable
fun AllAppScreen(
    onDetailClicked: (String) -> Unit,
    collectedList: List<InkStone>,
    allARInkStone: List<InkStone>,
    onInkStoneCardClicked: (String, Int) -> Unit,
    userScreenViewModel: UserScreenViewModel,
    onDetailWritingClicked : (String, Bitmap, Long) -> Unit
){
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = MainMenuDestinations.find { it.route == currentDestination?.route }?: KnowledgeDestination
    Scaffold(
        bottomBar = {
            MainTabRow(
                allScreen = MainMenuDestinations,
                onTabSelected = {
                    navController.navigateSingleTopTo(it.route)
                },
                currentDestination = currentScreen,
            )
        }
    ) {it
        NavHost(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
            navController = navController,
            startDestination = KnowledgeDestination.route,
            builder = {
                composable(route = KnowledgeDestination.route){
                    KnowledgeScreen(
                        allARInkStone = allARInkStone,
                        onDetailClicked = onDetailClicked,
                        onInkStoneCardClicked = onInkStoneCardClicked
                    )
                }
                composable(route = FunnyDestination.route){
                    FunnyScreen(onDetailClicked = onDetailClicked)
                }
                composable(route = UserDestination.route){
                    UserScreen(
                        onDetailClicked = onDetailClicked,
                        collectedList = collectedList,
                        userScreenViewModel = userScreenViewModel,
                        onDetailWritingClicked = onDetailWritingClicked
                    )
                }
            }
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
fun NavController.navigateTopTopTo(route : String)=
    this.navigate(route){
        popUpTo(
            this@navigateTopTopTo.graph.findStartDestination().id
        ){
            saveState = false
        }
        launchSingleTop = true
        restoreState = false
    }