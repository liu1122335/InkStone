package com.example.inkstonedemo1.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.ARShowDestination
import com.example.inkstonedemo1.view.navigateSingleTopTo
import kotlin.math.roundToInt

@Composable
fun ScrollableAppBar(
    mainNavController: NavController,
    navController : NavController,
    modifier: Modifier = Modifier,
    isCollected : Boolean ,
    title: String = stringResource(id = R.string.app_name), //默认为应用名
    navigationIcon: @Composable () -> Unit =
        {
            Icon(
                modifier = modifier
                    .fillMaxSize()
                    .clickable {
                        mainNavController.popBackStack()
                    },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "ArrowBack",
                tint = Color.White,
            )
        }, //默认为返回键
    @DrawableRes backgroundImageId: Int, // 背景图片
    color: Color,
    scrollableAppBarHeight: Dp,
    toolbarOffsetHeightPx: MutableState<Float>, //向上偏移量
    onCollectedChanged : (Boolean) -> Unit
) {

    // 应用栏最大向上偏移量
    val maxOffsetHeightPx = with(LocalDensity.current) { scrollableAppBarHeight.roundToPx().toFloat() - toolBarHeight.roundToPx().toFloat() }
    // Title 偏移量参考值
    val titleOffsetWidthReferenceValue = with(LocalDensity.current) { navigationIconSize.roundToPx().toFloat() }

    Box(modifier = Modifier
        .height(scrollableAppBarHeight)
        .offset {
            IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) //设置偏移量
        }
        .fillMaxWidth()
        .background(color = color)
        .clip(RoundedCornerShape(20.dp)),
    ) {
        Image(
            painter = painterResource(id = backgroundImageId),
            contentDescription = "background",
            modifier = modifier
                .align(Alignment.Center)
                .size(300.dp)
                .padding(bottom = 30.dp),
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .offset {
                    IntOffset(
                        x = 0,
                        y = -100
                    )
                },
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //跳转AR展示画面
            DraggableTab(
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                imageId = R.drawable.ic_ar
            ) {
                navController.navigateSingleTopTo(ARShowDestination.route)
            }

            //收藏按钮
            DraggableTab(
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                imageId = if (isCollected) R.drawable.ic_collected else R.drawable.ic_uncollected
            ) {
                onCollectedChanged(!isCollected)
            }

            //朗读功能
            DraggableTab(
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                imageId = R.drawable.ic_text_to_speech
            ) {

            }
        }
        
        // 自定义应用栏
        Row(
            modifier = modifier
                .offset {
                    IntOffset(
                        x = 0,
                        y = -toolbarOffsetHeightPx.value.roundToInt() //保证应用栏是始终不动的
                    )
                }
                .align(Alignment.TopStart)
                .height(toolBarHeight)
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 导航图标
            Box(modifier = Modifier
                .size(navigationIconSize),
                contentAlignment = Alignment.Center) {
                navigationIcon()
            }
        }

        Box(
            modifier = Modifier
                .height(toolBarHeight) //和ToolBar同高
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(top = 30.dp)
                .offset {
                    IntOffset(
                        x = -((toolbarOffsetHeightPx.value / maxOffsetHeightPx) * titleOffsetWidthReferenceValue).roundToInt(),
                        y = 0
                    )
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = title,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .matchParentSize(), // 使用 matchParentSize 修饰符保证不影响父 Box尺寸
                fontSize = 20.sp
            )
        }
    }
}

// 应用栏高度
private val toolBarHeight = 56.dp
// 导航图标大小
private val navigationIconSize = 56.dp