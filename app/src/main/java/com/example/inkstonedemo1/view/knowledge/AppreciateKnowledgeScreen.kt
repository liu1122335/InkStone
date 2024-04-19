package com.example.inkstonedemo1.view.knowledge

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.ui.theme.md_theme_light_surfaceVariant
import com.example.inkstonedemo1.data.allAppreciateKnowledge
import com.example.inkstonedemo1.model.AppreciateKnowledgeDestination
import com.example.inkstonedemo1.model.knowledge.AppreciateKnowledge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppreciateKnowledgeScreen(
    onBackButtonClicked: () -> Unit
){
    var isExpanded by remember { mutableStateOf(false) }
    var currentCardId by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material3.Text(text = AppreciateKnowledgeDestination.tabName)
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClicked
                    ){
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    Icon(
                        painterResource(id = AppreciateKnowledgeDestination.icon),
                        modifier = Modifier.fillMaxHeight().padding(end = 20.dp),
                        contentDescription = ""
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .paint(painter = painterResource(id = R.drawable.bg_element_knowledge_screen)),
            contentAlignment = Alignment.Center
        ){
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().padding(all = 20.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ){
                items(allAppreciateKnowledge.size){
                    AppreciateKnowledgeCard(
                        appreciateKnowledge = allAppreciateKnowledge[it],
                        onClicked = {
                            currentCardId = it
                            isExpanded = !isExpanded
                        },
                        currentId = it
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                modifier = Modifier.fillMaxSize()
            ) {
                AppreciateKnowledgeDetailCard(appreciateKnowledge = allAppreciateKnowledge[currentCardId])
            }
        }
        BackHandler(
            enabled = isExpanded,
            onBack = {
                isExpanded = !isExpanded
            }
        )
    }
}

@Composable
fun AppreciateKnowledgeCard(
    appreciateKnowledge: AppreciateKnowledge,
    onClicked : (Int) -> Unit,
    currentId : Int
){
    Card(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClicked(currentId)
            }
            .size(200.dp, 300.dp)
            .padding(top = 20.dp),
        colors = CardDefaults.cardColors(containerColor = md_theme_light_surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = appreciateKnowledge.coverImage),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = appreciateKnowledge.title,
                modifier = Modifier.padding(top = 25.dp),
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun AppreciateKnowledgeDetailCard(
    appreciateKnowledge: AppreciateKnowledge
){
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Image(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3f),
                painter = painterResource(id = appreciateKnowledge.coverImage),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }


        Text(
            text = appreciateKnowledge.content,
            modifier = Modifier.padding(all = 10.dp)
        )
    }
}