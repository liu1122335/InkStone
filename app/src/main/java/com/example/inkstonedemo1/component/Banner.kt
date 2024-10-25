package com.example.inkstonedemo1.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(
    images: List<Int>,
    autoScrollDuration: Long = 1500L
) {
    val pageCount = images.size
    val pagerState = rememberPagerState{
        pageCount
    }
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()){
        with(pagerState){
            var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey){
                launch {
                    delay(timeMillis = autoScrollDuration)
                    val nextPage = (currentPage + 1).mod(pageCount)
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    Box {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp
        ) {index ->
            Log.d("index",index.toString())
            BannerCard(
                imageId = images[index],
                modifier = Modifier
                    .carouselTransition(index, pagerState)
                    .padding(top = 10.dp)
            )
        }

        DotIndicators(
            pageCount = pageCount,
            pagerState = pagerState,
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
        )
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicators(
    pageCount : Int,
    pagerState : PagerState,
    modifier: Modifier
){
    Row (
        modifier = modifier.padding(bottom = 7.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ){
        repeat(pageCount){iteration ->
            val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface
            Canvas(modifier = Modifier
                .size(12.dp)
                .padding(horizontal = 2.dp), onDraw = {
                drawCircle(color)
            })
        }
    }
}


@Composable
fun BannerCard(
    imageId : Int,
    modifier : Modifier
){
    Card (modifier = modifier){
        Image(painter = painterResource(id = imageId), contentDescription = "",modifier = Modifier.size(width = 400.dp, height = 240.dp), contentScale = ContentScale.FillBounds)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.7f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f,1f)
            )
        alpha = transformation
        scaleY = transformation
    }