package com.example.inkstonedemo1.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.Destination
import com.example.inkstonedemo1.ui.theme.main_surface_color

@Composable
fun MainTabRow(
    allScreen : List<Destination>,
    onTabSelected : (Destination) -> Unit,
    currentDestination: Destination
){
    Surface(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        color = main_surface_color
    ) {
        Row (Modifier.selectableGroup(), horizontalArrangement = Arrangement.SpaceEvenly){
            allScreen.forEach{destination ->
                MyTab(
                    text = destination.tabName,
                    icon = destination.icon,
                    onSelected = { onTabSelected(destination) },
                    isSelected = currentDestination == destination
                )
            }
        }
    }
}

@Composable
fun MyTab(
    text : String,
    icon : Int,
    onSelected: () -> Unit,
    isSelected : Boolean
){
    val color = MaterialTheme.colorScheme.onBackground
    val durationMillis = if(isSelected) 150 else 100
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = 100
        )
    }

    val tabTintColor by animateColorAsState(
        targetValue = if (isSelected) color else color.copy(alpha = 0.60f),
        animationSpec = animSpec
    )

    Row (
        modifier = Modifier
            .clickable(onClick = {}, indication = null, interactionSource = remember {
                MutableInteractionSource()
            })
            .padding(16.dp)
            .height(56.dp)
            .animateContentSize()
            .selectable(
                selected = isSelected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = null/*rememberRipple(
                    bounded = false,
                    color = Color.Unspecified,
                    radius = Dp.Unspecified
                )*/
            )
    ){
        Icon(painterResource(id = icon), contentDescription = "" , tint = tabTintColor)
        if (isSelected){
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text, fontSize = 18.sp,color = tabTintColor,fontFamily = FontFamily(Font(R.font.font_1)))
        }
    }
}
