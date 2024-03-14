package com.example.inkstonedemo1.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.Gold
import com.example.compose.dark_golden
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicators(
    modifier: Modifier,
    pagerCount : Int,
    pagerState : PagerState
){
    Row (
        modifier = modifier.padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ){
        repeat(pagerCount){iteration ->
            val color by animateColorAsState(if (pagerState.currentPage == iteration) Gold else MaterialTheme.colorScheme.onPrimary)
            val coroutineScope = rememberCoroutineScope()
            Canvas(modifier = Modifier
                .size(15.dp)
                .padding(horizontal = 3.dp)
                .clickable(
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(iteration) }
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
            ){
                drawCircle(color = color)
            }
        }
    }
}