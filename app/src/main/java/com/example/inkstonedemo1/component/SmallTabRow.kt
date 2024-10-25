package com.example.inkstonedemo1.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.Destination

@Composable
fun SmallTabRow(
    allScreen : List<Destination>,
    onTabSelected : (Destination) -> Unit,
    currentDestination: Destination,
    backgroundColor : Color
){
    Box (
        modifier = Modifier
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier = Modifier
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            allScreen.forEach {destination ->
                SmallTab(
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
fun SmallTab(
    text : String,
    icon: Int,
    onSelected : () -> Unit,
    isSelected : Boolean
){
    val color = Color.Black
    val durationMillis = if (isSelected) 150 else 100
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
            .clickable(
                onClick = {},
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            )
            .padding(horizontal = 16.dp)
            .height(36.dp)
            .animateContentSize()
            .selectable(
                selected = isSelected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = text, tint = tabTintColor)
        if (isSelected){
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text, color = tabTintColor,fontFamily = FontFamily(Font(R.font.font_1)))
        }
    }
}