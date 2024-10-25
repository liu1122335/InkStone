package com.example.inkstonedemo1.view.knowledge

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.data.allColors
import com.example.inkstonedemo1.model.AllInkStoneListDestination
import com.example.inkstonedemo1.model.DetailInformationDestination
import com.example.inkstonedemo1.model.MainAllInkStoneListDestination
import com.example.inkstonedemo1.room.inkstone.InkStone
import com.example.inkstonedemo1.view.detail.DetailInformationScreen
import com.example.inkstonedemo1.view.navigateTopTopTo
import com.example.inkstonedemo1.intent.viewmodel.MainScreenViewModel

@Composable
fun AllInkStoneListScreen(
    onBackButtonClicked : () -> Unit,
    allInkStoneList : List<InkStone>,
    mainScreenViewModel: MainScreenViewModel, ){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        val navController = rememberNavController()
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = MainAllInkStoneListDestination.route,
            builder = {
                composable(route = MainAllInkStoneListDestination.route){
                    MainAllInkStoneListScreen(
                        allInkStoneList = allInkStoneList,
                        onInkStoneClicked = {
                            mainScreenViewModel.updateCurrentUiState(inkStone = it)
                            navController.navigateTopTopTo(DetailInformationDestination.route) },
                        onBackButtonClicked = onBackButtonClicked
                    )
                }
                composable(route = DetailInformationDestination.route){
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
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAllInkStoneListScreen(
    allInkStoneList: List<InkStone>,
    onInkStoneClicked: (InkStone) -> Unit,
    onBackButtonClicked: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = AllInkStoneListDestination.tabName,fontFamily = FontFamily(Font(R.font.font_1))) },
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClicked
                    ){
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    } },
                actions = {
                    Icon(
                        painterResource(id = AllInkStoneListDestination.icon),
                        modifier = Modifier.fillMaxHeight().padding(end = 20.dp),
                        contentDescription = ""
                    )
                }
            )
        }
    ){innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
                .paint(painterResource(id = R.drawable.bg_element_knowledge_screen))
        ){
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                verticalItemSpacing = 15.dp,
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.fillMaxSize()
            ){
                items(allInkStoneList){
                    Card(
                        modifier = Modifier.clickable(
                            onClick = {
                                onInkStoneClicked(it) },),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = it.intactImageId),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(
                            modifier = Modifier.padding(all = 10.dp)
                        ){
                            Text(
                                text = it.inkStoneName,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.font_1))
                            )
                            Text(
                                text = it.inkStoneDynasty + "/" + it.inkStoneType,
                                color = Color.Black.copy(alpha = 0.6f),
                                fontFamily = FontFamily(Font(R.font.font_1))
                            )
                        }
                    }
                }
            }
        }
    }
}