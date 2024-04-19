package com.example.inkstonedemo1.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun DraggableTab(
    modifier: Modifier ,
    imageId : Int,
    onClick : () -> Unit,
){
    var offset by remember { mutableStateOf(Offset.Zero) }

    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .offset {
                IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
            }
            .border(width = 3.dp, color = Color.Black, shape = CircleShape),
        containerColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
    ) {
        Icon(painter = painterResource(id = imageId), contentDescription = "", tint = Color.Black)
    }
}